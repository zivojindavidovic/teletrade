package com.teletrader.ordermatchingengine.component.stock.command;

import com.teletrader.ordermatchingengine.component.stock.model.Stock;
import com.teletrader.ordermatchingengine.component.stock.repository.StockRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class StockGetAllCommand {

    private final StockRepository stockRepository;

    public List<Stock> execute() {
        return stockRepository.findAll();
    }
}
