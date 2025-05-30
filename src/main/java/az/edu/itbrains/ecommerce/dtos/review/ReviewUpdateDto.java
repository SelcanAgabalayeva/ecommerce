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
public class ReviewUpdateDto {

    private String comment;
    private String rank;
    private LocalDateTime createDate;
    private String deleted;
    private Long productId;
}
