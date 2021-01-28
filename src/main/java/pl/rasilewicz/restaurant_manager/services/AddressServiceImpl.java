package pl.rasilewicz.restaurant_manager.services;

import org.springframework.stereotype.Service;
import pl.rasilewicz.restaurant_manager.entities.Address;
import pl.rasilewicz.restaurant_manager.repositories.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService{

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public void save(Address address) {
        addressRepository.save(address);
    }

    @Override
    public Address findAddressByPersonId(Long id) {
        return addressRepository.findAddressByPersonId(id);
    }
}
