package pl.rasilewicz.restaurant_manager.entities;

import lombok.Getter;

import javax.persistence.*;


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

    @Override
    public String toString() {
        return "Addition{" +
                "id=" + id +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
