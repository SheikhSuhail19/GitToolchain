package com.soprabanking.controllers;

import com.soprabanking.model.dto.CommitDto;
import com.soprabanking.services.impl.CommitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.soprabanking.constants.Constants;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(Constants.CROSS_ORIGIN_URL)
public class CommitController {
    private final CommitService commitService;

    @Autowired
    public CommitController(CommitService commitService) {
        this.commitService = commitService;
    }

    @GetMapping(Constants.GET_COMMIT_REQUEST_MAPPING)
    public ResponseEntity<List<CommitDto>> getCommits(
            @RequestParam String projectId,
            @RequestParam String branchName,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String privateToken
    ) {
        List<CommitDto> commitDetailsArr = commitService.getCommits( projectId, branchName, privateToken);

        if (commitDetailsArr == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(commitDetailsArr);
    }

    @GetMapping(Constants.GET_COMMIT_TOTAL_NUMBER)
    public List<Integer> getTotalCommitsNumber(@RequestParam String projectId, @RequestHeader(value = HttpHeaders.AUTHORIZATION) String privateToken) {
        Integer returnCommitNumber = null;
        Integer latestCommits = null;
        Integer weekCommits = null;
        List<Integer> toBeReturned = new ArrayList<>();

        returnCommitNumber = commitService.getNumberOfCommits(projectId, privateToken);
        latestCommits = commitService.getLatestCommit(projectId, privateToken);
        weekCommits = commitService.getLatestWeekCommit(projectId,privateToken);

        toBeReturned.add(returnCommitNumber);
        toBeReturned.add(latestCommits);
        toBeReturned.add(weekCommits);



        return toBeReturned;
    }
}
