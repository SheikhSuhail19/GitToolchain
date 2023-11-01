package com.soprabanking.services;

import java.util.Map;

public interface IMemberService {
    public Map<String, Object> getAllMembers(String id, String access_token, int page, String searchText, String state);
}

