package az.edu.itbrains.ecommerce.services;

import az.edu.itbrains.ecommerce.dtos.review.ReviewCreateDto;
import az.edu.itbrains.ecommerce.dtos.review.ReviewHomeDto;
import az.edu.itbrains.ecommerce.dtos.review.ReviewUpdateDto;
import az.edu.itbrains.ecommerce.payloads.ApiResponse;

import java.util.List;

public interface ReviewService {
    List<ReviewHomeDto> getHomeReviews();

    ApiResponse create(ReviewCreateDto reviewCreateDto);

    ReviewUpdateDto getUpdate(Long id);

    ApiResponse update(Long id, ReviewUpdateDto reviewUpdateDto);

    ApiResponse remove(Long id);
}
