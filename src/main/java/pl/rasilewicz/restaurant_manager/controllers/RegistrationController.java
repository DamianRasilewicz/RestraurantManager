package pl.rasilewicz.restaurant_manager.controllers;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.rasilewicz.restaurant_manager.entities.Address;
import pl.rasilewicz.restaurant_manager.entities.Person;
import pl.rasilewicz.restaurant_manager.entities.Role;
import pl.rasilewicz.restaurant_manager.services.AddressServiceImpl;
import pl.rasilewicz.restaurant_manager.services.PersonServiceImpl;
import pl.rasilewicz.restaurant_manager.services.RoleServiceImpl;

@Controller
public class RegistrationController {

    private final PersonServiceImpl personService;
    private final AddressServiceImpl addressService;
    private final RoleServiceImpl roleService;

    public RegistrationController(PersonServiceImpl personService, AddressServiceImpl addressService, RoleServiceImpl roleService) {
        this.personService = personService;
        this.addressService = addressService;
        this.roleService = roleService;
    }

    @GetMapping("/register")
    public String registrationForm(Model model){
        Person newPerson = new Person();
        model.addAttribute("newPerson", newPerson);

        Address address = new Address();
        model.addAttribute("address", address);

        return "mainPages/registration";
    }

    @PostMapping("/register")
    public String registeredPerson(Person newPerson, Address address){
        newPerson.setPassword(BCrypt.hashpw(newPerson.getPassword(), BCrypt.gensalt(12)));
        newPerson.setRegistered(true);

        Role role = roleService.findRoleById(2);
        newPerson.setRole(role);

        newPerson.setEnabled(true);

        personService.save(newPerson);

        address.setPerson(newPerson);
        addressService.save(address);

        return "redirect:/";
    }
}
