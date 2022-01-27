package com.example.service;

import com.example.dao.OrderDao;
import com.example.model.Order;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    @InjectMocks
    private OrderServiceImpl orderService;
    @Mock
    private OrderDao orderDao;
    private List<Order> mockOrders;

    @BeforeEach
    void setUp() {
        mockOrders = List.of(
                new Order(8L,"Phone", 10L),
                new Order(10L,"Phone", 10L));
    }

    @Test
    void shouldReturnItemWherePriceIsLowAndReduceQuantity() {
        Mockito.when(orderDao.findItemWherePriceIsLow("Phone", 11L))
                .thenReturn(List.of(new Order(8L,"Phone", 14L)));
        List<Order> actual = orderService.findItemWherePriceIsLow("Phone", 11L);
        Assertions.assertEquals(1, actual.size());
        Assertions.assertEquals(3, actual.get(0).getQuantity());
    }

    @Test
    void shouldReturnAllItem() {
        Mockito.when(orderDao.findItemWherePriceIsLow("Phone", 11L))
                .thenReturn(mockOrders);
        Mockito.when(orderDao.findAll()).thenReturn(mockOrders);
        List<Order> actual = orderService.findItemWherePriceIsLow("Phone", 11L);
        Assertions.assertEquals(2, actual.size());
    }
}
