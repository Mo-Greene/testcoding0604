package com.sparta.testcoding0604.service;

import com.sparta.testcoding0604.domain.Food;
import com.sparta.testcoding0604.domain.Restaurant;
import com.sparta.testcoding0604.dto.FoodDto;
import com.sparta.testcoding0604.repository.FoodRepository;
import com.sparta.testcoding0604.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional  //jpa실행
@RequiredArgsConstructor
@Service
public class FoodService {
    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;

    public void addRestaurantFoods(Long restaurantId, List<FoodDto> foodDto){
        Optional<Restaurant> foundRestaurant = restaurantRepository.findById(restaurantId);

        checkRestaurant(foundRestaurant);
        Restaurant restaurant = foundRestaurant.get();

        for (FoodDto requestDto : foodDto) {
            String foodName = requestDto.getName();
            int foodPrice = requestDto.getPrice();

            checkDuplicateRestaurantFood(restaurant, foodName);

            checkFoodPrice(foodPrice);

            Food food = Food.builder()
                    .name(foodName)
                    .price(foodPrice)
                    .restaurant(restaurant)
                    .build();

            foodRepository.save(food);
    }
}
    private void checkRestaurant(Optional<Restaurant> foundRestaurant) {
        if (!foundRestaurant.isPresent())
            throw new IllegalArgumentException("음식점이 존재 하지 않습니다.");
    }

    private void checkDuplicateRestaurantFood(Restaurant restaurant, String foodName) {
        Optional<Food> found = foodRepository.findFoodByRestaurantAndName(restaurant, foodName);
        if(found.isPresent())
            throw new IllegalArgumentException("같은 메뉴명이 존재 합니다.");
    }

    private void checkFoodPrice(int foodPrice) {
        if (foodPrice < 100)
            throw new IllegalArgumentException("100원 이상으로 정해주세요");

        if (foodPrice > 1_000_000)
            throw new IllegalArgumentException("메뉴는 백만원 이상으로 정할수 없습니다.");

        if (foodPrice % 100 > 0)
            throw new IllegalArgumentException("백원 단위로 적어주세요");
    }

    @Transactional
    public List<Food> findAllRestaurantFoods(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(
                        () -> new NullPointerException("찾으시는 음식점이 없습니다."));

        return foodRepository.findFoodsByRestaurant(restaurant);
    }
}