package pl.rasilewicz.restaurant_manager.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.rasilewicz.restaurant_manager.entities.Order;
import pl.rasilewicz.restaurant_manager.entities.Product;
import pl.rasilewicz.restaurant_manager.entities.TypeOfProduct;
import pl.rasilewicz.restaurant_manager.repositories.ProductRepository;
import pl.rasilewicz.restaurant_manager.repositories.TypeOfProductRepository;

import javax.transaction.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class DrinkControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TypeOfProductRepository typeOfProductRepository;

    @Test
    @Transactional
    void shouldGetSingleDrinkProductById() throws Exception {
        //given
        Product testDrink = new Product();
        testDrink.setName("Cola");

        TypeOfProduct testTypeOfProduct = new TypeOfProduct();
        testTypeOfProduct.setName("Napoje");
        typeOfProductRepository.save(testTypeOfProduct);

        testDrink.setType(testTypeOfProduct);
        testDrink.setPrice(5);
        productRepository.save(testDrink);

        //when
        Order order = new Order();
        order.setNumberOfProducts(0);
        order.setOrderCost(0);

        mockMvc.perform(MockMvcRequestBuilders.get("/order/drink?id=" + testDrink.getId()).sessionAttr("order", order))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200));
        //then

    }

    @Test
    @Transactional
    void shouldOrderedSelectedDrinkById() throws Exception{
        //given
        Product testDrink = new Product();
        testDrink.setName("Cola");

        TypeOfProduct testTypeOfProduct = new TypeOfProduct();
        testTypeOfProduct.setName("Napoje");
        typeOfProductRepository.save(testTypeOfProduct);

        testDrink.setType(testTypeOfProduct);
        testDrink.setPrice(5);
        productRepository.save(testDrink);

        //when
        Order order = new Order();
        order.setNumberOfProducts(0);
        order.setOrderCost(0);
        mockMvc.perform(MockMvcRequestBuilders.post("/order/drink?selectedDrinkId=" + testDrink.getId()).sessionAttr("order", order))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));

        //then
        assertThat(order.getProducts()).isNotNull();
        assertThat(order.getNumberOfProducts()).isEqualTo(1);
        assertThat(order.getOrderCost()).isEqualTo(5);
    }
}