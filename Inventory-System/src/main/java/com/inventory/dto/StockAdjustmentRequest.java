package com.inventory.dto;

import jakarta.validation.constraints.NotNull;

/**
 * Positive delta adds stock, negative delta removes stock.
 */
public record StockAdjustmentRequest(@NotNull Integer delta) {
}
