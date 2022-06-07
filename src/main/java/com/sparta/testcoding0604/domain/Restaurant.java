package com.sparta.testcoding0604.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                //pk id

    @Column(nullable = false)
    private String name;            //음식점 이름

    @Column(nullable = false)
    private int minOrderPrice;      //최소주문금액

    @Column(nullable = false)
    private int deliveryFee;        //배달비
}
