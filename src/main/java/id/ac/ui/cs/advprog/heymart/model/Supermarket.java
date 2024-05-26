package id.ac.ui.cs.advprog.heymart.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "supermarkets")
public class Supermarket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "balance", nullable = false, columnDefinition = "double default 0")
    private Double balance;

    @OneToMany(mappedBy = "supermarket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

    public Supermarket() {}

    public Supermarket(Long id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    @Override
    public String toString() {
        return "Supermarket{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    public static Supermarket getDefault() {
        return new Supermarket(1L, "not_affiliated", "default_location");
    }
}
