package com.example.service.mapper;

import com.example.dto.OrderRequestDto;
import com.example.dto.OrderResponseDto;
import com.example.model.Order;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class OrderDtoMapper {
    public OrderResponseDto toDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setItem(order.getItem());
        orderResponseDto.setPrice(order.getPrice());
        orderResponseDto.setQuantity(order.getQuantity());
        return orderResponseDto;
    }

    public Order toModel(OrderRequestDto orderRequestDto) {
        Order order = new Order();
        order.setItem(orderRequestDto.getItem());
        order.setPrice(orderRequestDto.getPrice());
        order.setQuantity(orderRequestDto.getQuantity());
        order.setOrderTime(LocalDateTime.now());
        return order;
    }
}
