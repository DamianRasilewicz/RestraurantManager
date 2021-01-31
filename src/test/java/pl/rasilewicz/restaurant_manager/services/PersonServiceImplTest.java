package pl.rasilewicz.restaurant_manager.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.rasilewicz.restaurant_manager.entities.Person;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PersonServiceImplTest {

    @Autowired
    private PersonServiceImpl personService;

    @Test
    void findPersonByName() {
        //given

        //when
        Person testPerson = personService.findPersonByName("user");
        //then
        assertThat(testPerson).isNotNull();
        assertThat(testPerson.getName()).isEqualTo("user");
    }
}