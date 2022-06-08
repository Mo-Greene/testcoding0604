package com.sparta.testcoding0604.service;

import com.sparta.testcoding0604.domain.Food;
import com.sparta.testcoding0604.domain.OrderItem;
import com.sparta.testcoding0604.domain.Orders;
import com.sparta.testcoding0604.domain.Restaurant;
import com.sparta.testcoding0604.dto.FoodsResponseDto;
import com.sparta.testcoding0604.dto.OrderRequestDto;
import com.sparta.testcoding0604.dto.OrderResponseDto;
import com.sparta.testcoding0604.repository.FoodRepository;
import com.sparta.testcoding0604.repository.OrderItemRepository;
import com.sparta.testcoding0604.repository.OrderRepository;
import com.sparta.testcoding0604.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Transactional
    public OrderResponseDto order(OrderRequestDto orderRequestDto) {
        Restaurant restaurant = getRestaurant(orderRequestDto);

        int totalPrice = 0;
        List<FoodsResponseDto> foodsResponseDtoList = new ArrayList<>();    //foods는 배열로 만들어야됨 api명세서 확인
        List<OrderItem> orderItems = orderRequestDto.getFoods();
        List<OrderItem> orderItemList = new ArrayList<>();
        // for each문으로 tempOrderItem은 orderItems 수량만큼 증가하며 돌아라
        for (OrderItem tempOrderItem : orderItems) {

            int quantity = tempOrderItem.getQuantity();
            if (quantity < 1 || quantity > 100) {
                throw new IllegalArgumentException("수량을 확실히 적어주세요");
            }

            Food food = getFood(tempOrderItem);

            OrderItem orderItem = OrderItem.builder()
                    .quantity(tempOrderItem.getQuantity())
                    .name(food.getName())
                    .price(food.getPrice() * quantity)
                    .food(food)
                    .build();
            orderItemRepository.save(orderItem);
            FoodsResponseDto foodsResponseDto = new FoodsResponseDto(orderItem);
            foodsResponseDtoList.add(foodsResponseDto);
            totalPrice += food.getPrice() * quantity;
            orderItemList.add(orderItem);
        }

        if (totalPrice < restaurant.getMinOrderPrice()) {
            throw new IllegalArgumentException("총 금액을 넘길 수 없습니다.");
        }

        int deliveryFee = restaurant.getDeliveryFee();
        totalPrice += deliveryFee;
        //orders를 만드는것
        Orders orders = Orders.builder()
                .restaurantName(restaurant.getName())
                .totalPrice(totalPrice)
                .foods(orderItemList)
                .build();
        orderRepository.save(orders);
        OrderResponseDto orderResponseDto = new OrderResponseDto(orders, deliveryFee, foodsResponseDtoList);
        return orderResponseDto;
    }


    //레스토랑이 있는지 없는지 체크하는 메서드
    private Restaurant getRestaurant(OrderRequestDto ordersRequestDto) {
        Restaurant restaurant = restaurantRepository.findById(ordersRequestDto.getRestaurantId())
                .orElseThrow(
                        () -> new NullPointerException("음식점이 없습니다.")
                );
        return restaurant;
    }

    private Food getFood(OrderItem tempOrderItem) {
        return foodRepository.findById(tempOrderItem.getId())
                .orElseThrow(() -> new NullPointerException("음식을 찾을 수 없습니다."));
    }

    @Transactional
    public List<OrderResponseDto> findAllOrder() {
        List<OrderResponseDto> ordersResponseDtoList = new ArrayList<>();

        List<Orders> ordersList = orderRepository.findAll();

        for (Orders orders : ordersList) {
            int deliveryFee = restaurantRepository.findByName(orders.getRestaurantName()).getDeliveryFee();
            List<FoodsResponseDto> foodsResponseDtoList = new ArrayList<>();


            List<OrderItem> orderItemsList  = orderItemRepository.findOrderItemsByOrders(orders);
            for (OrderItem orderItem : orderItemsList) {
                FoodsResponseDto foodsResponseDto = new FoodsResponseDto(orderItem);
                foodsResponseDtoList.add(foodsResponseDto);
            }

            OrderResponseDto orderResponseDto = new OrderResponseDto(orders, deliveryFee, foodsResponseDtoList);
            ordersResponseDtoList.add(orderResponseDto);
        }

        return ordersResponseDtoList;
    }

}
