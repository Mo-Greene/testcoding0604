package com.sparta.testcoding0604.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinColumn(name = "orders_id")
    private List<OrderItem> foods;

    @Column(nullable = false)
    private String restaurantName;

    @Column(nullable = false)
    private int totalPrice;

}
