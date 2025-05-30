package az.edu.itbrains.ecommerce.repositories;

import az.edu.itbrains.ecommerce.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

}
