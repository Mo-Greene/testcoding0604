package com.sparta.testcoding0604.service;

import com.sparta.testcoding0604.domain.Restaurant;
import com.sparta.testcoding0604.dto.RestaurantDto;
import com.sparta.testcoding0604.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional  //메서드는 메서드가 포함하고 있는 작업 중에 하나라도 실패할 경우 전체 작업을 취소한다.
@RequiredArgsConstructor
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public Restaurant addRestaurant(RestaurantDto restaurantDto){
        String name = restaurantDto.getName();
        int minOrderPrice = restaurantDto.getMinOrderPrice();
        int deliveryFee = restaurantDto.getDeliveryFee();
        //변수 지정 확인 후 메소드 진행

        allowMinOrderPrice(minOrderPrice);
        allowDeliveryFee(deliveryFee);

        Restaurant restaurant = Restaurant.builder()
                .name(name)
                .minOrderPrice(minOrderPrice)
                .deliveryFee(deliveryFee)
                .build();

        restaurantRepository.save(restaurant);

        return restaurant;
    }

    private void allowMinOrderPrice(int minOrderPrice){
        if (!(minOrderPrice >=1_000)) {
            throw new IllegalStateException("천원 이상만 등록가능합니다.");
        } else if (!(minOrderPrice <= 100_000)) {
            throw new IllegalStateException("십만원 이상은 등록할 수 없습니다.");
        }

        if (minOrderPrice % 100 > 0) {
            throw new IllegalStateException("백원 단위로 입력해주세요.");
        }
    }

    private void allowDeliveryFee(int deliveryFee){
        if (!(deliveryFee >= 0)) {
            throw new IllegalStateException("배달료를 입력해주세요.");
        } else if (!(deliveryFee <= 10_000)) {
            throw new IllegalStateException("배달료는 만원까지 입니다.");
        }

        if (deliveryFee % 500 > 0) {
            throw new IllegalStateException("오백원 단위로 입력해주세요");
        }
    }

    public List<Restaurant> findAllRestaurant() {
        return restaurantRepository.findAll();
    }
}
