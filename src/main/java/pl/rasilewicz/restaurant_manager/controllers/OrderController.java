package pl.rasilewicz.restaurant_manager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.rasilewicz.restaurant_manager.entities.Order;
import pl.rasilewicz.restaurant_manager.entities.Product;
import pl.rasilewicz.restaurant_manager.services.ProductServiceImpl;
import java.util.List;

import javax.servlet.http.HttpSession;

@Controller
public class OrderController {

    private final ProductServiceImpl productService;

    public OrderController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping("/order/basket")
    public String ordersBasket (HttpSession session, Model model){

        Order order = (Order) session.getAttribute("order");
        model.addAttribute("order", order);

        List<Product> productsInOrder = order.getProducts();
        model.addAttribute("productsInOrder", productsInOrder);

        return "mainPage/ordersBasket";
    }
}
