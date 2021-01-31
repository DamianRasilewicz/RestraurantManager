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
class SoupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TypeOfProductRepository typeOfProductRepository;

    @Test
    @Transactional
    void soupOrdering() throws Exception{
        //given
        Product testSoup = new Product();
        testSoup.setName("Rosół");

        TypeOfProduct testTypeOfProduct = new TypeOfProduct();
        testTypeOfProduct.setName("Zupy");
        typeOfProductRepository.save(testTypeOfProduct);

        testSoup.setType(testTypeOfProduct);
        testSoup.setPrice(25);
        productRepository.save(testSoup);

        //when
        Order order = new Order();
        order.setNumberOfProducts(0);
        order.setOrderCost(0);

        mockMvc.perform(MockMvcRequestBuilders.get("/order/soup?id=" + testSoup.getId()).sessionAttr("order", order))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200));
        //then
    }

    @Test
    void soupOrdered() throws Exception{
        //given
        Product testSoup = new Product();
        testSoup.setName("Rosół");

        TypeOfProduct testTypeOfProduct = new TypeOfProduct();
        testTypeOfProduct.setName("Zupy");
        typeOfProductRepository.save(testTypeOfProduct);

        testSoup.setType(testTypeOfProduct);
        testSoup.setPrice(10);
        productRepository.save(testSoup);

        //when
        Order order = new Order();
        order.setNumberOfProducts(0);
        order.setOrderCost(0);

        mockMvc.perform(MockMvcRequestBuilders.post("/order/soup?selectedSoupId=" + testSoup.getId()).sessionAttr("order", order))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
        //then
        assertThat(order.getProducts()).isNotNull();
        assertThat(order.getNumberOfProducts()).isEqualTo(1);
        assertThat(order.getOrderCost()).isEqualTo(10);
    }
}