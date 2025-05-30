package az.edu.itbrains.ecommerce.services.Impls;

import az.edu.itbrains.ecommerce.dtos.order.OrderCreateDto;
import az.edu.itbrains.ecommerce.dtos.order.OrderHomeDto;
import az.edu.itbrains.ecommerce.dtos.order.OrderUpdateDto;
import az.edu.itbrains.ecommerce.exceptions.ResourceNotFoundException;
import az.edu.itbrains.ecommerce.models.Order;
import az.edu.itbrains.ecommerce.payloads.ApiResponse;
import az.edu.itbrains.ecommerce.repositories.OrderRepository;
import az.edu.itbrains.ecommerce.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<OrderHomeDto> getHomeOrders() {
        List<Order> findOrder =orderRepository.findAll();
        List<OrderHomeDto> orders= findOrder.stream().map(order -> modelMapper.map(order,OrderHomeDto.class)).collect(Collectors.toList());
        return orders;
    }

    @Override
    public ApiResponse create(OrderCreateDto orderCreateDto) {
        try {


            Order order = new Order();
            order.setOrderDate(orderCreateDto.getOrderDate());


            orderRepository.save(order);
            return new ApiResponse("Order created successfully", true, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ApiResponse(e.getMessage(),false, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public OrderUpdateDto getUpdate(Long id) {
        Order findOrder =orderRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Order","id",id));
        OrderUpdateDto orderUpdateDto =modelMapper.map(findOrder,OrderUpdateDto.class);
        return orderUpdateDto;
    }

    @Override
    public ApiResponse update(Long id, OrderUpdateDto orderUpdateDto) {
        try{
            Order findOrder=orderRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Order","id",id));
            findOrder.setOrderDate(orderUpdateDto.getOrderDate());
            orderRepository.save(findOrder);
            return new ApiResponse("Order updated successfully",true, HttpStatus.OK);
        }catch (Exception e){
            return new ApiResponse(e.getMessage(),false,HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ApiResponse remove(Long id) {
        Order findOrder =orderRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Order","id",id));
        orderRepository.save(findOrder);
        return new ApiResponse("Order updated successfully",true, HttpStatus.OK);
    }
}

