package com.soprabanking.services;

import java.util.Map;

public interface IBranchService {
    public Map<String, Object> getAllBranches(String id, String access_token, Boolean pagination, int page);
}
