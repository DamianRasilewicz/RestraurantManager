package pl.rasilewicz.restaurant_manager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.rasilewicz.restaurant_manager.entities.Order;
import pl.rasilewicz.restaurant_manager.services.OrderServiceImpl;

import java.util.List;

@Controller
public class AdminController {

    private final OrderServiceImpl orderService;

    public AdminController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/admin/order/history")
    public String ordersHistory (Model model){

        List<Order> orderList = orderService.findAllOrders();
        model.addAttribute("orderList", orderList);

        return "admin/ordersHistory";
    }
}
