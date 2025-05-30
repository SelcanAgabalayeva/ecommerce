package az.edu.itbrains.ecommerce.services.Impls;

import az.edu.itbrains.ecommerce.dtos.orderItem.OrderItemDto;
import az.edu.itbrains.ecommerce.exceptions.ResourceNotFoundException;
import az.edu.itbrains.ecommerce.models.Order;
import az.edu.itbrains.ecommerce.models.OrderItem;
import az.edu.itbrains.ecommerce.models.Product;
import az.edu.itbrains.ecommerce.repositories.OrderItemRepository;
import az.edu.itbrains.ecommerce.repositories.OrderRepository;
import az.edu.itbrains.ecommerce.repositories.ProductRepository;
import az.edu.itbrains.ecommerce.services.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    @Override
    public OrderItem create(OrderItemDto dto) {
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Order order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        OrderItem item = new OrderItem();
        item.setProduct(product);
        item.setOrder(order);

        return orderItemRepository.save(item);
    }

    @Override
    public OrderItem update(Long id, OrderItemDto dto) {
        OrderItem item = orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderItem not found"));

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Order order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        item.setProduct(product);
        item.setOrder(order);

        return orderItemRepository.save(item);
    }

    @Override
    public void delete(Long id) {

            if (!orderItemRepository.existsById(id)) {
                throw new RuntimeException("OrderItem not found with id: " + id);
            }
            orderItemRepository.deleteById(id);
        }

    }




