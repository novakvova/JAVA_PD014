package models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="tbl_products")
public class Product {
    @Id
    @GeneratedValue
    private int id;
    @Column(length = 500, nullable = false)
    private String name;
    private double price;
    @Column(length = 4000)
    private String description;
    @ManyToOne
    @JoinColumn(name="category_id", nullable = false)
    private Category category;
}
