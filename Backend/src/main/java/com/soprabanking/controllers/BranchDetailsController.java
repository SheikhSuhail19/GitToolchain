package com.soprabanking.controllers;

import com.soprabanking.model.Branch;
import com.soprabanking.model.Commit;
import com.soprabanking.model.Issue;
import com.soprabanking.model.MergeRequest;
import com.soprabanking.services.BranchDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.soprabanking.constants.ApiUrlConstants.ALL_BRANCHES_ENDPOINT;
import static com.soprabanking.constants.ApiUrlConstants.AUTHORIZATION;
import static com.soprabanking.constants.ApiUrlConstants.BRANCHES_BASE_URL_ENDPOINT;
import static com.soprabanking.constants.ApiUrlConstants.BRANCH_COMMITS_ENDPOINT;
import static com.soprabanking.constants.ApiUrlConstants.BRANCH_DETAILS_ENDPOINT;
import static com.soprabanking.constants.ApiUrlConstants.BRANCH_ISSUES_ENDPOINT;
import static com.soprabanking.constants.ApiUrlConstants.BRANCH_MERGE_REQUESTS_ENDPOINT;

/**
 * Controller for handling branch-related operations for a project.
 */
@RestController
@RequestMapping(BRANCHES_BASE_URL_ENDPOINT)
public class BranchDetailsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BranchDetailsController.class);
    private final BranchDetailsService branchDetailsService;

    public BranchDetailsController(BranchDetailsService branchDetailsService) {
        this.branchDetailsService = branchDetailsService;
    }

    /**
     * Get a list of all branches for a project.
     *
     * @param innerSourceAccessToken The authorization token obtained from request header.
     * @param projectId              The ID of the project.
     * @return List of branches.
     */
    @GetMapping(ALL_BRANCHES_ENDPOINT)
    public List<Branch> getAllBranches(@RequestHeader(AUTHORIZATION) final String innerSourceAccessToken, @PathVariable final Long projectId) {
        return branchDetailsService.getAllBranches(innerSourceAccessToken, projectId);
    }

    /**
     * Get details of a specific branch within a project.
     *
     * @param innerSourceAccessToken The authorization token obtained from request header.
     * @param projectId              The ID of the project.
     * @param branchName             The name of the branch.
     * @return List of branch details.
     */
    @GetMapping(BRANCH_DETAILS_ENDPOINT)
    public List<Branch> getBranchDetails(@RequestHeader(AUTHORIZATION) String innerSourceAccessToken, @PathVariable final Long projectId, @RequestParam final String branchName) {
        return branchDetailsService.getBranchDetails(innerSourceAccessToken, projectId, branchName);
    }

    /**
     * Get a list of merge requests for a specific branch within a project.
     *
     * @param innerSourceAccessToken The authorization token obtained from request header.
     * @param projectId              The ID of the project.
     * @param branchName             The name of the branch.
     * @return List of merge requests.
     */
    @GetMapping(BRANCH_MERGE_REQUESTS_ENDPOINT)
    public List<MergeRequest> getAllMergeRequestsBranchWise(@RequestHeader(AUTHORIZATION) final String innerSourceAccessToken, @PathVariable final Long projectId, @RequestParam final String branchName) {
        return branchDetailsService.getAllMergeRequestsBranchWise(innerSourceAccessToken, projectId, branchName);
    }

    /**
     * Get a list of issues for a specific branch within a project.
     *
     * @param innerSourceAccessToken The authorization token obtained from request header.
     * @param projectId              The ID of the project.
     * @param branchName             The name of the branch.
     * @return List of issues.
     */
    @GetMapping(BRANCH_ISSUES_ENDPOINT)
    public List<Issue> getAllIssuesBranchWise(@RequestHeader(AUTHORIZATION) final String innerSourceAccessToken, @PathVariable final Long projectId, @RequestParam final String branchName) {
        return branchDetailsService.getAllIssuesBranchWise(innerSourceAccessToken, projectId, branchName);
    }

    /**
     * Get a list of commits for a specific branch within a project.
     *
     * @param innerSourceAccessToken The authorization token obtained from request header.
     * @param projectId              The ID of the project.
     * @param branchName             The name of the branch.
     * @return List of commits.
     */
    @GetMapping(BRANCH_COMMITS_ENDPOINT)
    public List<Commit> getAllCommitsBranchWise(@RequestHeader(AUTHORIZATION) final String innerSourceAccessToken, @PathVariable final Long projectId, @RequestParam final String branchName) {
        return branchDetailsService.getAllCommitsBranchWise(innerSourceAccessToken, projectId, branchName);
    }
}
