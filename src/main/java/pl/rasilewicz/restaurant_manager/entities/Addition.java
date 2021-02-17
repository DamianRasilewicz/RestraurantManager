package pl.rasilewicz.restaurant_manager.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Product> products;

    public Addition(String name, String description){
        this.name = name;
        this.description = description;
    }
    public Addition(){};
}
