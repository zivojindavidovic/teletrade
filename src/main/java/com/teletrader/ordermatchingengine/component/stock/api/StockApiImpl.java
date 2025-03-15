package com.teletrader.ordermatchingengine.component.stock.api;

import com.teletrader.ordermatchingengine.component.order.model.Order;
import com.teletrader.ordermatchingengine.component.stock.command.StockGetAllCommand;
import com.teletrader.ordermatchingengine.component.stock.command.StockGetBookOrdersCommand;
import com.teletrader.ordermatchingengine.component.stock.command.StockGetOneByIdCommand;
import com.teletrader.ordermatchingengine.component.stock.model.Stock;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class StockApiImpl implements StockApi{
    private final StockGetAllCommand stockGetStocksCommand;
    private final StockGetOneByIdCommand stockGetOneByIdCommand;
    private final StockGetBookOrdersCommand stockGetBookOrdersCommand;

    @Override
    public Optional<Stock> getById(Long stockId) {
        return stockGetOneByIdCommand.execute(stockId);
    }

    @Override
    public List<Stock> getAllStocks() {
        return stockGetStocksCommand.execute();
    }

    @Override
    public List<Order> getBookOrders(Long stockId) {
        return stockGetBookOrdersCommand.execute(stockId);
    }
}
