package az.edu.itbrains.ecommerce.repositories;

import az.edu.itbrains.ecommerce.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {
}
