package pl.rasilewicz.restaurant_manager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.rasilewicz.restaurant_manager.entities.Order;
import pl.rasilewicz.restaurant_manager.services.OrderServiceImpl;

import java.util.List;

@Controller
public class LoggedUserController {

    private final OrderServiceImpl orderService;

    public LoggedUserController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/admin/order/history")
    public String ordersHistory (Model model){

        List<Order> orderList = orderService.findAllOrders();
        model.addAttribute("orderList", orderList);

        return "admin/ordersHistory";
    }

    @GetMapping("/admin/order/history/view")
    public String orderDetails (@RequestParam Long id, Model model){

        Order order = orderService.findOrderById(id);
        model.addAttribute("order", order);

        return "admin/orderDetails";
    }
}
