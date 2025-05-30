package az.edu.itbrains.ecommerce.dtos.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewHomeDto {
    private Long id;
    private String comment;
    private String rank;
    private LocalDateTime createDate;
    private String deleted;
    private String username;
    private Long productId;
}
