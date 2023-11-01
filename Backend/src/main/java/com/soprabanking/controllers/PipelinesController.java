package com.soprabanking.controllers;


import com.soprabanking.services.impl.PipelineService;
import com.soprabanking.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(Constants.CROSS_ORIGIN_URL)
@RequestMapping(Constants.PROJECT_WISE_PIPELINES_API)
public class PipelinesController {

    @Autowired
    PipelineService pipelineService;

    /**
     * Gets all pipelines of a repository.
     *
     * @param id   the ID of the repository
     * @param page the page number to return
     * @return a paged map with pipelines array and total pipelines
     */
    @GetMapping()
    public ResponseEntity<Map<String, Object>> getBranches(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String access_token,
                                                           @PathVariable(Constants.PROJECT_ID_PATH_VARIABLE) String id,
                                                           @RequestParam(defaultValue = Constants.DEFAULT_PAGE) int page) {

        Map<String, Object> res = this.pipelineService.getAllPipelines(id, access_token, page + 1);

        return ResponseEntity.ok(res);

    }
}