package com.soprabanking.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.soprabanking.services.impl.IssueService;
import com.soprabanking.constants.Constants;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(Constants.CROSS_ORIGIN_URL)
@RequestMapping("/issue")
public class IssueController {
    private final IssueService IssueService;

    public IssueController(com.soprabanking.services.impl.IssueService issueService) {
        IssueService = issueService;
    }

        @GetMapping("/issueCount")

    public List<Map<String, Object>> getData(@RequestParam String data,@RequestHeader(value = HttpHeaders.AUTHORIZATION) String access_token) throws JsonProcessingException {
        String project_id = data;
        String[] apiToken = access_token.split(" ");
        return IssueService.getData(project_id,apiToken[1]);
    }
}
