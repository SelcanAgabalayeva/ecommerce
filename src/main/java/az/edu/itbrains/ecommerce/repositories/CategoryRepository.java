package az.edu.itbrains.ecommerce.repositories;

import az.edu.itbrains.ecommerce.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findByDeletedFalse();

    List<Category> findByDeletedFalseAndNavbarTrue();
}
