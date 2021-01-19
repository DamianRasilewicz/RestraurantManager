package pl.rasilewicz.restaurant_manager.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "users_id")
    private User user;

    @OneToOne(mappedBy = "order")
    private Comment comment;

    @ManyToMany
    private List<Product> products = new ArrayList<>();

}
