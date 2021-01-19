package pl.rasilewicz.restaurant_manager.entities;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String description;

    @OneToOne()
    @JoinColumn(name = "orders_id")
    private Order order;
}
