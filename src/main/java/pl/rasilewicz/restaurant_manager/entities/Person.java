package pl.rasilewicz.restaurant_manager.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, message = "Imię jest za krótkie (Minimum 3 litery)")
    private String firstName;

    @Size(min = 3, message = "Nazwisko jest za krótkie (Minimum 3 litery)")
    private String lastName;

    @NotBlank(message = "Niewłaściwy email")
    @Email(message = "Niewłaściwy email")
    private String email;

    @Pattern(regexp = "\\d{9}", message = "Niewłaściwy numer telefonu")
    private String phoneNumber;

    @Column(columnDefinition = "boolean default false")
    private boolean registered;

    @Column(columnDefinition = "boolean default true")
    private boolean enabled;

    @Size(min = 3, message = "Login jest za krótki (Minimum 3 litery)")
    private String name;

    @Size(min = 3, message = "Hasło jest za krótkie (Minimum 3 litery)")
    private String password;

    @OneToMany(mappedBy = "person")
    private List<Order> orders = new ArrayList<>();

    @OneToOne(mappedBy = "person")
    private Address address;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "person_role", joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Role role;


}