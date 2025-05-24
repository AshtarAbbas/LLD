package com.limitedtimedeal.service;

import com.limitedtimedeal.dto.DealDto;
import com.limitedtimedeal.model.Deal;
import com.limitedtimedeal.model.Product;
import com.limitedtimedeal.model.Users;

public interface DealService {
    Deal createDeal(DealDto dealDto);
    public int deactivateExpiredDeals();

    Users register(Users user);

    Product createProduct(Product product);

    Product buy(Long userId, Long dealId);
}
