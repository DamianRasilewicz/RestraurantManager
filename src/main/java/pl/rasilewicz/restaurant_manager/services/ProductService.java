package pl.rasilewicz.restaurant_manager.services;

import pl.rasilewicz.restaurant_manager.entities.Product;
import java.util.List;

public interface ProductService {

    List<Product> findProductsByType(Integer typeId);

    void save(Product product);

    Product findProductById(Long id);

    List<Product> findAllProducts();

    void update(String name, Double price, Integer typeOfProductId, Long id);
}
