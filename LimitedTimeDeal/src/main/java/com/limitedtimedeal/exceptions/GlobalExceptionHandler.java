package com.limitedtimedeal.exceptions;

import com.limitedtimedeal.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidDealException.class)
    public ResponseEntity<Response> invalidDealException(InvalidDealException exception){
        String message = exception.getMessage();
        Map<String, String> map = new HashMap<>();
        map.put("message", message);
        map.put("UserName",exception.userName);
        map.put("Deal Id", exception.getDealId()+"");
        return new ResponseEntity<>(new Response(map), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DealNotFoundException.class)
    public ResponseEntity<Response> dealNotFoundException(DealNotFoundException exception){
        String message = exception.getMessage();
        Map<String, String> map = new HashMap<>();
        map.put("message", message);
        map.put("Item", exception.getItem());
        map.put("Item Id", exception.getItemId()+"");
        return new ResponseEntity<>(new Response(map), HttpStatus.BAD_REQUEST);
    }
}
