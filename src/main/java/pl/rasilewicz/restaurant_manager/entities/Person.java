package pl.rasilewicz.restaurant_manager.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastName;

    private String email;

    private String phoneNumber;

    @Column(columnDefinition = "boolean default false")
    private boolean registered;

    @Column(columnDefinition = "boolean default true")
    private boolean enabled;

    private String login;

    private String password;

    @OneToOne(mappedBy = "person")
    private Order order;

    @OneToOne(mappedBy = "person")
    private Address address;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "person_role", joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Role role;

}