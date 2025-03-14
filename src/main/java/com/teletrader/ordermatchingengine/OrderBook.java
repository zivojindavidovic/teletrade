package com.teletrader.ordermatchingengine;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

@Component
public class OrderBook {
    private List<Order> orders = new ArrayList<>();
    private final Random random = new Random();
    private int nextOrderId = 41;

    public OrderBook() {
        generateOrders();
    }

    private void generateOrders() {
        orders.clear();

        for (int i = 1; i <= 20; i++) {
            orders.add(new Order(i, 1, random.nextInt(100), 90 + random.nextInt(30), "BUY"));
        }
        for (int i = 21; i <= 40; i++) {
            orders.add(new Order(i, 2, random.nextInt(100), 90 + random.nextInt(30), "SELL"));
        }

        updateTopOrders();
    }

    public void addOrder(Order order) {
        orders.add(order);
        updateTopOrders();
    }

    public void addRandomOrders() {
        for (int i = 0; i < 5; i++) {
            int quantity = random.nextInt(100);
            double price = 90 + random.nextInt(30);
            String type = random.nextBoolean() ? "BUY" : "SELL";
            orders.add(new Order(nextOrderId++, 1, quantity, price, type));
        }

        updateTopOrders();
    }

    private void updateTopOrders() {
        List<Order> topBuyOrders = orders.stream()
                .filter(order -> "BUY".equals(order.getType()))
                .sorted(Comparator.comparingDouble(Order::getPrice).reversed())
                .limit(10)
                .toList();

        List<Order> topSellOrders = orders.stream()
                .filter(order -> "SELL".equals(order.getType()))
                .sorted(Comparator.comparingDouble(Order::getPrice))
                .limit(10)
                .toList();

        orders = new ArrayList<>();
        orders.addAll(topBuyOrders);
        orders.addAll(topSellOrders);
    }

    public List<Order> getOrders() {
        return orders;
    }
}
