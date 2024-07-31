package com.example.NimapTask.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.NimapTask.entity.Product;
import com.example.NimapTask.service.ProductService;

@RestController
@RequestMapping("/api/products")
@Validated
public class ProductController {
	 @Autowired
	    private ProductService productService;

	    @GetMapping
	    public ResponseEntity<Page<Product>> getAllProducts(@RequestParam(defaultValue = "0") int page,
	                                                        @RequestParam(defaultValue = "10") int size) {
	        return new ResponseEntity<>(productService.getAllProducts(page, size), HttpStatus.OK);
	    }

	    @PostMapping
	    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
	        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
	        Optional<Product> product = productService.getProductById(id);
	        return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
	                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody Product product) {
	        return new ResponseEntity<>(productService.updateProduct(id, product), HttpStatus.OK);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
	        productService.deleteProduct(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
}
