package com.soprabanking.services;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface ICardServiceMergeRequests {
    List<Map<String, Object>> getMR(String project_id, String accessToken) throws JsonProcessingException;
}
