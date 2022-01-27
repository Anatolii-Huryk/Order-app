package com.example.controller;


import com.example.dto.OrderRequestDto;
import com.example.dto.OrderResponseDto;
import com.example.service.OrderService;
import com.example.service.mapper.OrderDtoMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderDtoMapper orderDtoMapper;

    public OrderController(OrderService orderService, OrderDtoMapper orderDtoMapper) {
        this.orderService = orderService;
        this.orderDtoMapper = orderDtoMapper;
    }

    @PostMapping
    public OrderResponseDto create(@RequestBody OrderRequestDto orderRequestDto) {
        return orderDtoMapper.toDto(orderService.add(orderDtoMapper.toModel(orderRequestDto)));
    }

    @PutMapping("/find-by-item")
    public List<OrderResponseDto> findByItem(@RequestParam String item, @RequestParam Long quantity) {
        return orderService.findItemWherePriceIsLow(item, quantity).stream()
                .map(orderDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/all")
    public List<OrderResponseDto> findAll() {
        return orderService.findAll().stream()
                .map(orderDtoMapper::toDto)
                .collect(Collectors.toList());
    }
}
