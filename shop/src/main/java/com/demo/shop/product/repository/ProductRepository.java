package com.demo.shop.product.repository;

import com.demo.shop.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p Where p.costCode = ?1")
    Optional<Product> findProductByCostCode(String costCode);
}
