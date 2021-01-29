package pl.rasilewicz.restaurant_manager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.rasilewicz.restaurant_manager.entities.Person;
import pl.rasilewicz.restaurant_manager.services.PersonServiceImpl;

@Controller
public class RegistrationController {

    private final PersonServiceImpl personService;

    public RegistrationController(PersonServiceImpl personService) {
        this.personService = personService;
    }

    @GetMapping("/register")
    public String registrationForm(Model model){
        Person newPerson = new Person();
        model.addAttribute("newPerson", newPerson);

        return "mainPages/registration";
    }


}
