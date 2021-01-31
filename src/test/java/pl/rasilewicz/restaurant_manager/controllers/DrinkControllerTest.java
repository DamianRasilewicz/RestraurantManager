package pl.rasilewicz.restaurant_manager.controllers;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.rasilewicz.restaurant_manager.entities.Order;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class DrinkControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldGetSingleDrinkProductById() throws Exception {
        //given

        //when
        Order order = new Order();
        order.setNumberOfProducts(0);
        order.setOrderCost(0);
        mockMvc.perform(MockMvcRequestBuilders.get("/order/drink?id=1").sessionAttr("order", order))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200));

        //then
    }

    @Test
    void drinkOrdered() {
    }
}