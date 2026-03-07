package com.CCL.FileConnect.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class SuccessResponseHandler {
    public static ResponseEntity<Object> successResponse(HttpStatus httpStatus, String msg,
                                                         Boolean status, Object data){
        Map<String, Object>mp = new HashMap<>();
        mp.put("status", status);
        mp.put("message", msg);
        mp.put("data", data);

        return new ResponseEntity<>(mp,httpStatus);
    }
}
