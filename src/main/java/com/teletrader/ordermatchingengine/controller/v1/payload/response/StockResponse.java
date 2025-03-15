package com.teletrader.ordermatchingengine.controller.v1.payload.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockResponse {
    private Long stockId;
    private String symbol;
}
