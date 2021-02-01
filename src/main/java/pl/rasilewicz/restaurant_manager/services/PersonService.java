package pl.rasilewicz.restaurant_manager.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import pl.rasilewicz.restaurant_manager.entities.Person;

public interface PersonService extends UserDetailsService {

    void save(Person user);

    Person findPersonByName(String personName);

    Person findPersonById(Long id);
}
