package pl.rasilewicz.restaurant_manager.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    private double price;


    private double finalPrice;

    private int quantity;

    @OneToOne()
    @JoinColumn(name = "type_of_products_id")
    private TypeOfProduct type;

    @ManyToMany
    private List<Addition> additions = new ArrayList<>();

    @ManyToMany(mappedBy = "products")
    private List<Order> orders = new ArrayList<>();

    @ManyToMany
    private List<Ingradient> ingradients = new ArrayList<>();


}
