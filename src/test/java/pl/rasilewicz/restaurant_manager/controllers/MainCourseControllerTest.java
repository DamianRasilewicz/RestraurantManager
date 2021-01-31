package pl.rasilewicz.restaurant_manager.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.rasilewicz.restaurant_manager.entities.Addition;
import pl.rasilewicz.restaurant_manager.entities.Order;
import pl.rasilewicz.restaurant_manager.entities.Product;
import pl.rasilewicz.restaurant_manager.entities.TypeOfProduct;
import pl.rasilewicz.restaurant_manager.repositories.AdditionRepository;
import pl.rasilewicz.restaurant_manager.repositories.ProductRepository;
import pl.rasilewicz.restaurant_manager.repositories.TypeOfProductRepository;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class MainCourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TypeOfProductRepository typeOfProductRepository;

    @Autowired
    private AdditionRepository additionRepository;

    @Test
    @Transactional
    void mainCourseOrdering() throws Exception{
        //given
        Product testMainCourse = new Product();
        testMainCourse.setName("Schabowy");

        TypeOfProduct testTypeOfProduct = new TypeOfProduct();
        testTypeOfProduct.setName("Dania Główne");
        typeOfProductRepository.save(testTypeOfProduct);

        testMainCourse.setType(testTypeOfProduct);
        testMainCourse.setPrice(25);
        productRepository.save(testMainCourse);

        Addition testAddition = new Addition();
        testAddition.setDescription("MainCourse");
        testAddition.setName("Salami");
        testAddition.setPrice(5);
        additionRepository.save(testAddition);

        //when
        Order order = new Order();
        order.setNumberOfProducts(0);
        order.setOrderCost(0);

        mockMvc.perform(MockMvcRequestBuilders.get("/order/mainCourse?id=" + testMainCourse.getId()).sessionAttr("order", order))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200));
        //then
    }

    @Test
    @Transactional
    void mainCourseOrdered() throws Exception{
        //given
        Product testMainCourse = new Product();
        testMainCourse.setName("Schabowy");

        TypeOfProduct testTypeOfProduct = new TypeOfProduct();
        testTypeOfProduct.setName("Dania Główne");
        typeOfProductRepository.save(testTypeOfProduct);

        testMainCourse.setType(testTypeOfProduct);
        testMainCourse.setPrice(25);
        productRepository.save(testMainCourse);

        //when
        Order order = new Order();
        order.setNumberOfProducts(0);
        order.setOrderCost(0);

        mockMvc.perform(MockMvcRequestBuilders.post("/order/drink?selectedDrinkId=" + testMainCourse.getId()).sessionAttr("order", order))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));

        //then
        assertThat(order.getProducts()).isNotNull();
        assertThat(order.getNumberOfProducts()).isEqualTo(1);
        assertThat(order.getOrderCost()).isEqualTo(25);
    }
}