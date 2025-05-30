package az.edu.itbrains.ecommerce.services;

import az.edu.itbrains.ecommerce.dtos.user.UserCreateDto;
import az.edu.itbrains.ecommerce.dtos.user.UserHomeDto;
import az.edu.itbrains.ecommerce.dtos.user.UserUpdateDto;
import az.edu.itbrains.ecommerce.payloads.ApiResponse;

import java.util.List;

public interface UserService {
    List<UserHomeDto> getHomeUser();

    ApiResponse create(UserCreateDto userCreateDto);

    UserUpdateDto getUpdate(Long id);

    ApiResponse update(Long id, UserUpdateDto userUpdateDto);

    ApiResponse remove(Long id);

}
