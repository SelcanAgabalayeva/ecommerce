package az.edu.itbrains.ecommerce.services.Impls;

import az.edu.itbrains.ecommerce.dtos.category.CategoryCreateDto;
import az.edu.itbrains.ecommerce.dtos.category.CategoryHomeDto;
import az.edu.itbrains.ecommerce.dtos.category.CategoryNavbarDto;
import az.edu.itbrains.ecommerce.dtos.category.CategoryUpdateDto;
import az.edu.itbrains.ecommerce.exceptions.ResourceNotFoundException;
import az.edu.itbrains.ecommerce.models.Category;
import az.edu.itbrains.ecommerce.payloads.ApiResponse;
import az.edu.itbrains.ecommerce.repositories.CategoryRepository;
import az.edu.itbrains.ecommerce.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<CategoryHomeDto> getHomeCategories() {
        List<Category> findCategory=categoryRepository.findByDeletedFalse();
        List<CategoryHomeDto> categories=findCategory.stream().map(category -> modelMapper.map(category,CategoryHomeDto.class)).collect(Collectors.toList());
        return categories;
    }

    @Override
    public List<CategoryNavbarDto> getNavbarCategories() {
        List<Category> findCategory=categoryRepository.findByDeletedFalseAndNavbarTrue();
        List<CategoryNavbarDto> categories=findCategory.stream().map(category -> modelMapper.map(category,CategoryNavbarDto.class)).collect(Collectors.toList());

        return categories;
    }

    @Override
    public CategoryUpdateDto getUpdate(Long id) {
       Category findCategory=categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category","id",id));
       
        return null;
    }

    @Override
    public ApiResponse update(Long id, CategoryUpdateDto categoryUpdateDto) {
        try{
            Category findCategory=categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category","id",id));
            findCategory.setName(categoryUpdateDto.getName());
            findCategory.setPhotoUrl(categoryUpdateDto.getPhotoUrl());
            findCategory.setNavbar(categoryUpdateDto.isNavbar());
            findCategory.setDeleted(categoryUpdateDto.isDeleted());

        return new ApiResponse("Category updated successfully",true, HttpStatus.OK);
    }catch (Exception e){
            return new ApiResponse(e.getMessage(),false,HttpStatus.BAD_REQUEST);
        }



    }

    @Override
    public ApiResponse remove(Long id) {
        Category findCategory=categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category","id",id));
        findCategory.setDeleted(true);
        categoryRepository.save(findCategory);
        return new ApiResponse("Category updated successfully",true, HttpStatus.OK);

    }

    @Override
    public ApiResponse create(CategoryCreateDto categoryCreateDto) {
        try {


            Category category = new Category();
            category.setName(categoryCreateDto.getName());
            category.setPhotoUrl(categoryCreateDto.getPhotoUrl());
            category.setNavbar(categoryCreateDto.isNavbar());
            category.setDeleted(false);
            categoryRepository.save(category);
            return new ApiResponse("Category updated successfully", true, HttpStatus.OK);
        }
        catch (Exception e){
            return new ApiResponse(e.getMessage(),false, HttpStatus.BAD_REQUEST);
        }
    }
}
