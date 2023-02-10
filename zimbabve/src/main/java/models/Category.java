package models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="tbl_categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 500, nullable = false)
    private String name;
    @Column(length = 500)
    private String image;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
    public Category() {
        products=new ArrayList<>();
    }
}
