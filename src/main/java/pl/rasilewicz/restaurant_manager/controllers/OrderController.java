package pl.rasilewicz.restaurant_manager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.rasilewicz.restaurant_manager.entities.Addition;
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

    @GetMapping("/order/delete")
    public String deleteOrder (HttpSession session){

        session.removeAttribute("order");
        Order order = new Order();
        order.setNumberOfProducts(0);
        order.setOrderCost(0.00);
        session.setAttribute("order", order);

        return "redirect:/";
    }

    @GetMapping("/order/product/delete")
    public String orderProductDelete (@RequestParam int index, HttpSession session){

        Order order = (Order) session.getAttribute("order");

        List <Product> productsInOrder = order.getProducts();
        Product deletingProduct = productsInOrder.get(index);

        double costOfRemovingProduct = 0.00;
        if (deletingProduct.getAdditions() == null){
            costOfRemovingProduct = costOfRemovingProduct + deletingProduct.getPrice();
        }else {

            for (Addition addition : deletingProduct.getAdditions()) {
                costOfRemovingProduct = costOfRemovingProduct + addition.getPrice();
            }
            costOfRemovingProduct = costOfRemovingProduct + deletingProduct.getPrice();
        }

        double orderCost = order.getOrderCost();
        orderCost = orderCost - costOfRemovingProduct;
        order.setOrderCost(orderCost);

        int numberOfProduct = order.getNumberOfProducts();
        numberOfProduct = numberOfProduct - 1;
        order.setNumberOfProducts(numberOfProduct);
        order.setNumberOfProducts(numberOfProduct);

        productsInOrder.remove(index);
        order.setProducts(productsInOrder);

        session.setAttribute("order", order);

        return "redirect:/order/basket";
    }


    @GetMapping("/order/submit")
    public String orderSubmitForm (HttpSession session, Model model){

        Order order = (Order) session.getAttribute("order");
        model.addAttribute("order", order);

        return "mainPage/orderSubmitForm";
    }

    @PostMapping("/order/submit")
    public String orderSubmitted (){

        return "mainPage/orderSubmitted";
    }
}
