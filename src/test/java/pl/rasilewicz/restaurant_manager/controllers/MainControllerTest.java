package pl.rasilewicz.restaurant_manager.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.rasilewicz.restaurant_manager.entities.Product;
import pl.rasilewicz.restaurant_manager.entities.TypeOfProduct;
import pl.rasilewicz.restaurant_manager.repositories.ProductRepository;
import pl.rasilewicz.restaurant_manager.repositories.TypeOfProductRepository;

import javax.transaction.Transactional;


@SpringBootTest
@AutoConfigureMockMvc
class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TypeOfProductRepository typeOfProductRepository;

    @Test
    @Transactional
    void shouldGetIndexPage() throws Exception{
        //given
        Product testPizza = new Product();
        testPizza.setName("Tosca");

        Product testMainCourse = new Product();
        testMainCourse.setName("Schabowy");

        Product testSoup = new Product();
        testSoup.setName("Rosół");

        Product testDrink = new Product();
        testDrink.setName("Cola");

        TypeOfProduct testTypeOfProduct1 = new TypeOfProduct();
        testTypeOfProduct1.setName("Pizza");
        typeOfProductRepository.save(testTypeOfProduct1);

        TypeOfProduct testTypeOfProduct2 = new TypeOfProduct();
        testTypeOfProduct2.setName("Danie Główne");
        typeOfProductRepository.save(testTypeOfProduct2);

        TypeOfProduct testTypeOfProduct3 = new TypeOfProduct();
        testTypeOfProduct3.setName("Zupy");
        typeOfProductRepository.save(testTypeOfProduct3);

        TypeOfProduct testTypeOfProduct4 = new TypeOfProduct();
        testTypeOfProduct4.setName("Napoje");
        typeOfProductRepository.save(testTypeOfProduct4);


        testPizza.setType(testTypeOfProduct1);
        testPizza.setPrice(25);
        productRepository.save(testPizza);

        testMainCourse.setType(testTypeOfProduct2);
        testMainCourse.setPrice(30);
        productRepository.save(testMainCourse);

        testSoup.setType(testTypeOfProduct3);
        testSoup.setPrice(10);
        productRepository.save(testSoup);

        testDrink.setType(testTypeOfProduct4);
        testDrink.setPrice(5);
        productRepository.save(testDrink);

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200));

        //then
    }
}