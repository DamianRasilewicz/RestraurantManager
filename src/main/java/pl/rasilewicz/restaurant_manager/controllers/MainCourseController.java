package pl.rasilewicz.restaurant_manager.controllers;

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
public class MainCourseController {

    private final ProductServiceImpl productService;
    private final AdditionServiceImpl additionService;

    public MainCourseController(ProductServiceImpl productService, AdditionServiceImpl additionService) {
        this.productService = productService;
        this.additionService = additionService;
    }

    @GetMapping("/order/mainCourse")
    public String mainCourseOrdering(@RequestParam Long id, Model model){

        Product mainCourse = productService.findProductById(id);
        model.addAttribute("selectedMainCourse", mainCourse);

        List<Addition> listOfMainCourseAdditions = additionService.findAdditionsByDescription("MainCourse");
        model.addAttribute("mainCourseAdditions", listOfMainCourseAdditions);

        return "/mainPages/mainCourseOrder";
    }

    @PostMapping("order/mainCourse")
    public String mainCourseOrdered(@RequestParam Long selectedMainCourseId, @RequestParam(value = "selectedAdditions", required = false) Integer[] selectedAdditions, HttpSession session){

        Order order = (Order) session.getAttribute("order");

        Product selectedMainCourse = productService.findProductById(selectedMainCourseId);

        double costOfOrder = 0.00;

        if (selectedAdditions != null) {

            List<Addition> selectedAdditionsList = new ArrayList<>();

            for (Integer additionId : selectedAdditions) {
                selectedAdditionsList.add(additionService.findAdditionById(additionId));
            }

            for (Addition addition : selectedAdditionsList) {
                costOfOrder = costOfOrder + addition.getPrice();
            }

            selectedMainCourse.setAdditions(selectedAdditionsList);
        }else {

            List<Addition> selectedAdditionsList = new ArrayList<>();
            selectedMainCourse.setAdditions(selectedAdditionsList);

        }

        costOfOrder = costOfOrder + selectedMainCourse.getPrice();

        order.setOrderCost(order.getOrderCost() + costOfOrder);

        order.setNumberOfProducts(order.getNumberOfProducts() + 1);

        if (order.getProducts() == null) {
            List<Product> listProductsInOrder = new ArrayList<>();
            listProductsInOrder.add(selectedMainCourse);
            order.setProducts(listProductsInOrder);
        }else {
            List<Product> listProductsInOrder = order.getProducts();
            listProductsInOrder.add(selectedMainCourse);
            order.setProducts(listProductsInOrder);
        }

        session.setAttribute("order", order);

        return "redirect:/";
    }
}
