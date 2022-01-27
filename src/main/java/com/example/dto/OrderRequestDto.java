package com.example.dto;

import lombok.Data;
import lombok.ToString;
import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
@ToString
public class OrderRequestDto {
    private Long price;
    private String item;
    private Long quantity;
    private LocalDateTime orderTime;

    public OrderRequestDto(String item, Long price, Long quantity) {
        this.item = item;
        this.price = price;
        this.quantity = quantity;
    }
}
