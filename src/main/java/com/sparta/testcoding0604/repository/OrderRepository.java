package com.sparta.testcoding0604.repository;

import com.sparta.testcoding0604.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}
