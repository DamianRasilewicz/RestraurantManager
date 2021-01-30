package pl.rasilewicz.restaurant_manager.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, message = "Niewłaściwa miejscowość")
    private String city;

    @Size(min = 3, message = "Niewłaściwa ulica")
    private String street;

    @Size(min = 3, message = "Niewłaściwy number budynku/lokalu")
    private String buildingNumber;

    @Size(min = 3, message = "Niewłaściwy kod pocztowy")
    private String postcode;

    @OneToOne()
    @JoinColumn(name = "person_id")
    private Person person;

}
