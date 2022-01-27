package com.example.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@ToString
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long price;
    private String item;
    private Long quantity;
  @Column(name = "order_time")
    private LocalDateTime orderTime;

    @Autowired
    public Order() {

    }
    public Order(Long id, Long price, String item, Long quantity, LocalDateTime orderTime) {
        this.id = id;
        this.price = price;
        this.item = item;
        this.quantity = quantity;
        this.orderTime = orderTime;
    }

    public Order(Long price, String item, Long quantity) {
        this.price = price;
        this.item = item;
        this.quantity = quantity;
    }
}
