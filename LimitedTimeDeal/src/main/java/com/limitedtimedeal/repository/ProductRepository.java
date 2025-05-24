package com.limitedtimedeal.repository;

import com.limitedtimedeal.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
