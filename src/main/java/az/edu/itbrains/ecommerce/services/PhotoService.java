package az.edu.itbrains.ecommerce.services;

import az.edu.itbrains.ecommerce.dtos.photo.PhotoCreateDto;
import az.edu.itbrains.ecommerce.dtos.photo.PhotoHomeDto;
import az.edu.itbrains.ecommerce.dtos.photo.PhotoUpdateDto;
import az.edu.itbrains.ecommerce.payloads.ApiResponse;

import java.util.List;

public interface PhotoService {
    List<PhotoHomeDto> getHomePhotos();

    ApiResponse create(PhotoCreateDto photoCreateDto);

    PhotoUpdateDto getUpdate(Long id);

    ApiResponse update(Long id, PhotoUpdateDto photoUpdateDto);

    ApiResponse remove(Long id);
}
