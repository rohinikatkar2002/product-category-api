package com.example.NimapTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.NimapTask.entity.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
