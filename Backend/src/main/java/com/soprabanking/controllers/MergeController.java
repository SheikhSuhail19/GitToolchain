package com.soprabanking.controllers;

import com.soprabanking.model.dto.MRNotificationDto;
import com.soprabanking.model.dto.MergeDto;
import com.soprabanking.services.impl.MergeService;
import com.soprabanking.constants.Constants;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(Constants.CROSS_ORIGIN_URL)
@RequestMapping(Constants.MERGE_CONTROLLER_REQ_MAPPING)
public class MergeController {

    @Autowired
    private MergeService MergeService;

    @GetMapping(Constants.MERGE_CONTROLLER_FETCH)
    public ResponseEntity<List<MergeDto>> getMergeDetails(@RequestParam String id,@RequestHeader(value = HttpHeaders.AUTHORIZATION) String access_token){


        List<MergeDto> mergeDetailsArr = this.MergeService.getMergeDetails(id,access_token);

        if (mergeDetailsArr == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(mergeDetailsArr);
    }

    @GetMapping(Constants.FILTERED_MERGE_REQUEST_MAPPING)
    public List<MRNotificationDto> filteredMergeRequest(@RequestParam String projectId, @RequestHeader(value = HttpHeaders.AUTHORIZATION) String access_token) throws MessagingException {
        List<MRNotificationDto> response = this.MergeService.filterMergeRequest(projectId,access_token);
        if(response!=null){
            return response;
        }
        else{
            return null;
        }
    }
}
