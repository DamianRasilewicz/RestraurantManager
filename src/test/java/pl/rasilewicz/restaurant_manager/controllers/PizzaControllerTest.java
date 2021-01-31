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
class PizzaControllerTest {

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
    void pizzaOrdering() throws Exception{
        //given
        Product testPizza = new Product();
        testPizza.setName("Tosca");

        TypeOfProduct testTypeOfProduct = new TypeOfProduct();
        testTypeOfProduct.setName("Pizza");
        typeOfProductRepository.save(testTypeOfProduct);

        testPizza.setType(testTypeOfProduct);
        testPizza.setPrice(25);
        productRepository.save(testPizza);

        Addition testAddition = new Addition();
        testAddition.setDescription("Pizza");
        testAddition.setName("Salami");
        testAddition.setPrice(5);
        additionRepository.save(testAddition);
        //when
        Order order = new Order();
        order.setNumberOfProducts(0);
        order.setOrderCost(0);

        mockMvc.perform(MockMvcRequestBuilders.get("/order/pizza?id=" + testPizza.getId()).sessionAttr("order", order))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200));
        //then
    }

    @Test
    @Transactional
    void pizzaOrdered() throws Exception{
        //given
        Product testPizza = new Product();
        testPizza.setName("Tosca");

        TypeOfProduct testTypeOfProduct = new TypeOfProduct();
        testTypeOfProduct.setName("Pizza");
        typeOfProductRepository.save(testTypeOfProduct);

        testPizza.setType(testTypeOfProduct);
        testPizza.setPrice(25);
        productRepository.save(testPizza);

        Addition testAddition = new Addition();
        testAddition.setDescription("Pizza");
        testAddition.setName("Salami");
        testAddition.setPrice(5);
        additionRepository.save(testAddition);
        //when
        Order order = new Order();
        order.setNumberOfProducts(0);
        order.setOrderCost(0);

        mockMvc.perform(MockMvcRequestBuilders.post("/order/pizza?selectedPizzaId=" + testPizza.getId()).sessionAttr("order", order))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
        //then
        assertThat(order.getProducts()).isNotNull();
        assertThat(order.getNumberOfProducts()).isEqualTo(1);
        assertThat(order.getOrderCost()).isEqualTo(25);
    }
}