package az.edu.itbrains.ecommerce.services.Impls;

import az.edu.itbrains.ecommerce.dtos.user.UserCreateDto;
import az.edu.itbrains.ecommerce.dtos.user.UserHomeDto;
import az.edu.itbrains.ecommerce.dtos.user.UserUpdateDto;
import az.edu.itbrains.ecommerce.exceptions.ResourceNotFoundException;
import az.edu.itbrains.ecommerce.models.Review;
import az.edu.itbrains.ecommerce.models.User;
import az.edu.itbrains.ecommerce.payloads.ApiResponse;
import az.edu.itbrains.ecommerce.repositories.UserRepository;
import az.edu.itbrains.ecommerce.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<UserHomeDto> getHomeUser() {
        List<User> users = userRepository.findAll();

        List<UserHomeDto> userDtos = users.stream()
                .map(user -> modelMapper.map(user, UserHomeDto.class))
                .collect(Collectors.toList());

        return userDtos;
    }

    @Override
    public ApiResponse create(UserCreateDto userCreateDto) {
        try {


            User user = new User();
           user.setEmail(userCreateDto.getEmail());
           user.setPassword(userCreateDto.getPassword());
           user.setFirstName(userCreateDto.getFirstName());
           user.setLastName(userCreateDto.getLastName());


            userRepository.save(user);
            return new ApiResponse("User created successfully", true, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ApiResponse(e.getMessage(),false, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public UserUpdateDto getUpdate(Long id) {
        User user=userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id",id));
        UserUpdateDto userUpdateDto =modelMapper.map(user,UserUpdateDto.class);
        return userUpdateDto;
    }

    @Override
    public ApiResponse update(Long id, UserUpdateDto userUpdateDto) {
        try{
            User user = new User();
            user.setEmail(userUpdateDto.getEmail());
            user.setPassword(userUpdateDto.getPassword());
            user.setFirstName(userUpdateDto.getFirstName());
            user.setLastName(userUpdateDto.getLastName());
            userRepository.save(user);
            return new ApiResponse("User updated successfully",true, HttpStatus.OK);
        }catch (Exception e){
            return new ApiResponse(e.getMessage(),false,HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ApiResponse remove(Long id) {
        User findUser =userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id",id));
        userRepository.save(findUser);
        return new ApiResponse("User updated successfully",true, HttpStatus.OK);

    }
}
