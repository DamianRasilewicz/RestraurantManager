package pl.rasilewicz.restaurant_manager.repositories;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.rasilewicz.restaurant_manager.entities.Product;
import pl.rasilewicz.restaurant_manager.entities.TypeOfProduct;

import java.util.List;

@Repository
@EntityScan(basePackages = "pl.rasilewicz.restaurant_manager.entities")
public interface TypeOfProductRepository extends JpaRepository<TypeOfProduct, Integer> {

    @Query(value = "SELECT * FROM restaurant_manager.type_of_products", nativeQuery = true)
    List<TypeOfProduct> findAllTypesOfProduct();
}
