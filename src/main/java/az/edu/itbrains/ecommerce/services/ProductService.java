package az.edu.itbrains.ecommerce.services;

import az.edu.itbrains.ecommerce.dtos.product.ProductCreateDto;
import az.edu.itbrains.ecommerce.dtos.product.ProductHomeDto;
import az.edu.itbrains.ecommerce.dtos.product.ProductUpdateDto;
import az.edu.itbrains.ecommerce.payloads.ApiResponse;

import java.util.List;

public interface ProductService {
    List<ProductHomeDto> getHomeProducts();

    ApiResponse create(ProductCreateDto productCreateDto);

    ProductUpdateDto getUpdate(Long id);

    ApiResponse update(Long id, ProductUpdateDto productUpdateDto);

    ApiResponse remove(Long id);

}
