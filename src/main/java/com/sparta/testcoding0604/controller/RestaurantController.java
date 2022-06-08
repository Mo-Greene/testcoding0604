package com.sparta.testcoding0604.controller;

import com.sparta.testcoding0604.domain.Restaurant;
import com.sparta.testcoding0604.dto.RestaurantDto;
import com.sparta.testcoding0604.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //json형태로 데이터를 받기위해서 쓰는것
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @PostMapping("/restaurant/register")    //음식점 추가
    public Restaurant postRestaurant(@RequestBody RestaurantDto requestDto){
        return restaurantService.addRestaurant(requestDto);
    }

    @GetMapping("/restaurants")     //음식점 조회
    //레스토랑 리스트 반환타입
    public List<Restaurant> getRestaurant(){
        return restaurantService.findAllRestaurant();
    }
}
