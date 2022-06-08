package com.sparta.testcoding0604.dto;

import com.sparta.testcoding0604.domain.OrderItem;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderRequestDto {
    private Long restaurantId;
    private List<OrderItem> foods;
}
