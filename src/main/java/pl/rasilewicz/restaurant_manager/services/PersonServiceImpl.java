package pl.rasilewicz.restaurant_manager.services;

import org.springframework.stereotype.Service;
import pl.rasilewicz.restaurant_manager.entities.Person;
import pl.rasilewicz.restaurant_manager.repositories.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void save(Person user) {
        personRepository.save(user);
    }
}
