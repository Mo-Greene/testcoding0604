package com.sparta.testcoding0604.repository;

import com.sparta.testcoding0604.domain.Food;
import com.sparta.testcoding0604.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findFoodsByRestaurant(Restaurant restaurant);
    Optional<Food> findFoodByRestaurantAndName(Restaurant restaurant, String foodName);
    //음식에                                   레스토랑을 가져오고, 음식이름을 가져와라
}
