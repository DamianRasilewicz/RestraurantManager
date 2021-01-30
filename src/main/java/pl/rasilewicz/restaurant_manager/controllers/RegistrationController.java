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

    @GetMapping("/registration/person")
    public String registrationFormPerson(Model model){
        Person newPerson = new Person();
        model.addAttribute("newPerson", newPerson);

        return "mainPages/registrationPerson";
    }

    @PostMapping("/registration/person")
    public String registeredPerson(@ModelAttribute("newPerson") @Valid Person newPerson, BindingResult result, RedirectAttributes redirectAttributes){
        if (result.hasErrors()) {
            return "mainPages/registrationPerson";
        }

        newPerson.setPassword(BCrypt.hashpw(newPerson.getPassword(), BCrypt.gensalt(12)));
        newPerson.setRegistered(true);

        Role role = roleService.findRoleById(2);
        newPerson.setRole(role);

        newPerson.setEnabled(true);

        personService.save(newPerson);

        redirectAttributes.addAttribute("newPersonName", newPerson.getName());
        return "redirect:/registration/address";
    }

    @GetMapping("/registration/address")
    public String registrationFormAddress(Model model, @RequestParam String newPersonName, RedirectAttributes redirectAttributes){
        Address newAddress = new Address();
        model.addAttribute("newAddress", newAddress);

        model.addAttribute("newPersonName", newPersonName);

        return "mainPages/registrationAddress";
    }

    @PostMapping("/registration/address")
    public String registeredAddress(@ModelAttribute("newAddress") @Valid Address newAddress, BindingResult result, @ModelAttribute("newPersonName") String newPersonName){
        if (result.hasErrors()) {
            return "mainPages/registrationAddress";
        }

        newAddress.setPerson(personService.findPersonByName(newPersonName));
        addressService.save(newAddress);

        return "redirect:/registration/address?success";
    }
}
