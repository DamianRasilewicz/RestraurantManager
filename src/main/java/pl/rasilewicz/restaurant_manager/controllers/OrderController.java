package pl.rasilewicz.restaurant_manager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.rasilewicz.restaurant_manager.entities.Order;
import pl.rasilewicz.restaurant_manager.entities.Product;
import pl.rasilewicz.restaurant_manager.services.OrderServiceImpl;
import pl.rasilewicz.restaurant_manager.services.ProductServiceImpl;
import java.util.List;

import javax.servlet.http.HttpSession;

@Controller
public class OrderController {

    private final ProductServiceImpl productService;
    private final OrderServiceImpl orderService;

    public OrderController(ProductServiceImpl productService, OrderServiceImpl orderService) {
        this.productService = productService;
        this.orderService = orderService;
    }

    @GetMapping("/order/basket")
    public String ordersBasket (HttpSession session, Model model){

        Order order = (Order) session.getAttribute("order");
        model.addAttribute("order", order);

        List<Product> productsInOrder = order.getProducts();
        model.addAttribute("productsInOrder", productsInOrder);

        return "mainPage/ordersBasket";
    }

    @GetMapping("/order/submit")
    public String orderSubmitForm (@RequestParam Long id, HttpSession session, Model model){

        Order order = (Order) session.getAttribute("order");
        model.addAttribute("order", order);

        return "mainPage/orderSubmitForm";
    }
}
