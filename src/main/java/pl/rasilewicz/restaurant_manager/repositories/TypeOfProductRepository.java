package pl.rasilewicz.restaurant_manager.repositories;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.rasilewicz.restaurant_manager.entities.TypeOfProduct;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@EntityScan(basePackages = "pl.rasilewicz.restaurant_manager.entities")
public interface TypeOfProductRepository extends JpaRepository<TypeOfProduct, Integer> {

    @Query(value = "SELECT * FROM restaurant_manager.type_of_products ORDER BY id", nativeQuery = true)
    List<TypeOfProduct> findAllTypesOfProduct();

    TypeOfProduct findTypeOfProductById(Integer id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE restaurant_manager.type_of_products SET name = ?1 WHERE id = ?2", nativeQuery = true)
    void update(String name, Integer id);
}
