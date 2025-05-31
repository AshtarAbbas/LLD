package com.limitedtimedeal.service.impl;

import com.limitedtimedeal.dto.DealDto;
import com.limitedtimedeal.exceptions.DealNotFoundException;
import com.limitedtimedeal.exceptions.InvalidDealException;
import com.limitedtimedeal.model.Deal;
import com.limitedtimedeal.model.Product;
import com.limitedtimedeal.model.Users;
import com.limitedtimedeal.repository.DealRepository;
import com.limitedtimedeal.repository.ProductRepository;
import com.limitedtimedeal.repository.UserRepository;
import com.limitedtimedeal.service.DealService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class DealServiceImpl implements DealService {

    @Autowired
    private DealRepository dealRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;


    @Override
    public Deal createDeal(DealDto dealDto) {
        Deal deal = new Deal();
        deal.setDiscount(dealDto.getDiscount());
        deal.setEndTime(dealDto.getEndTime());
        deal.setProductCount(dealDto.getProductCount());
        deal.setProductId(dealDto.getProductId());
        return dealRepository.save(deal);
    }


    @Transactional
    public void deactivateItem(Long dealId) {
        Deal deal = dealRepository.findById(dealId)
                .orElseThrow(() -> new DealNotFoundException(dealId, "Deal"));
        deal.setValid(false);
        dealRepository.save(deal);
    }

    @Transactional
    public int deactivateExpiredDeals() {
        LocalDateTime now = LocalDateTime.now(java.time.ZoneId.of("Asia/Kolkata")); // Get current time in IST
        return dealRepository.deactivateExpiredDeals(now);
    }

    @Override
    public Users register(Users user) {
        return userRepository.save(user);
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

        @Override
    @Transactional
    public Product buy(Long userId, Long dealId) {
        Deal deal = dealRepository.findById(dealId)
                .orElseThrow(()-> new DealNotFoundException(dealId, "Deal"));
        Users user = userRepository.findById(userId)
                .orElseThrow(()-> new DealNotFoundException(userId, "User"));
        Set<Users> users = deal.getUsers(); // triggers lazy loading
        if (!deal.isValid() || users.contains(user)) {
            throw new InvalidDealException(user.getUserName(), dealId);
        }

        // Make state changes

        deal.getUsers().add(user);

        if (deal.getDiscount() == 0) {
            deal.setValid(false);
        }

        // Save changes
        dealRepository.save(deal);

        // Load product
        Product product = productRepository.findById(deal.getProductId())
                .orElseThrow(() -> new DealNotFoundException(deal.getProductId(), "Product"));
        return product;
    }
//    @Override
//    @Transactional
//    public Product buy(Long userId, Long dealId) {
//        Deal deal = dealRepository.findById(dealId)
//                .orElseThrow(() -> new DealNotFoundException(dealId, "Deal"));
//        Users user = userRepository.findById(userId)
//                .orElseThrow(() -> new DealNotFoundException(userId, "User "));
//
//        // Create a copy of the users set to avoid ConcurrentModificationException
//        Set<Users> usersCopy = new HashSet<>(deal.getUsers());
//
//        synchronized (usersCopy) { // Synchronize on the copy
//            if (!deal.isValid() || usersCopy.contains(user)) {
//                throw new InvalidDealException(user.getUserName(), dealId);
//            }
//            // Now add the user to the original set
//            deal.getUsers().add(user);
//        }
//
//        if (deal.getDiscount() == 0) {
//            deal.setValid(false);
//        }
//
//        // Save changes
//        dealRepository.save(deal);
//
//        // Load product
//        Product product = productRepository.findById(deal.getProductId())
//                .orElseThrow(() -> new DealNotFoundException(deal.getProductId(), "Product"));
//        return product;
//    }
//    @Override
//    @Transactional
//    public Product buy(Long userId, Long dealId) {
//        Deal deal = dealRepository.findById(dealId)
//                .orElseThrow(() -> new DealNotFoundException(dealId, "Deal"));
//        Users user = userRepository.findById(userId)
//                .orElseThrow(() -> new DealNotFoundException(userId, "User "));
//
//        // Create a copy of the users set to avoid ConcurrentModificationException
//        Set<Users> usersCopy = Collections.newSetFromMap(new ConcurrentHashMap<>());
//        usersCopy.addAll(deal.getUsers());
//
//
//            if (!deal.isValid() || usersCopy.contains(user)) {
//                throw new InvalidDealException(user.getUserName(), dealId);
//            }
//            // Now add the user to the original set
//            deal.getUsers().add(user);
//
//
//        if (deal.getDiscount() == 0) {
//            deal.setValid(false);
//        }
//
//        // Save changes
//        dealRepository.save(deal);
//
//        // Load product
//        Product product = productRepository.findById(deal.getProductId())
//                .orElseThrow(() -> new DealNotFoundException(deal.getProductId(), "Product"));
//        return product;
//    }
}