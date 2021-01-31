package pl.rasilewicz.restaurant_manager.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.rasilewicz.restaurant_manager.entities.Address;
import pl.rasilewicz.restaurant_manager.entities.Person;
import pl.rasilewicz.restaurant_manager.repositories.AddressRepository;
import pl.rasilewicz.restaurant_manager.repositories.PersonRepository;

import javax.transaction.Transactional;


@SpringBootTest
@AutoConfigureMockMvc
class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Test
    @Transactional
    void registeredPerson() throws Exception{
        //given
        Person testPerson = new Person();
        testPerson.setFirstName("Test");
        testPerson.setLastName("Testing");
        testPerson.setName("test123");
        testPerson.setEmail("testmail@onet.pl");
        testPerson.setPassword("123456789");
        testPerson.setPhoneNumber("567890123");
        personRepository.save(testPerson);
        //when

        mockMvc.perform(MockMvcRequestBuilders.post("/registration/person").flashAttr("newPerson", testPerson))
                .andDo(MockMvcResultHandlers.print())
                 .andExpect(MockMvcResultMatchers.redirectedUrl("/registration/address?newPersonName=" + testPerson.getName()));

        //then
    }

    @Test
    @Transactional
    void registrationFormAddress() throws Exception{
        Person testPerson = new Person();
        testPerson.setFirstName("Test");
        testPerson.setLastName("Testing");
        testPerson.setName("test123");
        testPerson.setEmail("testmail@onet.pl");
        testPerson.setPassword("123456789");
        testPerson.setPhoneNumber("567890123");
        personRepository.save(testPerson);
        //when

        mockMvc.perform(MockMvcRequestBuilders.get("/registration/address?newPersonName=" + testPerson.getName()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200));
        //then
    }

    @Test
    @Transactional
    void registeredAddress() throws Exception{
        Person testPerson = new Person();
        testPerson.setFirstName("Test");
        testPerson.setLastName("Testing");
        testPerson.setName("test123");
        testPerson.setEmail("testmail@onet.pl");
        testPerson.setPassword("123456789");
        testPerson.setPhoneNumber("567890123");
        personRepository.save(testPerson);

        Address testAddress = new Address();
        testAddress.setStreet("Testowa");
        testAddress.setBuildingNumber("44/5");
        testAddress.setPostcode("85-743");
        testAddress.setCity("Testowo");
        testAddress.setPerson(testPerson);
        addressRepository.save(testAddress);

        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/registration/address").flashAttr("newAddress", testAddress).flashAttr("newPersonName", testPerson.getName()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/registration/address?success&newPersonName=test123"));
        //then
    }
}