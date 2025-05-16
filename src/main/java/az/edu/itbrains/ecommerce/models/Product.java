package az.edu.itbrains.ecommerce.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private Double discountPrice;
    private String shortDescription;
    private String description;
    private String information;

    @ManyToOne
    private Category category;
    @OneToMany(mappedBy = "product")
    private List<Photo> photos=new ArrayList<>();
    @OneToMany(mappedBy = "product")
    private List<Review> reviews=new ArrayList<>();
}
