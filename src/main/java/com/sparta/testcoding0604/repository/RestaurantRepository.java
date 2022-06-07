package com.sparta.testcoding0604.repository;

import com.sparta.testcoding0604.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
