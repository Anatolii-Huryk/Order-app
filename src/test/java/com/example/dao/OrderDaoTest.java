package com.example.dao;

import com.example.model.Order;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
class OrderDaoTest {
    @Autowired
    private OrderDao orderDao;

    @Test
    void shouldReturnOnlyItems() {
        List<Order> mockOrders = List.of(
                new Order(10L, "Phone", 3L),
                new Order(12L, "Phone", 5L));
        Mockito.when(orderDao.findItemWherePriceIsLow("Phone", 2L))
                .thenReturn(mockOrders);
        List<Order> actual = orderDao.findItemWherePriceIsLow("Phone", 2L);
        Assertions.assertEquals(2, actual.size());
    }
}
