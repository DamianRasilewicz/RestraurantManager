package pl.rasilewicz.restaurant_manager.repositories;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.rasilewicz.restaurant_manager.entities.Addition;
import pl.rasilewicz.restaurant_manager.entities.Order;
import pl.rasilewicz.restaurant_manager.entities.Product;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@EntityScan(basePackages = "pl.rasilewicz.restaurant_manager.entities")
public interface AdditionRepository extends JpaRepository<Addition, Integer> {

    @Query(value = "SELECT * FROM restaurant_manager.additions WHERE description = ?1" , nativeQuery = true)
    List<Addition> findAdditionsByDescription(String description);

    Addition findAdditionById(Integer id);

    Addition findAdditionByName(String name);

    @Query(value = "SELECT * FROM restaurant_manager.additions ORDER BY description DESC", nativeQuery = true)
    List<Addition> findAllAdditions();

    @Modifying
    @Transactional
    @Query(value = "UPDATE restaurant_manager.additions SET name = ?1 , description = ?2 , price = ?3 WHERE id = ?4", nativeQuery = true)
    void update(String name, String description, Double price, Integer id);
}
