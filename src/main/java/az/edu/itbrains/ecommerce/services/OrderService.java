package az.edu.itbrains.ecommerce.services;

import az.edu.itbrains.ecommerce.dtos.order.OrderCreateDto;
import az.edu.itbrains.ecommerce.dtos.order.OrderHomeDto;
import az.edu.itbrains.ecommerce.dtos.order.OrderUpdateDto;
import az.edu.itbrains.ecommerce.payloads.ApiResponse;

import java.util.List;

public interface OrderService {
    List<OrderHomeDto> getHomeOrders();

    ApiResponse create(OrderCreateDto orderCreateDto);

    OrderUpdateDto getUpdate(Long id);

    ApiResponse update(Long id, OrderUpdateDto orderUpdateDto);

    ApiResponse remove(Long id);

}