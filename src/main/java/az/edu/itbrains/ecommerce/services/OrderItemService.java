package az.edu.itbrains.ecommerce.services;

import az.edu.itbrains.ecommerce.dtos.orderItem.OrderItemDto;
import az.edu.itbrains.ecommerce.models.OrderItem;
import az.edu.itbrains.ecommerce.payloads.ApiResponse;

public interface OrderItemService {
    OrderItem create(OrderItemDto dto);

    OrderItem update(Long id, OrderItemDto dto);


    void delete(Long id);
}
