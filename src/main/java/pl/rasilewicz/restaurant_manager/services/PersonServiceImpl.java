package pl.rasilewicz.restaurant_manager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.rasilewicz.restaurant_manager.entities.Person;
import pl.rasilewicz.restaurant_manager.repositories.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void save(Person user) {
        personRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String personName) throws UsernameNotFoundException {
        Person person = personRepository.findPersonByName(personName);
        if (person == null) {
            throw new UsernameNotFoundException("Invalid login or password.");
        }
        return new VLVPersonDetails(person);
    }

    public PersonServiceImpl() {
    }

    @Override
    public Person findPersonByName(String personName) {
        return personRepository.findPersonByName(personName);
    }

    @Override
    public Person findPersonById(Long id) {
        return personRepository.findPersonById(id);
    }
}
