package pl.rasilewicz.restaurant_manager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.rasilewicz.restaurant_manager.entities.*;
import pl.rasilewicz.restaurant_manager.services.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class LoggedUserController {

    private final OrderServiceImpl orderService;
    private final PersonServiceImpl personService;
    private final ProductServiceImpl productService;
    private final TypeOfProductsServiceImpl typeOfProductsService;
    private final AdditionServiceImpl additionService;

    public LoggedUserController(OrderServiceImpl orderService, PersonServiceImpl personService, ProductServiceImpl productService,
                                TypeOfProductsServiceImpl typeOfProductsService, AdditionServiceImpl additionService) {
        this.orderService = orderService;
        this.personService = personService;
        this.productService = productService;
        this.typeOfProductsService = typeOfProductsService;
        this.additionService = additionService;
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
    public String productEditingForm (@RequestParam Long id, Model model){

        Product editingProduct = productService.findProductById(id);
        model.addAttribute("editingProduct", editingProduct);

        List<TypeOfProduct> typeOfProductList = typeOfProductsService.findAllTypesOfProduct();
        model.addAttribute("allTypeOfProducts", typeOfProductList);

        return "admin/productEditingForm";
    }

    @PostMapping("/admin/products/edit")
    public String submitProductEditingForm (@RequestParam Integer typeOfProductId, @ModelAttribute Product editingProduct){

        productService.update(editingProduct.getName(), editingProduct.getPrice(), typeOfProductId, editingProduct.getId());

        return "redirect:/admin/products";
    }

    @GetMapping("/admin/products/delete")
    public String productDeleting (@RequestParam Long id){

        productService.deleteProductById(id);

        return "redirect:/admin/products";
    }

    @GetMapping("/admin/products/add")
    public String productAddingForm (Model model){

        Product newProduct = new Product();
        model.addAttribute("newProduct", newProduct);

        List<TypeOfProduct> typeOfProductList = typeOfProductsService.findAllTypesOfProduct();
        model.addAttribute("allTypeOfProducts", typeOfProductList);

        return "admin/productAddingForm";
    }

    @PostMapping("/admin/products/add")
    public String submitProductAddingForm (@RequestParam Integer typeOfProductId, @ModelAttribute("newProduct") @Valid Product newProduct,
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

    @GetMapping("/admin/typeOfProducts/edit")
    public String typeOfProductEditingForm (@RequestParam Integer id, Model model){

        TypeOfProduct editingTypeOfProduct = typeOfProductsService.findTypeOfProductById(id);
        model.addAttribute("editingTypeOfProduct", editingTypeOfProduct);

        return "admin/typeOfProductEditingForm";
    }

    @PostMapping("/admin/typeOfProducts/edit")
    public String submitTypeOfProductEditingForm (@ModelAttribute TypeOfProduct editingTypeOfProduct){

        typeOfProductsService.update(editingTypeOfProduct.getName(), editingTypeOfProduct.getId());

        return "redirect:/admin/typeOfProducts";
    }

    @GetMapping("/admin/typeOfProducts/delete")
    public String typeOfProductDeleting (@RequestParam Integer id){

        typeOfProductsService.deleteTypeOfProductById(id);

        return "redirect:/admin/typeOfProducts";
    }

    @GetMapping("/admin/typeOfProducts/add")
    public String typeOfProductAddingForm (Model model){

        TypeOfProduct newTypeOfProduct = new TypeOfProduct();
        model.addAttribute("newTypeOfProduct", newTypeOfProduct);

        return "admin/typeOfProductAddingForm";
    }

    @PostMapping("/admin/typeOfProducts/add")
    public String submitTypeOfProductAddingForm (@ModelAttribute("newTypeOfProduct") @Valid TypeOfProduct newTypeOfProduct, BindingResult resultNewTypeOfProduct){

        if (resultNewTypeOfProduct.hasErrors()) {
            return "admin/typeOfProductAddingForm";
        }

        typeOfProductsService.save(newTypeOfProduct);

        return "redirect:/admin/typeOfProducts";
    }

    @GetMapping("/admin/additions")
    public String allAdditions (Model model){

        List<Addition> allAdditions = additionService.findAllAdditions();
        model.addAttribute("allAdditions", allAdditions);

        return "admin/allAdditions";
    }

}
