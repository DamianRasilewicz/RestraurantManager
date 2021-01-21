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
public class SoupController {

    private final ProductServiceImpl productService;

    public SoupController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping("/order/soup")
    public String soupOrdering(@RequestParam Long id, Model model){

        Product soup = productService.findProductById(id);
        model.addAttribute("selectedSoup", soup);

        return "mainPage/soupOrder";
    }

    @PostMapping("/order/soup")
    public String soupOrdered(@RequestParam Long selectedSoupId, HttpSession session){

        Order order = (Order) session.getAttribute("order");

        Product selectedSoup = productService.findProductById(selectedSoupId);

        double costOfOrder = 0.00;

        costOfOrder = costOfOrder + selectedSoup.getPrice();

        order.setOrderCost(order.getOrderCost() + costOfOrder);

        order.setNumberOfProducts(order.getNumberOfProducts() + 1);

        List<Addition> selectedAdditionsList = new ArrayList<>();
        selectedSoup.setAdditions(selectedAdditionsList);

        if (order.getProducts() == null) {
            List<Product> listProductsInOrder = new ArrayList<>();
            listProductsInOrder.add(selectedSoup);
            order.setProducts(listProductsInOrder);
        }else {
            List<Product> listProductsInOrder = order.getProducts();
            listProductsInOrder.add(selectedSoup);
            order.setProducts(listProductsInOrder);
        }

        session.setAttribute("order", order);

        return "redirect:/";
    }
}
