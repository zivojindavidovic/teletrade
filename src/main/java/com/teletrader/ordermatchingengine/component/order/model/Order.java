package com.teletrader.ordermatchingengine.component.order.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.teletrader.ordermatchingengine.component.order.enums.OrderTypeEnum;
import com.teletrader.ordermatchingengine.component.stock.model.Stock;
import com.teletrader.ordermatchingengine.component.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    private Double price;

    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private OrderTypeEnum type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    @JsonBackReference
    private Stock stock;
}
