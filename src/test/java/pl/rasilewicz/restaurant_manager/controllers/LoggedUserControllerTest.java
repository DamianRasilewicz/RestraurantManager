package pl.rasilewicz.restaurant_manager.controllers;

import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.rasilewicz.restaurant_manager.entities.Order;
import pl.rasilewicz.restaurant_manager.entities.Person;
import pl.rasilewicz.restaurant_manager.repositories.OrderRepository;
import pl.rasilewicz.restaurant_manager.repositories.PersonRepository;
import pl.rasilewicz.restaurant_manager.services.OrderServiceImpl;


import javax.transaction.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@AutoConfigureMockMvc
class LoggedUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PersonRepository personRepository;

    @Test
    @Transactional
    void shouldGetOrdersHistoryAdmin() throws Exception{
        //given
        Order order = new Order();
        order.setNumberOfProducts(0);
        order.setOrderCost(0);
        order.setOrderTime(LocalTime.now());
        order.setOrderDate(LocalDate.now());
        order.setComment("testComment");

        Person testPerson = new Person();
        testPerson.setFirstName("Test");
        testPerson.setLastName("Testing");
        testPerson.setName("test123");
        testPerson.setEmail("test@test.com");
        personRepository.save(testPerson);

        order.setPerson(testPerson);
        orderRepository.save(order);

        Order order2 = new Order();
        order2.setNumberOfProducts(0);
        order2.setOrderCost(0);
        order2.setOrderTime(LocalTime.now());
        order2.setOrderDate(LocalDate.now());
        order2.setComment("testComment");

        order.setPerson(testPerson);
        orderRepository.save(order2);
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/order/history"))
                .andDo(MockMvcResultHandlers.print());
        //then
    }


    @Test
    @Transactional
    void shouldGetOrderDetailsAdmin() throws Exception{
        //given
        Order order = new Order();
        order.setNumberOfProducts(0);
        order.setOrderCost(0);
        order.setOrderTime(LocalTime.now());
        order.setOrderDate(LocalDate.now());
        order.setComment("testComment");

        Person testPerson = new Person();
        testPerson.setFirstName("Test");
        testPerson.setLastName("Testing");
        testPerson.setName("test123");
        testPerson.setEmail("test@test.com");
        personRepository.save(testPerson);

        order.setPerson(testPerson);
        orderRepository.save(order);

        Order order2 = new Order();
        order2.setNumberOfProducts(0);
        order2.setOrderCost(0);
        order2.setOrderTime(LocalTime.now());
        order2.setOrderDate(LocalDate.now());
        order2.setComment("testComment");

        order.setPerson(testPerson);
        orderRepository.save(order2);

        String personName = testPerson.getName();


        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/order/history").sessionAttr("personName", personName))
                .andDo(MockMvcResultHandlers.print());
        //then
    }

}