package pl.rasilewicz.restaurant_manager.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastName;

    private String email;

    private String phoneNumber;

    private boolean registered = false;

    private String login;

    private String password;

    @OneToOne(mappedBy = "person")
    private Order order;

    @OneToOne(mappedBy = "person")
    private Address address;

}
