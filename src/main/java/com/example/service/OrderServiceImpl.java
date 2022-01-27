package com.example.service;

import com.example.dao.OrderDao;
import com.example.model.Order;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;

    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public Order add(Order order) {
        return orderDao.save(order);
    }

    @Override
    public List<Order> findItemWherePriceIsLow(String item, Long quantity) {
        List<Order> order = orderDao.findItemWherePriceIsLow(item, quantity);
        Order orderLowPrice = order.get(0);
        long newQuantity = orderLowPrice.getQuantity() - quantity;
        if (newQuantity < 0) {
            return orderDao.findAll();
        } else {
            orderLowPrice.setQuantity(newQuantity);
            orderDao.save(orderLowPrice);
        }
        return List.of(orderLowPrice);
    }

    @Override
    public List<Order> findAll() {
        return orderDao.findAll();
    }
}
