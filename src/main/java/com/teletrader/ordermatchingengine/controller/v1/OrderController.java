package com.teletrader.ordermatchingengine.controller.v1;

import com.teletrader.ordermatchingengine.component.order.api.OrderApi;
import com.teletrader.ordermatchingengine.component.order.model.Order;
import com.teletrader.ordermatchingengine.component.stock.api.StockApi;
import com.teletrader.ordermatchingengine.controller.v1.payload.request.OrderSaveRequest;
import com.teletrader.ordermatchingengine.controller.v1.payload.response.OrderBookResponse;
import com.teletrader.ordermatchingengine.controller.v1.payload.response.OrderResponse;
import com.teletrader.ordermatchingengine.controller.v1.payload.response.OrderSaveResponse;
import com.teletrader.ordermatchingengine.controller.v1.payload.response.Response;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderApi orderApi;
    private final StockApi stockApi;
    private final SimpMessagingTemplate messagingTemplate;

    @PostMapping
    public ResponseEntity<Response<OrderSaveResponse>> saveOrder(@RequestBody @Valid OrderSaveRequest orderSaveRequest) {
        Order order = orderApi.saveOrder(orderSaveRequest);

        List<Order> orderBook = stockApi.getBookOrders(orderSaveRequest.getStockId());

        List<OrderResponse> orderResponses = orderBook.stream()
                .map(singleOrder -> OrderResponse.builder()
                        .orderId(singleOrder.getOrderId())
                        .price(singleOrder.getPrice())
                        .quantity(singleOrder.getQuantity())
                        .type(singleOrder.getType().toString())
                        .build())
                .toList();

        OrderBookResponse orderBookResponse = OrderBookResponse.builder()
                .stockId(orderSaveRequest.getStockId())
                .orders(orderResponses)
                .build();

        messagingTemplate.convertAndSend("/topic/" + orderSaveRequest.getStockId() + "/order-book", orderBookResponse);

        OrderSaveResponse orderSaveResponse = OrderSaveResponse.builder()
                .orderId(order.getOrderId())
                .price(order.getPrice())
                .quantity(order.getQuantity())
                .type(order.getType().toString())
                .build();

        Response<OrderSaveResponse> response = Response.<OrderSaveResponse>builder()
                .success(true)
                .errors(List.of())
                .data(orderSaveResponse)
                .build();

        return ResponseEntity.ok(response);
    }
}
