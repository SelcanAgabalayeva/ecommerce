package az.edu.itbrains.ecommerce.controllers;

import az.edu.itbrains.ecommerce.dtos.category.CategoryCreateDto;
import az.edu.itbrains.ecommerce.dtos.category.CategoryHomeDto;
import az.edu.itbrains.ecommerce.dtos.category.CategoryUpdateDto;
import az.edu.itbrains.ecommerce.dtos.product.ProductCreateDto;
import az.edu.itbrains.ecommerce.dtos.product.ProductHomeDto;
import az.edu.itbrains.ecommerce.dtos.product.ProductUpdateDto;
import az.edu.itbrains.ecommerce.payloads.ApiResponse;
import az.edu.itbrains.ecommerce.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @GetMapping("/home")
    public ResponseEntity<List<ProductHomeDto>> getHomeProducts() {
        List<ProductHomeDto> products = productService.getHomeProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> create(@RequestBody ProductCreateDto productCreateDto){
        ApiResponse response=productService.create(productCreateDto);
        return new ResponseEntity<>(response,response.getHttpStatus());
    }
    @GetMapping("/update/{id}")
    public ResponseEntity<ProductUpdateDto> update(@PathVariable Long id) {
        ProductUpdateDto response = productService.getUpdate(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody ProductUpdateDto productUpdateDto) {
        ApiResponse response = productService.update(id, productUpdateDto);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> remove(@PathVariable Long id) {
        ApiResponse response = productService.remove(id);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}
