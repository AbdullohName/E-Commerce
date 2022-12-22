package Infinity.Intex.repository;

import Infinity.Intex.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategoryId(Integer id);
    List<Product> findByStatus(String status);
    Page<Product> findAllBy(Pageable pageable);
    Optional<Product> findFirstByNameRu(String name);
}