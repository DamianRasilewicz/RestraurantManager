package pl.rasilewicz.restaurant_manager.repositories;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.rasilewicz.restaurant_manager.entities.Product;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@EntityScan(basePackages = "pl.rasilewicz.restaurant_manager.entities")
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM restaurant_manager.products WHERE type_of_product_id = ?1" , nativeQuery = true)
    List<Product> findProductsByType(Integer typeId);

    Product findProductById(Long id);

    @Query(value = "SELECT * FROM restaurant_manager.products ORDER BY type_of_product_id;" , nativeQuery = true)
    List<Product> findAllProducts();

    @Modifying
    @Transactional
    @Query(value = "UPDATE restaurant_manager.products SET name = ?1 , price = ?2 , type_of_product_id = ?3 WHERE id = ?4", nativeQuery = true)
    void update(String name, Double price, Integer typeOfProductId, Long id);

}
