package pl.rasilewicz.restaurant_manager.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    private double price;

    @OneToOne()
    @JoinColumn(name = "type_of_product_id")
    private TypeOfProduct type;

    @ManyToMany
    private List<Addition> additions = new ArrayList<>();

    @ManyToMany
    private List<Ingredient> ingredients = new ArrayList<>();

}
