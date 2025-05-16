package az.edu.itbrains.ecommerce.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiPayload<T> {
    private boolean success;
    private T data;
    private HttpStatus httpStatus;
}