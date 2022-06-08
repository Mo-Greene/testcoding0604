package com.sparta.testcoding0604.repository;

import com.sparta.testcoding0604.domain.Food;
import com.sparta.testcoding0604.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {
    // 밑에 있는 녀석들은 서비스에서만 활용
    List<Food> findFoodsByRestaurant(Restaurant restaurant);    //이름 적는 방법 규칙
    Optional<Food> findFoodByRestaurantAndName(Restaurant restaurant, String foodName);
    //음식에                                           객체 가져오고,      음식이름을 가져와라
}
