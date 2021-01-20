package pl.rasilewicz.restaurant_manager.services;

import pl.rasilewicz.restaurant_manager.entities.Product;
import java.util.List;

public interface ProductService {

    List<Product> findProductsByType(Integer typeId);

    List<Product> findAllProducts();
}
