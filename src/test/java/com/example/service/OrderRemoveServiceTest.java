package com.example.service;

import com.example.dao.OrderDao;
import com.example.model.Order;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderRemoveServiceTest {
    @InjectMocks
    private OrderRemoveService orderRemoveService;

    @Mock
    private OrderDao orderDao;

    @Test
    void shouldDeleteAfterTenMinutes() {
        Mockito.when(orderDao.findAll())
                .thenReturn(List.of(
                        new Order(1L, 12L, "Phone", 9L,
                                LocalDateTime.now().plusMinutes(15)),
                        new Order(2L, 15L, "Phone", 12L,
                                LocalDateTime.now().plusMinutes(15))));
        List<Order> actual = orderDao.findAll();
        orderRemoveService.removerOrders();
        Assertions.assertEquals(0, actual.size());
    }
}
