package pl.rasilewicz.restaurant_manager.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
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

    private LocalDate orderDate;

    private LocalTime orderTime;

    @ManyToOne
    private Person person;

    private String comment;

    @ManyToMany
    private List<Product> products = new ArrayList<>();

    private double orderCost;

    private Integer numberOfProducts;

}
