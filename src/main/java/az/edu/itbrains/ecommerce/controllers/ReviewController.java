package az.edu.itbrains.ecommerce.controllers;

import az.edu.itbrains.ecommerce.dtos.product.ProductCreateDto;
import az.edu.itbrains.ecommerce.dtos.product.ProductHomeDto;
import az.edu.itbrains.ecommerce.dtos.product.ProductUpdateDto;
import az.edu.itbrains.ecommerce.dtos.review.ReviewCreateDto;
import az.edu.itbrains.ecommerce.dtos.review.ReviewHomeDto;
import az.edu.itbrains.ecommerce.dtos.review.ReviewUpdateDto;
import az.edu.itbrains.ecommerce.payloads.ApiResponse;
import az.edu.itbrains.ecommerce.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    @GetMapping("/home")
    public ResponseEntity<List<ReviewHomeDto>> getHomeReview() {
        List<ReviewHomeDto> reviews = reviewService.getHomeReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> create(@RequestBody ReviewCreateDto reviewCreateDto){
        ApiResponse response=reviewService.create(reviewCreateDto);
        return new ResponseEntity<>(response,response.getHttpStatus());
    }
    @GetMapping("/update/{id}")
    public ResponseEntity<ReviewUpdateDto> update(@PathVariable Long id) {
        ReviewUpdateDto response = reviewService.getUpdate(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody ReviewUpdateDto reviewUpdateDto) {
        ApiResponse response = reviewService.update(id, reviewUpdateDto);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> remove(@PathVariable Long id) {
        ApiResponse response = reviewService.remove(id);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}
