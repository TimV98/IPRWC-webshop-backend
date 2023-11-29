package com.example.IPRWC.Backend.payload.dto;

import com.example.IPRWC.Backend.entities.Order;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {

    public Order order;
}

