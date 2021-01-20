package pl.rasilewicz.restaurant_manager.entities;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "additions")
public class Addition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column()
    private double price;

    private String name;

    private String description;

    @ManyToMany(mappedBy = "additions")
    private List<Product> products = new ArrayList<>();



}
