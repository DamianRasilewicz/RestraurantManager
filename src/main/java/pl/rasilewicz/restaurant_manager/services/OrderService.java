package pl.rasilewicz.restaurant_manager.services;

import pl.rasilewicz.restaurant_manager.entities.Order;

import java.util.List;

public interface OrderService {

    Order findOrderById(Long id);

    void save(Order order);

    List<Order> findAllOrders();
}
