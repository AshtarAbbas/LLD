package com.limitedtimedeal.exceptions;

import lombok.Data;

@Data
public class InvalidDealException extends RuntimeException {
    Long dealId;
    String userName;
    public InvalidDealException(String userName, Long dealId) {
        super("Deal "+dealId+" is invalid for the user "+userName);
        this.dealId=dealId;
        this.userName=userName;
    }
}
