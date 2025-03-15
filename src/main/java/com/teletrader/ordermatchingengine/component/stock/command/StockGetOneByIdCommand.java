package com.teletrader.ordermatchingengine.component.stock.command;

import com.teletrader.ordermatchingengine.component.stock.model.Stock;
import com.teletrader.ordermatchingengine.component.stock.repository.StockRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class StockGetOneByIdCommand {

    private final StockRepository stockRepository;

    public Optional<Stock> execute(Long stockId) {
        return stockRepository.findById(stockId);
    }
}
