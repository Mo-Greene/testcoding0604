package com.sparta.testcoding0604.controller;

import com.sparta.testcoding0604.dto.OrderRequestDto;
import com.sparta.testcoding0604.dto.OrderResponseDto;
import com.sparta.testcoding0604.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order/request")
    public OrderResponseDto orderFood(@RequestBody OrderRequestDto orderRequestDto) {
        return orderService.order(orderRequestDto);
    }

    @GetMapping("/orders")
    public List<OrderResponseDto> findAllOrder() {
        return orderService.findAllOrder();
    }
}
