package pl.rasilewicz.restaurant_manager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.rasilewicz.restaurant_manager.entities.*;
import pl.rasilewicz.restaurant_manager.services.AddressServiceImpl;
import pl.rasilewicz.restaurant_manager.services.OrderServiceImpl;
import pl.rasilewicz.restaurant_manager.services.PersonServiceImpl;
import pl.rasilewicz.restaurant_manager.services.ProductServiceImpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.http.HttpSession;

@Controller
public class OrderController {

    private final OrderServiceImpl orderService;
    private final PersonServiceImpl personService;
    private final AddressServiceImpl addressService;
    private final ProductServiceImpl productService;

    public OrderController(OrderServiceImpl orderService, PersonServiceImpl personService,
                           AddressServiceImpl addressService, ProductServiceImpl productService) {
        this.orderService = orderService;
        this.personService = personService;
        this.addressService = addressService;
        this.productService = productService;

    }

    @GetMapping("/order/basket")
    public String ordersBasket (HttpSession session, Model model){

        if (session.getAttribute("order") == null) {
            Order order = new Order();
            order.setNumberOfProducts(0);
            order.setOrderCost(0.00);
            session.setAttribute("order", order);
        }

        Order order = (Order) session.getAttribute("order");
        model.addAttribute("order", order);

        List<Product> productsInOrder = order.getProducts();
        model.addAttribute("productsInOrder", productsInOrder);

        return "mainPages/ordersBasket";
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

        Person person = new Person();
        model.addAttribute("person", person);

        Address address = new Address();
        model.addAttribute("address", address);

        return "mainPages/orderSubmitForm";
    }

    @PostMapping("/order/submit")
    public String orderSubmitted (Order order, Person person, Address address, HttpSession session){

        personService.save(person);

        address.setPerson(person);
        addressService.save(address);

        Order orderInSession = (Order) session.getAttribute("order");
        List<Product> productsInOrder = orderInSession.getProducts();
        order.setProducts(productsInOrder);

        for (Product product : productsInOrder) {
            productService.save(product);
        }

        order.setPerson(person);
        order.setOrderDate(LocalDate.now());
        order.setOrderTime(LocalTime.now());
        orderService.save(order);

        session.removeAttribute("order");

        return "redirect:/";
    }


}
