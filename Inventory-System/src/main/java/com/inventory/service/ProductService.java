package com.inventory.service;

import com.inventory.dto.ProductRequest;
import com.inventory.exception.InvalidOperationException;
import com.inventory.exception.ResourceNotFoundException;
import com.inventory.model.Product;
import com.inventory.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<Product> findLowStock(int threshold) {
        return productRepository.findByQuantityLessThanEqual(threshold);
    }

    public Product create(ProductRequest request) {
        if (productRepository.existsBySku(request.sku())) {
            throw new InvalidOperationException("SKU already exists: " + request.sku());
        }
        Product product = new Product();
        apply(product, request);
        return productRepository.save(product);
    }

    public Product update(Long id, ProductRequest request) {
        Product product = findById(id);
        if (!product.getSku().equals(request.sku()) && productRepository.existsBySku(request.sku())) {
            throw new InvalidOperationException("SKU already exists: " + request.sku());
        }
        apply(product, request);
        return productRepository.save(product);
    }

    public Product adjustStock(Long id, int delta) {
        Product product = findById(id);
        int newQuantity = product.getQuantity() + delta;
        if (newQuantity < 0) {
            throw new InvalidOperationException(
                    "Insufficient stock for product " + id + ": available " + product.getQuantity());
        }
        product.setQuantity(newQuantity);
        return productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.delete(findById(id));
    }

    private void apply(Product product, ProductRequest request) {
        product.setSku(request.sku());
        product.setName(request.name());
        product.setDescription(request.description());
        product.setQuantity(request.quantity());
        product.setPrice(request.price());
    }
}
