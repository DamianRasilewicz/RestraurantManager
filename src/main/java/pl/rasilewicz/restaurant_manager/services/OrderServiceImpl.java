package pl.rasilewicz.restaurant_manager.services;

import org.springframework.stereotype.Service;
import pl.rasilewicz.restaurant_manager.entities.Order;
import pl.rasilewicz.restaurant_manager.repositories.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order findOrderById(Long id) {
        return orderRepository.findOrderById(id);
    }
}
