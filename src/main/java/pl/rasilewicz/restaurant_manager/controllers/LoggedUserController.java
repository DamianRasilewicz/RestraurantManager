package pl.rasilewicz.restaurant_manager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.rasilewicz.restaurant_manager.entities.Order;
import pl.rasilewicz.restaurant_manager.entities.Person;
import pl.rasilewicz.restaurant_manager.entities.Product;
import pl.rasilewicz.restaurant_manager.services.OrderServiceImpl;
import pl.rasilewicz.restaurant_manager.services.PersonServiceImpl;
import pl.rasilewicz.restaurant_manager.services.ProductServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoggedUserController {

    private final OrderServiceImpl orderService;
    private final PersonServiceImpl personService;
    private final ProductServiceImpl productService;

    public LoggedUserController(OrderServiceImpl orderService, PersonServiceImpl personService, ProductServiceImpl productService) {
        this.orderService = orderService;
        this.personService = personService;
        this.productService = productService;
    }

    @GetMapping("/admin/order/history")
    public String ordersHistoryAdmin (Model model){

        List<Order> orderList = orderService.findAllOrders();
        model.addAttribute("orderList", orderList);

        return "admin/ordersHistory";
    }

    @GetMapping("/user/order/history")
    public String ordersHistoryUser (Model model, HttpSession session){

        Person person = personService.findPersonById((Long)session.getAttribute("personId"));

        List<Order> orderList = person.getOrders();
        model.addAttribute("orderList", orderList);

        return "user/ordersHistory";
    }

    @GetMapping("/admin/order/history/view")
    public String orderDetailsAdmin (@RequestParam Long id, Model model){

        Order order = orderService.findOrderById(id);
        model.addAttribute("order", order);

        return "admin/orderDetails";
    }

    @GetMapping("/user/order/history/view")
    public String orderDetailsUser (@RequestParam Long id, Model model){

        Order order = orderService.findOrderById(id);
        model.addAttribute("order", order);

        return "user/orderDetails";
    }

    @GetMapping("/admin/products")
    public String allProducts (Model model){

        List<Product> allProducts = productService.findAllProducts();
        model.addAttribute("allProducts", allProducts);

        return "admin/allProducts";
    }

}
