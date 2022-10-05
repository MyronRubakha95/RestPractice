package com.example.Product.controller;

import com.example.Product.entity.Product;
import com.example.Product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Product>> getProduct() {
        var list = productService.getProduct();
        String version = String.valueOf(list.hashCode());
        return ResponseEntity.ok()
                .eTag(version)
                .body(list);

    }

    @GetMapping("{id}")
    public ResponseEntity<Product> productById(@PathVariable Long id) {
        Product product = productService.productId(id);
        String version = Long.toString(product.getVersion());
        return ResponseEntity.ok()
                .eTag(version)
                .body(product);
    }

    @PostMapping
    public Product add(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PutMapping(value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Product update(@RequestBody Product product, @PathVariable Long id) {

        return productService.update(product, id);
    }

    @DeleteMapping("{id}")
    public Product delete(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }

}