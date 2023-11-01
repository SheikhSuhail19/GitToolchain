package com.soprabanking.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.soprabanking.services.impl.MRService;
import com.soprabanking.constants.Constants;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(Constants.CROSS_ORIGIN_URL)
@RequestMapping("/MR")
public class MRController {
    private final MRService MRService;

    public MRController(MRService MRService) {
        this.MRService = MRService;
    }
    @GetMapping("/getMrCount")
    public List<Map<String, Object>> getMR(@RequestParam String data,@RequestHeader(value = HttpHeaders.AUTHORIZATION) String access_token) throws JsonProcessingException {

        String project_id = data;
        String[] apiToken = access_token.split(" ");
        return MRService.getMR(project_id,apiToken[1]);
    }
}
