package pl.rasilewicz.restaurant_manager.repositories;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rasilewicz.restaurant_manager.entities.Address;

@Repository
@EntityScan(basePackages = "pl.rasilewicz.restaurant_manager.entities")
public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findAddressByPersonId(Long id);
}
