package pl.rasilewicz.restaurant_manager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import pl.rasilewicz.restaurant_manager.entities.*;
import pl.rasilewicz.restaurant_manager.services.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

@Controller
public class OrderController {

    private final OrderServiceImpl orderService;
    private final PersonServiceImpl personService;
    private final AddressServiceImpl addressService;
    private final ProductServiceImpl productService;
    private final MailServiceImpl mailService;
    private final TemplateEngine templateEngine;

    public OrderController(OrderServiceImpl orderService, PersonServiceImpl personService,
                           AddressServiceImpl addressService, ProductServiceImpl productService,
                           MailServiceImpl mailService, TemplateEngine templateEngine) {
        this.orderService = orderService;
        this.personService = personService;
        this.addressService = addressService;
        this.productService = productService;
        this.mailService = mailService;
        this.templateEngine = templateEngine;

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

    @GetMapping("/user/order/basket")
    public String ordersBasketUser (HttpSession session, Model model){

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

        return "user/ordersBasket";
    }

    @GetMapping("/admin/order/basket")
    public String ordersBasketAdmin (HttpSession session, Model model){

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

        return "admin/ordersBasket";
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

    @GetMapping("/user/order/delete")
    public String deleteOrderUser (HttpSession session){

        session.removeAttribute("order");
        Order order = new Order();
        order.setNumberOfProducts(0);
        order.setOrderCost(0.00);
        session.setAttribute("order", order);

        return "redirect:/user/home";
    }

    @GetMapping("/admin/order/delete")
    public String deleteOrderAdmin (HttpSession session){

        session.removeAttribute("order");
        Order order = new Order();
        order.setNumberOfProducts(0);
        order.setOrderCost(0.00);
        session.setAttribute("order", order);

        return "redirect:/admin/home";
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

    @GetMapping("/user/order/product/delete")
    public String orderProductDeleteUser (@RequestParam int index, HttpSession session){

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

        return "redirect:/user/order/basket";
    }

    @GetMapping("/admin/order/product/delete")
    public String orderProductDeleteAdmin (@RequestParam int index, HttpSession session){

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

        return "redirect:/admin/order/basket";
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

    @GetMapping("/user/order/submit")
    public String orderSubmitFormUser (HttpSession session, Model model){

        Order order = (Order) session.getAttribute("order");
        model.addAttribute("order", order);

        Person person = personService.findPersonByName((String) session.getAttribute("personName"));
        model.addAttribute("person", person);

        if(person.getAddress() == null) {
            Address address = new Address();
            model.addAttribute("address", address);
        }else {
            Address address = addressService.findAddressByPersonId(person.getId());
            model.addAttribute("address", address);
        }

        return "user/orderSubmitForm";
    }

    @GetMapping("/admin/order/submit")
    public String orderSubmitFormAdmin (HttpSession session, Model model){

        Order order = (Order) session.getAttribute("order");
        model.addAttribute("order", order);

        Person person = personService.findPersonByName((String) session.getAttribute("personName"));
        model.addAttribute("person", person);

        if(person.getAddress() == null) {
            Address address = new Address();
            model.addAttribute("address", address);
        }else {
            Address address = addressService.findAddressByPersonId(person.getId());
            model.addAttribute("address", address);
        }

        return "admin/orderSubmitForm";
    }

    @PostMapping("/order/submit")
    public String orderSubmitted (Order order, Person person, Address address, HttpSession session) throws MessagingException {

        Order orderInSession = (Order) session.getAttribute("order");
        List<Product> productsInOrder = orderInSession.getProducts();
        order.setProducts(productsInOrder);

        for (Product product : productsInOrder) {
            productService.save(product);
        }

        order.setOrderDate(LocalDate.now());
        order.setOrderTime(LocalTime.now());
        orderService.save(order);

        addressService.save(address);

        List<Order> personOrders = new ArrayList<>();
        personOrders.add(order);
        person.setOrders(personOrders);
        person.setAddress(address);
        personService.save(person);


        Context context = new Context();
        context.setVariable("productsInOrder", order.getProducts());
        context.setVariable("order", order);
        context.setVariable("person", person);
        context.setVariable("address", address);
        String body = templateEngine.process("mailTemplate", context);
        mailService.sendEmail(person.getEmail(), "Twoje zamówienie w serwisie Restauracja Metapack", body);

        session.removeAttribute("order");

        return "redirect:/";
    }

    @PostMapping("/user/order/submit")
    public String orderSubmittedUser (Order order, Person person, Address address, HttpSession session) throws MessagingException {

        Order orderInSession = (Order) session.getAttribute("order");
        List<Product> productsInOrder = orderInSession.getProducts();
        order.setProducts(productsInOrder);

        for (Product product : productsInOrder) {
            productService.save(product);
        }

        order.setOrderDate(LocalDate.now());
        order.setOrderTime(LocalTime.now());

        Person personFromDatabase = personService.findPersonByName((String) session.getAttribute("personName"));

        Address personAddress = personFromDatabase.getAddress();
        if (!address.getStreet().equals(personAddress.getStreet()) || !address.getBuildingNumber().equals(personAddress.getBuildingNumber())
             || !address.getCity().equals(personAddress.getCity())){
            addressService.save(address);
        }


        if (person.getFirstName().equals(personFromDatabase.getFirstName()) && person.getLastName().equals(personFromDatabase.getLastName())){
            order.setPerson(personFromDatabase);
            orderService.save(order);
            
        }else {
            person.setAddress(address);
            personService.save(person);
            order.setPerson(person);
            orderService.save(order);
        }


        Context context = new Context();
        context.setVariable("productsInOrder", order.getProducts());
        context.setVariable("order", order);
        context.setVariable("person", person);
        context.setVariable("address", address);
        String body = templateEngine.process("mailTemplate", context);
        mailService.sendEmail(person.getEmail(), "Twoje zamówienie w serwisie Restauracja Metapack", body);

        session.removeAttribute("order");

        return "redirect:/user/home";
    }

    @PostMapping("/admin/order/submit")
    public String orderSubmittedAdmin (Order order, Person person, Address address, HttpSession session) throws MessagingException {

        Order orderInSession = (Order) session.getAttribute("order");
        List<Product> productsInOrder = orderInSession.getProducts();
        order.setProducts(productsInOrder);

        for (Product product : productsInOrder) {
            productService.save(product);
        }

        order.setOrderDate(LocalDate.now());
        order.setOrderTime(LocalTime.now());
        orderService.save(order);

        Person personFromDatabase = personService.findPersonByName((String) session.getAttribute("personName"));

        Address personAddress = personFromDatabase.getAddress();
        if (!address.getStreet().equals(personAddress.getStreet()) || !address.getBuildingNumber().equals(personAddress.getBuildingNumber())
                || address.getCity().equals(personAddress.getCity())){
            addressService.save(address);
        }


        if (person.getFirstName().equals(personFromDatabase.getFirstName()) && person.getLastName().equals(personFromDatabase.getLastName())){
            List<Order> personOrders = personFromDatabase.getOrders();
            personOrders.add(order);
            personFromDatabase.setOrders(personOrders);
            personService.save(personFromDatabase);
        }else {
            List<Order> personOrders = new ArrayList<>();
            personOrders.add(order);
            person.setOrders(personOrders);
            person.setAddress(address);
            personService.save(person);
        }


        Context context = new Context();
        context.setVariable("productsInOrder", order.getProducts());
        context.setVariable("order", order);
        context.setVariable("person", person);
        context.setVariable("address", address);
        String body = templateEngine.process("mailTemplate", context);
        mailService.sendEmail(person.getEmail(), "Twoje zamówienie w serwisie Restauracja Metapack", body);

        session.removeAttribute("order");

        return "redirect:/admin/home";
    }

}
