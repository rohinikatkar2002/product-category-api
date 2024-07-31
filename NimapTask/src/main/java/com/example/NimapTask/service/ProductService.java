package com.example.NimapTask.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.NimapTask.entity.Category;
import com.example.NimapTask.entity.Product;
import com.example.NimapTask.repository.CategoryRepository;
import com.example.NimapTask.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Page<Product> getAllProducts(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size));
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product updateProduct(Long id, Product product) {
        return productRepository.findById(id).map(existingProduct -> {
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            if (product.getCategory() != null) {
                Category category = categoryRepository.findById(product.getCategory().getId())
                        .orElseThrow(() -> new RuntimeException("Category not found"));
                existingProduct.setCategory(category);
            }
            return productRepository.save(existingProduct);
        }).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}
