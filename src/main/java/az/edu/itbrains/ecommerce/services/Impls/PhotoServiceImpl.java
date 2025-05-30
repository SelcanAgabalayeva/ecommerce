package az.edu.itbrains.ecommerce.services.Impls;

import az.edu.itbrains.ecommerce.dtos.photo.PhotoCreateDto;
import az.edu.itbrains.ecommerce.dtos.photo.PhotoHomeDto;
import az.edu.itbrains.ecommerce.dtos.photo.PhotoUpdateDto;
import az.edu.itbrains.ecommerce.dtos.product.ProductHomeDto;
import az.edu.itbrains.ecommerce.exceptions.ResourceNotFoundException;
import az.edu.itbrains.ecommerce.models.Photo;
import az.edu.itbrains.ecommerce.payloads.ApiResponse;
import az.edu.itbrains.ecommerce.repositories.PhotoRepository;
import az.edu.itbrains.ecommerce.services.PhotoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {
    private final PhotoRepository photoRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<PhotoHomeDto> getHomePhotos() {
        List<Photo> findProduct=photoRepository.findAll();
        List<PhotoHomeDto> photos =findProduct.stream().map(photo -> modelMapper.map(photo,PhotoHomeDto.class)).collect(Collectors.toList());
        return photos;
    }

    @Override
    public ApiResponse create(PhotoCreateDto photoCreateDto) {
        try {


            Photo photo = new Photo();
            photo.setPhotoUrl(photoCreateDto.getPhotoUrl());

            photoRepository.save(photo);
            return new ApiResponse("Photo created successfully", true, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ApiResponse(e.getMessage(),false, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public PhotoUpdateDto getUpdate(Long id) {
        Photo findPhoto =photoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Photo","id",id));
        PhotoUpdateDto photoUpdateDto =modelMapper.map(findPhoto,PhotoUpdateDto.class);
        return photoUpdateDto;
    }

    @Override
    public ApiResponse update(Long id, PhotoUpdateDto photoUpdateDto) {
        try{
            Photo findPhoto =photoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Photo","id",id));
            findPhoto.setPhotoUrl(photoUpdateDto.getPhotoUrl());

            photoRepository.save(findPhoto);
            return new ApiResponse("Photo updated successfully",true, HttpStatus.OK);
        }catch (Exception e){
            return new ApiResponse(e.getMessage(),false,HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ApiResponse remove(Long id) {
        Photo findPhoto =photoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Photo","id",id));
        photoRepository.save(findPhoto);
        return new ApiResponse("Photo updated successfully",true, HttpStatus.OK);
    }
}
