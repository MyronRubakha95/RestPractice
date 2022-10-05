package com.example.Product.service;

import com.example.Product.entity.Product;
import com.example.Product.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductService {

    private ProductRepo productRepo;

    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Iterable<Product> getProduct() {
        return productRepo.findAll();

    }

    public Product productId(Long id) {
        return productRepo.findById(id).orElse(null);

    }

    public Product addProduct(Product product) {
        productRepo.save(product);
        return product;
    }

    public Product update(Product product, Long id) {

        Product product1 = productRepo.findById(id).orElse(null);
        product1.setPrice(product.getPrice());
        product1.setName(product.getName());
        productRepo.save(product1);
        return product1;
    }

    public Product deleteProduct(Long productId) {
        isFound(productId);
        productRepo.deleteById(productId);
        return null;
    }

    public void isFound(Long productId) {
        if (!productRepo.existsById(productId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }


}