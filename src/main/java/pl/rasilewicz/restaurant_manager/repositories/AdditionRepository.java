package pl.rasilewicz.restaurant_manager.repositories;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.rasilewicz.restaurant_manager.entities.Addition;


import java.util.List;

@Repository
@EntityScan(basePackages = "pl.rasilewicz.restaurant_manager.entities")
public interface AdditionRepository extends JpaRepository<Addition, Integer> {

    @Query(value = "SELECT * FROM restaurant_manager.additions WHERE description = ?1" , nativeQuery = true)
    List<Addition> findAdditionsByDescription(String description);

    Addition findAdditionById(Integer id);

    Addition findAdditionByName(String name);
}
