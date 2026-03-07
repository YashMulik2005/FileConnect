package com.CCL.FileConnect.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ErrorResponseHandler {
    public static ResponseEntity<Object> errorResponse(HttpStatus httpStatus, String msg, Boolean status){
        Map<String ,Object>mp = new HashMap<>();

        mp.put("status", status);
        mp.put("message", msg);

        return new ResponseEntity<>(mp, httpStatus);
    }
}
