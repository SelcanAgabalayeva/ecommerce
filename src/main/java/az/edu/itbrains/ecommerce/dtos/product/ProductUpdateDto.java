package az.edu.itbrains.ecommerce.dtos.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateDto {
    private String name;
    private Double price;
    private Double discountPrice;
    private String shortDescription;
    private String description;
    private String information;
    private Long categoryId;
}
