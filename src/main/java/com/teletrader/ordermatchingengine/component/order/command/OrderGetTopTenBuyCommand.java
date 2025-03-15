package com.teletrader.ordermatchingengine.component.order.command;

import com.teletrader.ordermatchingengine.component.order.enums.OrderTypeEnum;
import com.teletrader.ordermatchingengine.component.order.model.Order;
import com.teletrader.ordermatchingengine.component.order.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderGetTopTenBuyCommand {

    private final OrderRepository orderRepository;

    public List<Order> execute(Long stockId) {
        return orderRepository.findTop10ByTypeAndStock_StockIdOrderByPriceDesc(OrderTypeEnum.BUY, stockId);
    }
}
