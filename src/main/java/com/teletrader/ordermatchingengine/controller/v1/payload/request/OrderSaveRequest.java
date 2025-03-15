package com.teletrader.ordermatchingengine.controller.v1.payload.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class OrderSaveRequest {

    @NotNull(message = "UserID is required")
    private Long userId;

    @NotNull(message = "StockID is required")
    private Long stockId;

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be greater than zero")
    private Integer quantity;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be at least 0.01")
    private Double price;

    @NotNull(message = "Type is required")
    private String type;
}
