package pl.rasilewicz.restaurant_manager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.rasilewicz.restaurant_manager.entities.Order;
import pl.rasilewicz.restaurant_manager.entities.Product;
import pl.rasilewicz.restaurant_manager.services.OrderServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminController {

    private final OrderServiceImpl orderService;

    public AdminController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/admin/order/history")
    public String ordersHistory (HttpSession session){



        return "admin/ordersHistory";
    }
}
