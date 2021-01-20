package pl.rasilewicz.restaurant_manager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.rasilewicz.restaurant_manager.entities.Addition;
import pl.rasilewicz.restaurant_manager.entities.Product;
import pl.rasilewicz.restaurant_manager.services.AdditionServiceImpl;
import pl.rasilewicz.restaurant_manager.services.ProductServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PizzaController {

    private final AdditionServiceImpl additionService;
    private final ProductServiceImpl productService;

    public PizzaController(AdditionServiceImpl additionService, ProductServiceImpl productService) {
        this.additionService = additionService;
        this.productService = productService;
    }

    @GetMapping("order/pizza")
        public String pizzaOrdering(@RequestParam Long id, Model model) {

        Product pizza = productService.findProductById(id);
        model.addAttribute("selectedPizza", pizza);

        List<Addition> listOfPizzaToppings = additionService.findAdditionsByDescription("Pizza");
        model.addAttribute("pizzaToppings", listOfPizzaToppings);

        return "mainPage/pizzaOrder";
    }
}
