package com.teletrader.ordermatchingengine.component.order.api;

import com.teletrader.ordermatchingengine.component.order.model.Order;
import com.teletrader.ordermatchingengine.controller.v1.payload.request.OrderSaveRequest;

import java.util.List;

public interface OrderApi {

    Order saveOrder(OrderSaveRequest orderSaveRequest);

    List<Order> getTopTenSellOrders(Long stockId);

    List<Order> getTopTenBuyOrders(Long stockId);
}
