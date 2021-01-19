package pl.rasilewicz.restaurant_manager.entities;

import javax.persistence.*;

@Entity
@Table(name = "drinks")
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private double price;

}
