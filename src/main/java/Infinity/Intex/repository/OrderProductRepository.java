package Infinity.Intex.repository;

import Infinity.Intex.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Integer> {
    Optional<OrderProduct> findFirstByFullName(String name);
}