package pl.rasilewicz.restaurant_manager.entities;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastName;

    private String email;

    @OneToOne()
    @JoinColumn(name = "orders_id")
    private Order order;

    @OneToOne(mappedBy = "user")
    private Address address;
}
