package com.soprabanking.services;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Map;

public interface IUserProfileService {
    List<Map<String, Object>> getUserData(String apiToken) throws JsonProcessingException;
}
