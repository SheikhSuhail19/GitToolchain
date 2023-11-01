package com.soprabanking.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.soprabanking.services.impl.UserProfileServiceImpl;
import com.soprabanking.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(Constants.CROSS_ORIGIN_URL)
@RequestMapping("/user")
public class UserProfileController {

    @Autowired
    private UserProfileServiceImpl userProfileService;

    @RequestMapping("/getUserProfile")
    public ResponseEntity<List<Map<String, Object>>> getUserProfile(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String access_token){
        List<Map<String, Object>> userMap = new ArrayList<>();
        try {
             userMap = userProfileService.getUserData(access_token);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(userMap);
    }
}
