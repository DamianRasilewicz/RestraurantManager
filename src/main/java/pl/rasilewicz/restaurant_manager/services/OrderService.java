package pl.rasilewicz.restaurant_manager.services;

import pl.rasilewicz.restaurant_manager.entities.Order;

public interface OrderService {

    Order findOrderById(Long id);

    void save(Order order);
}
