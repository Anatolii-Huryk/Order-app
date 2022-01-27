package com.example.dao;

import com.example.model.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao extends JpaRepository<Order, Long> {
    @Query(value = "select * from orders where item = ?1 order by price",
            nativeQuery = true)
    List<Order> findItemWherePriceIsLow(String item, Long quantity);
}
