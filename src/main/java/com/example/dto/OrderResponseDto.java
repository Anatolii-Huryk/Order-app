package com.example.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderResponseDto {
    private Long price;
    private String item;
    private Long quantity;
}
