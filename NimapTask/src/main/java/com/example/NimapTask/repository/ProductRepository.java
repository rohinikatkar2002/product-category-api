package com.example.NimapTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.NimapTask.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
