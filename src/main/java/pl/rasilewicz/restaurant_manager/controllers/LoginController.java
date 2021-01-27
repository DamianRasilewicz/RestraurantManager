package pl.rasilewicz.restaurant_manager.controllers;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import pl.rasilewicz.restaurant_manager.entities.Person;
import pl.rasilewicz.restaurant_manager.services.VLVPersonDetails;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @GetMapping("/login")

    public String loginForm(Model model) {
        model.addAttribute("person", new Person());
        return "mainPages/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("person") Person person, BindingResult result, HttpSession session) {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        validatePrinciple(authentication.getPrincipal());
        Person loggedInPerson = ((VLVPersonDetails) authentication.getPrincipal()).getUserDetails();

        if (result.hasErrors()) {
            return "redirect:/login?error";
        }

        session.setAttribute("personName", loggedInPerson.getName());
        if (loggedInPerson.getRole().getName().equals("USER")){
            return "redirect:/";
        }else{
            return "redirect:/admin/home";
        }
    }

    private void validatePrinciple(Object principal) {
        if (!(principal instanceof VLVPersonDetails)) {
            throw new  IllegalArgumentException("Principal can not be null!");
        }
    }

    @GetMapping("/logout")
    public String logout(SessionStatus session, HttpSession httpSession) {
        SecurityContextHolder.getContext().setAuthentication(null);
        session.setComplete();
        httpSession.removeAttribute("personLogin");
        return "redirect:/";
    }

}
