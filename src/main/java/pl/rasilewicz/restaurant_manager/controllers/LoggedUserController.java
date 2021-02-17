package pl.rasilewicz.restaurant_manager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.rasilewicz.restaurant_manager.entities.*;
import pl.rasilewicz.restaurant_manager.services.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class LoggedUserController {

    private final OrderServiceImpl orderService;
    private final PersonServiceImpl personService;

    public LoggedUserController(OrderServiceImpl orderService, PersonServiceImpl personService) {
        this.orderService = orderService;
        this.personService = personService;
    }

    @GetMapping("/user/order/history")
    public String ordersHistoryUser (Model model, HttpSession session){

        Person person = personService.findPersonById((Long)session.getAttribute("personId"));

        List<Order> orderList = person.getOrders();
        model.addAttribute("orderList", orderList);

        return "user/ordersHistory";
    }

    @GetMapping("/user/order/history/view")
    public String orderDetailsUser (@RequestParam Long id, Model model){

        Order order = orderService.findOrderById(id);
        model.addAttribute("order", order);

        return "user/orderDetails";
    }


}
