package com.teletrader.ordermatchingengine.component.order.repository;

import com.teletrader.ordermatchingengine.component.order.enums.OrderTypeEnum;
import com.teletrader.ordermatchingengine.component.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    public List<Order> findTop10ByTypeAndStock_StockIdOrderByPrice(OrderTypeEnum type, Long stockId);

    public List<Order> findTop10ByTypeAndStock_StockIdOrderByPriceDesc(OrderTypeEnum type, Long stockId);
}
