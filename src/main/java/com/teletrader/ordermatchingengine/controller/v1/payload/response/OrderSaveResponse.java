package com.teletrader.ordermatchingengine.controller.v1.payload.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderSaveResponse {
    private Long orderId;
    private Double price;
    private Integer quantity;
    private String type;
}
