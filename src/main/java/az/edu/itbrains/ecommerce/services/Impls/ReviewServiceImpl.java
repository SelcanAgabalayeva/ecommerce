package az.edu.itbrains.ecommerce.services.Impls;

import az.edu.itbrains.ecommerce.dtos.review.ReviewCreateDto;
import az.edu.itbrains.ecommerce.dtos.review.ReviewHomeDto;
import az.edu.itbrains.ecommerce.dtos.review.ReviewUpdateDto;
import az.edu.itbrains.ecommerce.exceptions.ResourceNotFoundException;
import az.edu.itbrains.ecommerce.models.Product;
import az.edu.itbrains.ecommerce.models.Review;
import az.edu.itbrains.ecommerce.payloads.ApiResponse;
import az.edu.itbrains.ecommerce.repositories.ProductRepository;
import az.edu.itbrains.ecommerce.repositories.ReviewRepository;
import az.edu.itbrains.ecommerce.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;
    @Override
    public List<ReviewHomeDto> getHomeReviews() {
        List<Review> findReview =reviewRepository.findAll();
        List<ReviewHomeDto> reviews = findReview.stream().map(review -> modelMapper.map(review,ReviewHomeDto.class)).collect(Collectors.toList());
        return reviews;
    }

    @Override
    public ApiResponse create(ReviewCreateDto reviewCreateDto) {
        try {
            Optional<Product> optionalProduct = productRepository.findById(reviewCreateDto.getProductId());
            if (optionalProduct.isEmpty()) {
                return new ApiResponse("Product not found", false, HttpStatus.NOT_FOUND);
            }

            Review review = new Review();
            review.setComment(reviewCreateDto.getComment());
            review.setRank(reviewCreateDto.getRank());
            review.setCreateDate(reviewCreateDto.getCreateDate());
            review.setDeleted(reviewCreateDto.getDeleted());
            review.setProduct(optionalProduct.get());

            reviewRepository.save(review);
            return new ApiResponse("Review created successfully", true, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ApiResponse(e.getMessage(),false, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ReviewUpdateDto getUpdate(Long id) {
        Review findReview =reviewRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Review","id",id));
        ReviewUpdateDto reviewUpdateDto =modelMapper.map(findReview,ReviewUpdateDto.class);
        return reviewUpdateDto;
    }

    @Override
    public ApiResponse update(Long id, ReviewUpdateDto reviewUpdateDto) {
        try{
            Review findReview =reviewRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Review","id",id));
            findReview.setRank(reviewUpdateDto.getRank());
            findReview.setComment(reviewUpdateDto.getComment());
            findReview.setCreateDate(reviewUpdateDto.getCreateDate());
            findReview.setDeleted(reviewUpdateDto.getDeleted());
            reviewRepository.save(findReview);
            return new ApiResponse("Review updated successfully",true, HttpStatus.OK);
        }catch (Exception e){
            return new ApiResponse(e.getMessage(),false,HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ApiResponse remove(Long id) {
        Review findReview =reviewRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Review","id",id));
        reviewRepository.save(findReview);
        return new ApiResponse("Review updated successfully",true, HttpStatus.OK);
    }
}
