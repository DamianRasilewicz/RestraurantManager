package pl.rasilewicz.restaurant_manager.entities;

import javax.persistence.*;

@Entity
@Table(name = "main_courses")
public class MainCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private double price;
}
