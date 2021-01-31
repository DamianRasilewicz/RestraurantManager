package pl.rasilewicz.restaurant_manager.entities;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "type_of_products")
public class TypeOfProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column()
    private String name;
}
