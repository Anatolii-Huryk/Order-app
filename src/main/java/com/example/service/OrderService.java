package com.example.service;

import com.example.model.Order;
import java.util.List;

public interface OrderService {
    Order add(Order order);

    List<Order> findItemWherePriceIsLow(String item, Long quantity);

    List<Order> findAll();
}
