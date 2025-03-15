package com.teletrader.ordermatchingengine.component.stock.api;

import com.teletrader.ordermatchingengine.component.order.model.Order;
import com.teletrader.ordermatchingengine.component.stock.model.Stock;

import java.util.List;
import java.util.Optional;

public interface StockApi {

    Optional<Stock> getById(Long stockId);

    List<Stock> getAllStocks();

    List<Order> getBookOrders(Long stockId);
}
