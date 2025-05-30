package az.edu.itbrains.ecommerce.controllers;

import az.edu.itbrains.ecommerce.dtos.order.OrderCreateDto;
import az.edu.itbrains.ecommerce.dtos.order.OrderHomeDto;
import az.edu.itbrains.ecommerce.dtos.order.OrderUpdateDto;
import az.edu.itbrains.ecommerce.payloads.ApiResponse;
import az.edu.itbrains.ecommerce.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @GetMapping("/home")
    public ResponseEntity<List<OrderHomeDto>> getHomeOrders() {
        List<OrderHomeDto> orders = orderService.getHomeOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> create(@RequestBody OrderCreateDto orderCreateDto){
        ApiResponse response=orderService.create(orderCreateDto);
        return new ResponseEntity<>(response,response.getHttpStatus());
    }
    @GetMapping("/update/{id}")
    public ResponseEntity<OrderUpdateDto> update(@PathVariable Long id) {
        OrderUpdateDto response = orderService.getUpdate(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody OrderUpdateDto orderUpdateDto) {
        ApiResponse response = orderService.update(id, orderUpdateDto);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> remove(@PathVariable Long id) {
        ApiResponse response = orderService.remove(id);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}
