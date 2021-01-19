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

    @Column()
    private double price;

    private double finalPrice;

    @OneToOne()
    @JoinColumn(name = "type_of_products_id")
    private TypeOfProduct type;

    @OneToMany(mappedBy = "product")
    private List<Addition> additions = new ArrayList<>();
}
