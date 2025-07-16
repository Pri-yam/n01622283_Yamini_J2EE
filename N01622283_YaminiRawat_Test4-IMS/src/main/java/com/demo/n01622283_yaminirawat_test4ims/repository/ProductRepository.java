package com.demo.n01622283_yaminirawat_test4ims.repository;

import com.demo.n01622283_yaminirawat_test4ims.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
