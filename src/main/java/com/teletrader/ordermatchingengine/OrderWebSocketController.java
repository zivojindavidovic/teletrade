package com.teletrader.ordermatchingengine;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderWebSocketController {

    private final OrderBook orderBook;
    private final SimpMessagingTemplate messagingTemplate;

    public OrderWebSocketController(OrderBook orderBook, SimpMessagingTemplate messagingTemplate) {
        this.orderBook = orderBook;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/getOrders")
    @SendTo("/topic/orders")
    public List<Order> getOrders() {
        return orderBook.getOrders();
    }

    @PostMapping("/add-random")
    public List<Order> addRandomOrders() {
        orderBook.addRandomOrders();
        List<Order> updatedOrders = orderBook.getOrders();

        messagingTemplate.convertAndSend("/topic/orders", updatedOrders);

        return updatedOrders;
    }

    @PostMapping("/add-order")
    public List<Order> addOrder(@RequestBody Order order) {
        orderBook.addOrder(order);
        List<Order> updatedOrders = orderBook.getOrders();

        messagingTemplate.convertAndSend("/topic/orders", updatedOrders);

        return updatedOrders;
    }

    @Scheduled(fixedRate = 5000)
    public void updateOrders() {
        List<Order> orders = orderBook.getOrders();
        messagingTemplate.convertAndSend("/topic/orders", orders);
    }
}
