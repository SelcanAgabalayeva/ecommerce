package az.edu.itbrains.ecommerce.dtos.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryHomeDto {
    private Long id;
    private String name;
    private String photoUrl;
    private String productCount;
}
