package az.edu.itbrains.ecommerce.controllers;

import az.edu.itbrains.ecommerce.dtos.product.ProductUpdateDto;
import az.edu.itbrains.ecommerce.dtos.review.ReviewCreateDto;
import az.edu.itbrains.ecommerce.dtos.review.ReviewHomeDto;
import az.edu.itbrains.ecommerce.dtos.review.ReviewUpdateDto;
import az.edu.itbrains.ecommerce.dtos.user.UserCreateDto;
import az.edu.itbrains.ecommerce.dtos.user.UserHomeDto;
import az.edu.itbrains.ecommerce.dtos.user.UserUpdateDto;
import az.edu.itbrains.ecommerce.models.User;
import az.edu.itbrains.ecommerce.payloads.ApiResponse;
import az.edu.itbrains.ecommerce.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/home")
    public ResponseEntity<List<UserHomeDto>> getHomeUser() {

        List<UserHomeDto> user = userService.getHomeUser();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> create(@RequestBody UserCreateDto userCreateDto){
        ApiResponse response=userService.create(userCreateDto);
        return new ResponseEntity<>(response,response.getHttpStatus());
    }
    @GetMapping("/update/{id}")
    public ResponseEntity<UserUpdateDto> update(@PathVariable Long id) {
        UserUpdateDto response = userService.getUpdate(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody UserUpdateDto userUpdateDto) {
        ApiResponse response = userService.update(id, userUpdateDto);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> remove(@PathVariable Long id) {
        ApiResponse response = userService.remove(id);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }


}
