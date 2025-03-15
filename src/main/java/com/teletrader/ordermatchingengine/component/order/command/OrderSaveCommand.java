package com.teletrader.ordermatchingengine.component.order.command;

import com.teletrader.ordermatchingengine.component.order.enums.OrderTypeEnum;
import com.teletrader.ordermatchingengine.component.order.model.Order;
import com.teletrader.ordermatchingengine.component.order.repository.OrderRepository;
import com.teletrader.ordermatchingengine.component.stock.model.Stock;
import com.teletrader.ordermatchingengine.component.stock.repository.StockRepository;
import com.teletrader.ordermatchingengine.component.user.api.UserApi;
import com.teletrader.ordermatchingengine.component.user.model.User;
import com.teletrader.ordermatchingengine.controller.v1.payload.request.OrderSaveRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderSaveCommand {
    private final OrderRepository orderRepository;
    private final UserApi userApi;
    private final StockRepository stockRepository;
    //private final StockApi stockApi;

    public Order execute(OrderSaveRequest orderSaveRequest) {
        User user = userApi.getById(orderSaveRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Stock stock = stockRepository.findById(orderSaveRequest.getStockId())
                .orElseThrow(() -> new RuntimeException("Stock not found"));

        Order order = Order.builder()
                .user(user)
                .stock(stock)
                .quantity(orderSaveRequest.getQuantity())
                .price(orderSaveRequest.getPrice())
                .type(OrderTypeEnum.valueOf(orderSaveRequest.getType()))
                .build();

        return orderRepository.save(order);
    }
}
