package com.teletrader.ordermatchingengine.component.order.api;

import com.teletrader.ordermatchingengine.component.order.command.OrderGetTopTenBuyCommand;
import com.teletrader.ordermatchingengine.component.order.command.OrderGetTopTenSellCommand;
import com.teletrader.ordermatchingengine.component.order.command.OrderSaveCommand;
import com.teletrader.ordermatchingengine.component.order.model.Order;
import com.teletrader.ordermatchingengine.controller.v1.payload.request.OrderSaveRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderApiImpl implements OrderApi{

    private final OrderSaveCommand orderSaveCommand;
    private final OrderGetTopTenSellCommand orderGetTopTenSellCommand;
    private final OrderGetTopTenBuyCommand orderGetTopTenBuyCommand;

    public OrderApiImpl(OrderSaveCommand orderSaveCommand, OrderGetTopTenSellCommand orderGetTopTenSellCommand, OrderGetTopTenBuyCommand orderGetTopTenBuyCommand) {
        this.orderSaveCommand = orderSaveCommand;
        this.orderGetTopTenSellCommand = orderGetTopTenSellCommand;
        this.orderGetTopTenBuyCommand = orderGetTopTenBuyCommand;
    }

    @Override
    public Order saveOrder(OrderSaveRequest orderSaveRequest) {
        return orderSaveCommand.execute(orderSaveRequest);
    }

    @Override
    public List<Order> getTopTenSellOrders(Long stockId) {
        return orderGetTopTenSellCommand.execute(stockId);
    }

    @Override
    public List<Order> getTopTenBuyOrders(Long stockId) {
        return orderGetTopTenBuyCommand.execute(stockId);
    }
}
