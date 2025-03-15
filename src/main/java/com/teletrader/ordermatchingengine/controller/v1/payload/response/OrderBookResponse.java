package com.teletrader.ordermatchingengine.controller.v1.payload.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderBookResponse {
    private Long stockId;
    private List<OrderResponse> orders;
}
