package com.limitedtimedeal.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class DealNotFoundException extends RuntimeException {

    private Long itemId;
    private String item;


    public DealNotFoundException(Long itemId, String item) {
        super(item + " with id " + itemId + " was not found.");
        this.itemId = itemId;
        this.item = item;
    }
}
