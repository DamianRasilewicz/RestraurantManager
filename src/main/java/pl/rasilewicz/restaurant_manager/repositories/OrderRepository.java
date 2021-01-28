package pl.rasilewicz.restaurant_manager.repositories;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.rasilewicz.restaurant_manager.entities.Order;
import java.util.List;

@Repository
@EntityScan(basePackages = "pl.rasilewicz.restaurant_manager.entities")
public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findOrderById(Long id);

    @Query(value = "SELECT * FROM restaurant_manager.orders", nativeQuery = true)
    List<Order> findAllOrders();

    @Query(value = "SELECT * FROM restaurant_manager.persons_orders WHERE person_id = ?1", nativeQuery = true)
    List<Order> findOrdersByPersonId(Long id);
}
