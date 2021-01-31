package pl.rasilewicz.restaurant_manager.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.rasilewicz.restaurant_manager.entities.Order;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    @Test
    void findOrderById() {
        //given

        //when
        Order testOrder = orderService.findOrderById(1L);
        //then
        assertThat(testOrder).isNotNull();
        assertThat(testOrder.getNumberOfProducts()).isEqualTo(2);
    }

    @Test
    void findAllOrders() {
        //given

        //when
        List<Order> testOrderList = orderService.findAllOrders();
        //then
        assertThat(testOrderList).isNotNull();
        assertThat(testOrderList.get(0).getNumberOfProducts()).isEqualTo(2);
    }

    @Test
    void findOrdersByPersonId() {
        //given

        //when
        List<Order> testOrderList = orderService.findOrdersByPersonId(1L);
        //then
        assertThat(testOrderList).isNotNull();
        assertThat(testOrderList.get(0).getOrderCost()).isEqualTo(51);
    }
}