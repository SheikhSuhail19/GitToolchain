package com.soprabanking.services;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Map;

public interface IIssueService {
    List<Map<String, Object>> getData(String project_id, String accessToken) throws JsonProcessingException;

}
