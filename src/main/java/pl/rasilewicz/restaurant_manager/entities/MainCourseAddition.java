package pl.rasilewicz.restaurant_manager.entities;

import javax.persistence.*;

@Entity
@Table(name = "main_course_additions")
public class MainCourseAddition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private double price;

    @ManyToOne
    private MainCourse mainCourse;
}
