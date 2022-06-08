package com.sparta.testcoding0604.dto;

import com.sparta.testcoding0604.domain.Orders;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderResponseDto {
    private String restaurantName;
    private List<FoodsResponseDto> foods;
    private int deliveryFee;
    private int totalPrice;


    public OrderResponseDto(Orders orders, int deliveryFee, List<FoodsResponseDto> foods) {
        this.restaurantName = orders.getRestaurantName();
        this.foods = foods;
        this.deliveryFee = deliveryFee;
        this.totalPrice = orders.getTotalPrice();
    }
}
