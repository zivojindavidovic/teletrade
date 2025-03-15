package com.teletrader.ordermatchingengine.component.stock.api;

import com.teletrader.ordermatchingengine.component.order.model.Order;
import com.teletrader.ordermatchingengine.component.stock.model.Stock;

import java.util.List;
import java.util.Optional;

public interface StockApi {

    public Optional<Stock> getById(Long stockId);

    public List<Stock> getAllStocks();

    public List<Order> getBookOrders(Long stockId);
}
