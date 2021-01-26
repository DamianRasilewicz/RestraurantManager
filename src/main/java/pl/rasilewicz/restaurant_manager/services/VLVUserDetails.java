package pl.rasilewicz.restaurant_manager.services;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.rasilewicz.restaurant_manager.entities.Person;
import pl.rasilewicz.restaurant_manager.entities.Role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class VLVUserDetails implements UserDetails {

    private Person person;

    public VLVUserDetails(Person person) {
        this.person = person;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Role role = person.getRole();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.getName()));
        return authorities;
    }

    public Long getId() {return person.getId();
    }

    @Override
    public String getPassword() {
        return person.getPassword();
    }

    @Override
    public String getUsername() {
        return person.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return person.isEnabled();
    }

    @Override
    public boolean isAccountNonLocked() {
        return person.isEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return person.isEnabled();
    }

    @Override
    public boolean isEnabled() {
        return person.isEnabled();
    }
}
