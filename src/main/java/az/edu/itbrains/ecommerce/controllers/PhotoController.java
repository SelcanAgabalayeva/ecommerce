package az.edu.itbrains.ecommerce.controllers;

import az.edu.itbrains.ecommerce.dtos.photo.PhotoCreateDto;
import az.edu.itbrains.ecommerce.dtos.photo.PhotoHomeDto;
import az.edu.itbrains.ecommerce.dtos.photo.PhotoUpdateDto;
import az.edu.itbrains.ecommerce.dtos.review.ReviewCreateDto;
import az.edu.itbrains.ecommerce.dtos.review.ReviewHomeDto;
import az.edu.itbrains.ecommerce.dtos.review.ReviewUpdateDto;
import az.edu.itbrains.ecommerce.payloads.ApiResponse;
import az.edu.itbrains.ecommerce.services.PhotoService;
import az.edu.itbrains.ecommerce.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/photo")
@RequiredArgsConstructor
public class PhotoController {
    private final PhotoService photoService;
    @GetMapping("/home")
    public ResponseEntity<List<PhotoHomeDto>> getHomeReview() {
        List<PhotoHomeDto> reviews = photoService.getHomePhotos();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> create(@RequestBody PhotoCreateDto photoCreateDto){
        ApiResponse response=photoService.create(photoCreateDto);
        return new ResponseEntity<>(response,response.getHttpStatus());
    }
    @GetMapping("/update/{id}")
    public ResponseEntity<PhotoUpdateDto> update(@PathVariable Long id) {
        PhotoUpdateDto response = photoService.getUpdate(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody PhotoUpdateDto photoUpdateDto) {
        ApiResponse response = photoService.update(id, photoUpdateDto);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> remove(@PathVariable Long id) {
        ApiResponse response = photoService.remove(id);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}
