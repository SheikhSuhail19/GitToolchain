package com.soprabanking.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soprabanking.services.IUserProfileService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserProfileServiceImpl implements IUserProfileService {

    private List<Map<String, Object>> dataList;
    private String apiUrl="https://innersource.soprasteria.com/api/v4/user/";
    @Override
    public List<Map<String, Object>> getUserData(String apiToken) throws JsonProcessingException {

        String[] access_token = apiToken.split(" ");
        RestTemplate restTemplate=new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("private-token", access_token[1]);
        String profileStr = "";
        String output = null;
        List<Map<String, Object>> profile = new ArrayList<>();
        HttpEntity<?> entity = new HttpEntity<>(headers);
        try {
            String profileUrl=apiUrl;
            UriComponentsBuilder profileBuilder = UriComponentsBuilder.fromHttpUrl(profileUrl);
            ResponseEntity<String> response = restTemplate.exchange(profileBuilder.toUriString(), HttpMethod.GET, entity, String.class);
            profileStr=response.getBody();

            String profileStr1 ="["+profileStr+"]";

            ObjectMapper objectMapper = new ObjectMapper();
             profile = objectMapper.readValue(profileStr1, new TypeReference<List<Map<String, Object>>>() {});

        } catch (Exception e) {
            e.printStackTrace();
            output="[{\"Error\":"+"\"Something Went Wrong\""+"}]";
        }
        return profile ;
    }
}

