package com.teletrader.ordermatchingengine.component.stock.command;

import com.teletrader.ordermatchingengine.component.order.api.OrderApi;
import com.teletrader.ordermatchingengine.component.order.model.Order;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class StockGetBookOrdersCommand {

    @Lazy
    private final OrderApi orderApi;

    private List<Order> orderBook = new ArrayList<>();

    public List<Order> execute(Long stockId) {
        orderBook.clear();
        orderBook.addAll(getTopTenBuyOrders(stockId));
        orderBook.addAll(getTopTenSellOrders(stockId));

        return orderBook;
    }

    private List<Order> getTopTenBuyOrders(Long stockId) {
        return orderApi.getTopTenBuyOrders(stockId);
    }

    private List<Order> getTopTenSellOrders(Long stockId) {
        return orderApi.getTopTenSellOrders(stockId);
    }
}
