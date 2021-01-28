package pl.rasilewicz.restaurant_manager.services;

import pl.rasilewicz.restaurant_manager.entities.Address;

public interface AddressService {

    void save(Address address);

    Address findAddressByPersonId(Long id);
}
