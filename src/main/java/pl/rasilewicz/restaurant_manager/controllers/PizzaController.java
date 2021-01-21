package pl.rasilewicz.restaurant_manager.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String pizzaOrdered(@RequestParam Long selectedPizzaId, @RequestParam(value = "selectedAdditions", required = false) Integer[] selectedAdditions, HttpSession session){

        Order order = (Order) session.getAttribute("order");

        Product selectedPizza = productService.findProductById(selectedPizzaId);

        double costOfOrder = 0.00;

        if (selectedAdditions != null) {

            List<Addition> selectedAdditionsList = new ArrayList<>();

            for (Integer additionId : selectedAdditions) {
                selectedAdditionsList.add(additionService.findAdditionById(additionId));
            }

            for (Addition addition : selectedAdditionsList) {
                costOfOrder = costOfOrder + addition.getPrice();
            }

            selectedPizza.setAdditions(selectedAdditionsList);
        }else {

            List<Addition> selectedAdditionsList = new ArrayList<>();
            selectedPizza.setAdditions(selectedAdditionsList);

        }

        costOfOrder = costOfOrder + selectedPizza.getPrice();

        order.setOrderCost(order.getOrderCost() + costOfOrder);

        order.setNumberOfProducts(order.getNumberOfProducts() + 1);

        if (order.getProducts() == null) {
            List<Product> listProductsInOrder = new ArrayList<>();
            listProductsInOrder.add(selectedPizza);
            order.setProducts(listProductsInOrder);
        }else {
            List<Product> listProductsInOrder = order.getProducts();
            listProductsInOrder.add(selectedPizza);
            order.setProducts(listProductsInOrder);
        }

        session.setAttribute("order", order);

        return "redirect:/";
    }
}