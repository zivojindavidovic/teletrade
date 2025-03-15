package com.teletrader.ordermatchingengine.component.stock.repository;

import com.teletrader.ordermatchingengine.component.stock.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

}
