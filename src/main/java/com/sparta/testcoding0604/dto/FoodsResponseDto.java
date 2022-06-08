package com.sparta.testcoding0604.dto;

import com.sparta.testcoding0604.domain.OrderItem;
import lombok.Getter;

@Getter
public class FoodsResponseDto {
    private String name;
    private int quantity;
    private int price;

    public FoodsResponseDto(OrderItem orderItem) {
        this.name = orderItem.getName();
        this.quantity = orderItem.getQuantity();
        this.price = orderItem.getPrice();
    }
}
