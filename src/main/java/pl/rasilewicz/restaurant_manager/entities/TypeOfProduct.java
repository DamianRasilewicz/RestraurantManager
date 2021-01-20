package pl.rasilewicz.restaurant_manager.entities;

import javax.persistence.*;

@Entity
@Table(name = "type_of_products")
public class TypeOfProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column()
    private String name;
}
