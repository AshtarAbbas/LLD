package com.limitedtimedeal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;


import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DealDto {

    private Long productId;

    private LocalDateTime endTime;

    private double discount;

    private Long productCount;
}
