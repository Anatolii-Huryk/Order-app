package com.example.service;

import com.example.dao.OrderDao;
import com.example.model.Order;
import java.time.LocalDateTime;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManagerFactory;
import org.hibernate.query.Query;

@Service
public class OrderRemoveService {
    private final SessionFactory sessionFactory;
    private final OrderDao orderDao;

    @Autowired
    public OrderRemoveService(EntityManagerFactory factory, OrderDao orderDao) {
        this.sessionFactory = factory.unwrap(SessionFactory.class);
        this.orderDao = orderDao;
    }

    @Scheduled(cron = "0/60 * * * * *", zone = "Europe/Kiev")
    public void removerOrders() {
        try (Session session = sessionFactory.openSession()) {
            Query<Order> query = session.createQuery(
                    "FROM Order o WHERE o.orderTime <=: orderTime", Order.class);
            query.setParameter("orderTime", LocalDateTime.now().minusMinutes(10));
            List<Order> list = query.getResultList();
            if (!list.isEmpty()) {
                orderDao.deleteAll(list);
            }
        }
    }
}
