package com.limitedtimedeal.controller;

import com.limitedtimedeal.dto.DealDto;
import com.limitedtimedeal.model.Deal;
import com.limitedtimedeal.model.Product;
import com.limitedtimedeal.model.Users;
import com.limitedtimedeal.service.DealService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/limitedtimedeal")
public class DealController {

    @Autowired
    private DealService dealService;

    @PostMapping("/register")
    public ResponseEntity<Users> register(@RequestBody Users user){
        Users savedUSer = dealService.register(user);
        return new ResponseEntity<>(savedUSer, HttpStatus.CREATED);
    }

    @PostMapping("/createDeal")
    public ResponseEntity<Deal> createDeal(@RequestBody DealDto dealDto){
        Deal deal = dealService.createDeal(dealDto);
        return new ResponseEntity<>(deal, HttpStatus.CREATED);
    }

    @PostMapping("/createProduct")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product savedProduct = dealService.createProduct(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @GetMapping("/buy")
    public ResponseEntity<Product> buy(@PathParam("userId") Long userId, @PathParam("dealId") Long dealId){
        Product product = dealService.buy(userId, dealId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
