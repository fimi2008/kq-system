package com.lionxxw.kqsystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class OrderDinnerOptionDto implements Serializable {

    private Long id;

    private Long orderId;

    private Long tempId;

    private String name;
}