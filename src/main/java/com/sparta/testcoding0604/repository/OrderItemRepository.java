package com.sparta.testcoding0604.repository;

import com.sparta.testcoding0604.domain.OrderItem;
import com.sparta.testcoding0604.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findOrderItemsByOrders(Orders orders);
}
