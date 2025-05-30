package az.edu.itbrains.ecommerce.controllers;

import az.edu.itbrains.ecommerce.dtos.category.CategoryCreateDto;
import az.edu.itbrains.ecommerce.dtos.category.CategoryHomeDto;
import az.edu.itbrains.ecommerce.dtos.category.CategoryNavbarDto;
import az.edu.itbrains.ecommerce.dtos.category.CategoryUpdateDto;
import az.edu.itbrains.ecommerce.payloads.ApiResponse;
import az.edu.itbrains.ecommerce.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/home")
    public ResponseEntity<List<CategoryHomeDto>> getHomeCategories() {
        List<CategoryHomeDto> categories = categoryService.getHomeCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> create(@RequestBody CategoryCreateDto categoryCreateDto){
        ApiResponse response=categoryService.create(categoryCreateDto);
        return new ResponseEntity<>(response,response.getHttpStatus());
    }

    @GetMapping("/navbar")
    public ResponseEntity<List<CategoryNavbarDto>> getNavbarCategories() {
        List<CategoryNavbarDto> categories = categoryService.getNavbarCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/update/{id}")
    public ResponseEntity<CategoryUpdateDto> update(@PathVariable Long id) {
        CategoryUpdateDto response = categoryService.getUpdate(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody CategoryUpdateDto categoryUpdateDto) {
        ApiResponse response = categoryService.update(id, categoryUpdateDto);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> remove(@PathVariable Long id) {
        ApiResponse response = categoryService.remove(id);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

}
