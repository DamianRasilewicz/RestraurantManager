package pl.rasilewicz.restaurant_manager.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.rasilewicz.restaurant_manager.entities.Addition;
import pl.rasilewicz.restaurant_manager.entities.Order;
import pl.rasilewicz.restaurant_manager.entities.Product;
import pl.rasilewicz.restaurant_manager.services.AdditionServiceImpl;
import pl.rasilewicz.restaurant_manager.services.ProductServiceImpl;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PizzaController {

    private final AdditionServiceImpl additionService;
    private final ProductServiceImpl productService;

        public PizzaController(AdditionServiceImpl additionService, ProductServiceImpl productService) {
        this.additionService = additionService;
        this.productService = productService;
    }

    private final Logger logger = LoggerFactory.getLogger(PizzaController.class);

    @GetMapping("order/pizza")
        public String pizzaOrdering(@RequestParam Long id, Model model) {

        Product pizza = productService.findProductById(id);
        model.addAttribute("selectedPizza", pizza);

        List<Addition> listOfPizzaToppings = additionService.findAdditionsByDescription("Pizza");
        model.addAttribute("pizzaToppings", listOfPizzaToppings);

        return "mainPage/pizzaOrder";
    }

    @PostMapping("order/pizza")
        public String pizzaOrdered(@RequestParam Long selectedPizzaId, @RequestParam(value = "selectedAdditions", required = false) Integer[] selectedAdditions, HttpSession session, Model model){

        Order order = (Order) session.getAttribute("order");

        List<Addition> selectedAdditionsList = new ArrayList<>();

        for (Integer additionName :selectedAdditions) {
            selectedAdditionsList.add(additionService.findAdditionById(additionName));
        }

        logger.error(selectedAdditionsList.toString());

        Product selectedPizza = productService.findProductById(selectedPizzaId);

        selectedPizza.setAdditions(selectedAdditionsList);

        List<Product> listProductsInOrder = new ArrayList<>();
        listProductsInOrder.add(selectedPizza);

        logger.error(selectedPizza.toString());

        order.setProducts(listProductsInOrder);
        order.setNumberOfProducts(order.getNumberOfProducts() + 1);

        double costOfOrder = 0.00;

        List<Addition> selectedPizzaAdditions = selectedPizza.getAdditions();

        for (Addition addition:selectedPizzaAdditions) {
            costOfOrder = costOfOrder + addition.getPrice();
        }

        logger.error(Double.toString(costOfOrder));

        costOfOrder = costOfOrder + selectedPizza.getPrice();

        order.setOrderCost(order.getOrderCost() + costOfOrder);

        logger.error(String.valueOf(order.getOrderCost()));

        session.setAttribute("order", order);

        return "redirect:/";
    }
}
