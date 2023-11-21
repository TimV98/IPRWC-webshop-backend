package com.example.IPRWC.Backend.payload.response;

import com.example.IPRWC.Backend.entities.Order;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {

    private Order order;
    private List<Order> orders;
    private String message;

    public OrderResponse(Order order, String message){
        this.message = message;
        this.order = order;
    }

    public OrderResponse(List<Order> orders, String message){
        this.message = message;
        this.orders = orders;
    }

}
