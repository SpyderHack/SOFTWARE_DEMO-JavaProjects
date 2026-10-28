package com.inventory.service;

import com.inventory.dto.ProductRequest;
import com.inventory.exception.InvalidOperationException;
import com.inventory.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    void createAndAdjustStock() {
        Product product = productService.create(
                new ProductRequest("SKU-001", "Widget", "Test widget", 10, new BigDecimal("9.99")));

        Product updated = productService.adjustStock(product.getId(), -4);

        assertThat(updated.getQuantity()).isEqualTo(6);
    }

    @Test
    void adjustStockBelowZeroFails() {
        Product product = productService.create(
                new ProductRequest("SKU-002", "Gadget", null, 2, new BigDecimal("5.00")));

        assertThatThrownBy(() -> productService.adjustStock(product.getId(), -3))
                .isInstanceOf(InvalidOperationException.class);
    }

    @Test
    void duplicateSkuFails() {
        productService.create(new ProductRequest("SKU-003", "Thing", null, 1, BigDecimal.ONE));

        assertThatThrownBy(() -> productService.create(
                new ProductRequest("SKU-003", "Other", null, 1, BigDecimal.ONE)))
                .isInstanceOf(InvalidOperationException.class);
    }
}
