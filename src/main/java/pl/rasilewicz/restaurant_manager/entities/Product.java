package pl.rasilewicz.restaurant_manager.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "type_of_product_id")
    private TypeOfProduct type;

    @ManyToMany()
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(name = "products_ingredients", joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredient> ingredients;

    @ManyToMany()
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(name = "products_additions", joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "addition_id"))
    private List<Addition> additions;

    @ManyToMany(mappedBy = "products")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Order> orders = new ArrayList<>();
}
