package com.teletrader.ordermatchingengine.controller.v1;

import com.teletrader.ordermatchingengine.component.order.model.Order;
import com.teletrader.ordermatchingengine.component.stock.api.StockApi;
import com.teletrader.ordermatchingengine.component.stock.model.Stock;
import com.teletrader.ordermatchingengine.controller.v1.payload.response.OrderBookResponse;
import com.teletrader.ordermatchingengine.controller.v1.payload.response.OrderResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/stocks")
public class StockController {

    private final StockApi stockApi;

    @GetMapping
    public ResponseEntity<List<Stock>> getAllStocks() {
        return ResponseEntity.ok(stockApi.getAllStocks());
    }

    @GetMapping("/{stockId}")
    public ResponseEntity<Stock> getById(@PathVariable Long stockId) {
        Optional<Stock> stock = stockApi.getById(stockId);
        return stock.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @MessageMapping("/{stockId}/order-book")
    @SendTo("/topic/{stockId}/order-book")
    public ResponseEntity<OrderBookResponse> getOrderBookByStockId(@DestinationVariable Long stockId) {
        List<Order> orderBook = stockApi.getBookOrders(stockId);

        List<OrderResponse> orderResponses = orderBook.stream()
                .map(order -> OrderResponse.builder()
                        .orderId(order.getOrderId())
                        .price(order.getPrice())
                        .quantity(order.getQuantity())
                        .type(order.getType().toString())
                        .build())
                .toList();

        return ResponseEntity.ok(
                OrderBookResponse.builder()
                        .stockId(stockId)
                        .orders(orderResponses)
                        .build()
        );
    }
}
