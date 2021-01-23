package pl.rasilewicz.restaurant_manager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.rasilewicz.restaurant_manager.entities.Addition;
import pl.rasilewicz.restaurant_manager.entities.Order;
import pl.rasilewicz.restaurant_manager.entities.Product;
import pl.rasilewicz.restaurant_manager.services.ProductServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DrinkController {

    private final ProductServiceImpl productService;

    public DrinkController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping("/order/drink")
    public String drinkOrdering(@RequestParam Long id, Model model){

        Product drink = productService.findProductById(id);
        model.addAttribute("selectedDrink", drink);

        return "mainPages/drinkOrder";
    }

    @PostMapping("/order/drink")
    public String drinkOrdered(@RequestParam Long selectedDrinkId, HttpSession session){

        Order order = (Order) session.getAttribute("order");

        Product selectedDrink = productService.findProductById(selectedDrinkId);

        double costOfOrder = 0.00;

        costOfOrder = costOfOrder + selectedDrink.getPrice();

        order.setOrderCost(order.getOrderCost() + costOfOrder);

        order.setNumberOfProducts(order.getNumberOfProducts() + 1);

        List<Addition> selectedAdditionsList = new ArrayList<>();
        selectedDrink.setAdditions(selectedAdditionsList);

        if (order.getProducts() == null) {
            List<Product> listProductsInOrder = new ArrayList<>();
            listProductsInOrder.add(selectedDrink);
            order.setProducts(listProductsInOrder);
        }else {
            List<Product> listProductsInOrder = order.getProducts();
            listProductsInOrder.add(selectedDrink);
            order.setProducts(listProductsInOrder);
        }

        session.setAttribute("order", order);

        return "redirect:/";
    }
}
