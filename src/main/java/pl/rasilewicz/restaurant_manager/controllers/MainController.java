package pl.rasilewicz.restaurant_manager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.rasilewicz.restaurant_manager.entities.Order;
import pl.rasilewicz.restaurant_manager.entities.Product;
import pl.rasilewicz.restaurant_manager.services.ProductServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MainController {

    private final ProductServiceImpl productService;

    public MainController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String index(HttpSession session, Model model) {

        List<Product> listOfPizzas = productService.findProductsByType(1);
        model.addAttribute("listOfPizzas", listOfPizzas);

        List<Product> listOfMainCourses = productService.findProductsByType(2);
        model.addAttribute("listOfMainCourses", listOfMainCourses);

        List<Product> listOfSoups = productService.findProductsByType(3);
        model.addAttribute("listOfSoups", listOfSoups);

        List<Product> listOfDrinks = productService.findProductsByType(4);
        model.addAttribute("listOfDrinks", listOfDrinks);



        double costOfOrder = 0.00;
        session.setAttribute("costOfOrder", costOfOrder);

        int numberOfProducts = 0;
        session.setAttribute("numberOfProducts", numberOfProducts);

        Order order = new Order();
        session.setAttribute("order", order);

    return "mainPage/index";
    }
}
