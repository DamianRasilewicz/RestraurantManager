package pl.rasilewicz.restaurant_manager.controllers;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.rasilewicz.restaurant_manager.entities.Address;
import pl.rasilewicz.restaurant_manager.entities.Person;
import pl.rasilewicz.restaurant_manager.entities.Role;
import pl.rasilewicz.restaurant_manager.services.AddressServiceImpl;
import pl.rasilewicz.restaurant_manager.services.PersonServiceImpl;
import pl.rasilewicz.restaurant_manager.services.RoleServiceImpl;
import javax.validation.Valid;

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

    @GetMapping("/registration")
    public String registrationFormPerson(Model model){
        Person person = new Person();
        model.addAttribute("person", person);

        Address address = new Address();
        model.addAttribute("address", address);

        return "mainPages/registration";
    }

    @PostMapping("/registration")
    public String registeredPerson(@ModelAttribute("person") @Valid Person newPerson, BindingResult resultPerson, @ModelAttribute("address") @Valid Address newAddress,
                                   BindingResult resultAddress){
        if (resultPerson.hasErrors() || resultAddress.hasErrors()) {
            return "mainPages/registration";
        }

        newPerson.setPassword(BCrypt.hashpw(newPerson.getPassword(), BCrypt.gensalt(12)));
        newPerson.setRegistered(true);

        Role role = roleService.findRoleById(2);
        newPerson.setRole(role);

        newPerson.setEnabled(true);

        personService.save(newPerson);

        newAddress.setPerson(personService.findPersonByName(newPerson.getName()));
        addressService.save(newAddress);

        return "redirect:/registration?success";

    }
}
