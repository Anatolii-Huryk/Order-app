package com.example.dto;

import lombok.Data;
import lombok.ToString;
import java.time.LocalDateTime;

@Data
@ToString
public class OrderRequestDto {
    private Long price;
    private String item;
    private Long quantity;
    private LocalDateTime orderTime;

    public OrderRequestDto( Long price, String item, Long quantity) {
        this.price = price;
        this.item = item;
        this.quantity = quantity;
    }
}
