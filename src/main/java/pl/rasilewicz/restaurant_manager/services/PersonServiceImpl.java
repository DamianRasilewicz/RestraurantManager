package pl.rasilewicz.restaurant_manager.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.rasilewicz.restaurant_manager.entities.Person;
import pl.rasilewicz.restaurant_manager.repositories.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void save(Person user) {
        personRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String personLogin) throws UsernameNotFoundException {
        Person person = personRepository.findByLogin(personLogin);
        if (person == null) {
            throw new UsernameNotFoundException("Invalid user email or password.");
        }
        return new VLVUserDetails(person);
    }

    public PersonServiceImpl() {
    }
}
