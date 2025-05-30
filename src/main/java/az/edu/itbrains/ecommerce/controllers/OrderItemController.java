package az.edu.itbrains.ecommerce.controllers;

import az.edu.itbrains.ecommerce.dtos.orderItem.OrderItemDto;
import az.edu.itbrains.ecommerce.models.OrderItem;
import az.edu.itbrains.ecommerce.services.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order-item")
@RequiredArgsConstructor
public class OrderItemController {
    private final OrderItemService orderItemService;
    @PostMapping
    public ResponseEntity<OrderItem> create(@RequestBody OrderItemDto dto) {
        return ResponseEntity.ok(orderItemService.create(dto));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<OrderItem> update(@PathVariable Long id, @RequestBody OrderItemDto dto) {
        return ResponseEntity.ok(orderItemService.update(id, dto));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderItemService.delete(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

}
