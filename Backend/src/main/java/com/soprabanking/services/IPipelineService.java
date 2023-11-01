package com.soprabanking.services;

import java.util.Map;

public interface IPipelineService {
    public Map<String, Object> getAllPipelines(String id, String access_token, int page);
}
