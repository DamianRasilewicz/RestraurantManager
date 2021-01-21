package pl.rasilewicz.restaurant_manager.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
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

    private double orderCost;

    private Integer numberOfProducts;

}
