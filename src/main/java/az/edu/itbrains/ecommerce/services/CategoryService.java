package az.edu.itbrains.ecommerce.services;

import az.edu.itbrains.ecommerce.dtos.category.CategoryCreateDto;
import az.edu.itbrains.ecommerce.dtos.category.CategoryHomeDto;
import az.edu.itbrains.ecommerce.dtos.category.CategoryNavbarDto;
import az.edu.itbrains.ecommerce.dtos.category.CategoryUpdateDto;
import az.edu.itbrains.ecommerce.payloads.ApiResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryHomeDto> getHomeCategories();

    List<CategoryNavbarDto> getNavbarCategories();

    CategoryUpdateDto getUpdate(Long id);

    ApiResponse update(Long id, CategoryUpdateDto categoryUpdateDto);

    ApiResponse remove(Long id);

    ApiResponse create(CategoryCreateDto categoryCreateDto);
}
