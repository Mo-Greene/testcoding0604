package com.sparta.testcoding0604.controller;

import com.sparta.testcoding0604.domain.Food;
import com.sparta.testcoding0604.dto.FoodDto;
import com.sparta.testcoding0604.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/restaurant")
@RestController
@RequiredArgsConstructor    //pirvate final을 지정을 해줘야 할때 적는거
public class FoodController {

    private final FoodService foodService;

    @PostMapping("/{restaurant}/food/register") //메뉴 등록
    public void addRestaurantFood(
            @PathVariable Long restaurant,
            @RequestBody List<FoodDto> requestDtoList
    ) {
        foodService.addRestaurantFoods(restaurant, requestDtoList);
    }

    @GetMapping("/{restaurant}/foods")  //메뉴 조회
    public List<Food> findAllRestaurantFoods(
            @PathVariable Long restaurant
    ) {
        return foodService.findAllRestaurantFoods(restaurant);
    }
}
