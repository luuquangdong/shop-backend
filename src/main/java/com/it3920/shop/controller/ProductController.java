package com.it3920.shop.controller;

import com.it3920.shop.entity.Product;
import com.it3920.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<?> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable String id){
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@Valid @RequestBody Product product){
        Product newProduct = productService.create(product);
        return ResponseEntity.ok(newProduct);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@Valid @RequestBody Product product, @PathVariable String id){
        Product updatedProduct = productService.update(product, id);
        return ResponseEntity.ok(updatedProduct);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        Product deletedProduct = productService.delete(id);
        return ResponseEntity.ok(deletedProduct);
    }
}
