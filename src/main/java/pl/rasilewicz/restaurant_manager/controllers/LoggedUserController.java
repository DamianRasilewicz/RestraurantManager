package pl.rasilewicz.restaurant_manager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.rasilewicz.restaurant_manager.entities.Order;
import pl.rasilewicz.restaurant_manager.entities.Person;
import pl.rasilewicz.restaurant_manager.entities.Product;
import pl.rasilewicz.restaurant_manager.entities.TypeOfProduct;
import pl.rasilewicz.restaurant_manager.services.OrderServiceImpl;
import pl.rasilewicz.restaurant_manager.services.PersonServiceImpl;
import pl.rasilewicz.restaurant_manager.services.ProductServiceImpl;
import pl.rasilewicz.restaurant_manager.services.TypeOfProductsServiceImpl;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class LoggedUserController {

    private final OrderServiceImpl orderService;
    private final PersonServiceImpl personService;
    private final ProductServiceImpl productService;
    private final TypeOfProductsServiceImpl typeOfProductsService;

    public LoggedUserController(OrderServiceImpl orderService, PersonServiceImpl personService, ProductServiceImpl productService,
                                TypeOfProductsServiceImpl typeOfProductsService) {
        this.orderService = orderService;
        this.personService = personService;
        this.productService = productService;
        this.typeOfProductsService = typeOfProductsService;
    }

    @GetMapping("/admin/order/history")
    public String ordersHistoryAdmin (Model model){

        List<Order> orderList = orderService.findAllOrders();
        model.addAttribute("orderList", orderList);

        return "admin/ordersHistory";
    }

    @GetMapping("/user/order/history")
    public String ordersHistoryUser (Model model, HttpSession session){

        Person person = personService.findPersonById((Long)session.getAttribute("personId"));

        List<Order> orderList = person.getOrders();
        model.addAttribute("orderList", orderList);

        return "user/ordersHistory";
    }

    @GetMapping("/admin/order/history/view")
    public String orderDetailsAdmin (@RequestParam Long id, Model model){

        Order order = orderService.findOrderById(id);
        model.addAttribute("order", order);

        return "admin/orderDetails";
    }

    @GetMapping("/user/order/history/view")
    public String orderDetailsUser (@RequestParam Long id, Model model){

        Order order = orderService.findOrderById(id);
        model.addAttribute("order", order);

        return "user/orderDetails";
    }

    @GetMapping("/admin/products")
    public String allProducts (Model model){

        List<Product> allProducts = productService.findAllProducts();
        model.addAttribute("allProducts", allProducts);

        return "admin/allProducts";
    }

    @GetMapping("/admin/products/edit")
    public String ProductEditingForm (@RequestParam Long id, Model model){

        Product editingProduct = productService.findProductById(id);
        model.addAttribute("editingProduct", editingProduct);

        List<TypeOfProduct> typeOfProductList = typeOfProductsService.findAllTypesOfProduct();
        model.addAttribute("allTypeOfProducts", typeOfProductList);

        return "admin/productEditingForm";
    }

    @PostMapping("/admin/products/edit")
    public String SubmitProductEditingForm (@RequestParam Integer typeOfProductId, @ModelAttribute Product editingProduct){

        productService.update(editingProduct.getName(), editingProduct.getPrice(), typeOfProductId, editingProduct.getId());

        return "redirect:/admin/products";
    }

    @GetMapping("/admin/products/delete")
    public String ProductDeleting (@RequestParam Long id){

        productService.deleteProductById(id);

        return "redirect:/admin/products";
    }

    @GetMapping("/admin/products/add")
    public String ProductAddingForm (Model model){

        Product newProduct = new Product();
        model.addAttribute("newProduct", newProduct);

        List<TypeOfProduct> typeOfProductList = typeOfProductsService.findAllTypesOfProduct();
        model.addAttribute("allTypeOfProducts", typeOfProductList);

        return "admin/productAddingForm";
    }

    @PostMapping("/admin/products/add")
    public String SubmitProductAddingForm (@RequestParam Integer typeOfProductId, @ModelAttribute("newProduct") @Valid Product newProduct,
                                           BindingResult resultNewProduct, Model model){

        if (resultNewProduct.hasErrors()) {
            List<TypeOfProduct> typeOfProductList = typeOfProductsService.findAllTypesOfProduct();
            model.addAttribute("allTypeOfProducts", typeOfProductList);
            return "admin/productAddingForm";
        }

        newProduct.setType(typeOfProductsService.findTypeOfProductById(typeOfProductId));
        productService.save(newProduct);

        return "redirect:/admin/products";
    }

    @GetMapping("/admin/typeOfProducts")
    public String allTypeOfProducts (Model model){

        List<TypeOfProduct> allTypesOfProducts = typeOfProductsService.findAllTypesOfProduct();
        model.addAttribute("allTypesOfProducts", allTypesOfProducts);

        return "admin/allTypesOfProducts";
    }

}
