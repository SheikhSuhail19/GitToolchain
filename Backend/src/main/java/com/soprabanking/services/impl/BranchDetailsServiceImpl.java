package com.soprabanking.services.impl;

import com.soprabanking.model.Branch;
import com.soprabanking.model.Commit;
import com.soprabanking.model.Issue;
import com.soprabanking.model.MergeRequest;
import com.soprabanking.services.BranchDetailsService;
import com.soprabanking.utility.InnerSourceApiCallerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.soprabanking.constants.InnerSourceClientUrlConstants.ALL_BRANCHES_API;
import static com.soprabanking.constants.InnerSourceClientUrlConstants.BRANCH_NAME;
import static com.soprabanking.constants.InnerSourceClientUrlConstants.BRANCH_WISE_COMMITS_API;
import static com.soprabanking.constants.InnerSourceClientUrlConstants.BRANCH_WISE_ISSUES_API;
import static com.soprabanking.constants.InnerSourceClientUrlConstants.BRANCH_WISE_MERGE_REQUESTS_API;
import static com.soprabanking.constants.InnerSourceClientUrlConstants.PROJECT_ID;
import static com.soprabanking.constants.InnerSourceClientUrlConstants.SINGLE_BRANCH_DETAIL_API;


/**
 * Implementation of the {@link BranchDetailsService} service for handling branch-related operations.
 */
@Service
public class BranchDetailsServiceImpl implements BranchDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BranchDetailsServiceImpl.class);

    private final InnerSourceApiCallerUtil innerSourceApiCallerUtil;

    public BranchDetailsServiceImpl(InnerSourceApiCallerUtil innerSourceApiCallerUtil) {
        this.innerSourceApiCallerUtil = innerSourceApiCallerUtil;
    }

    /**
     * Retrieves details for all branches of a specific project.
     *
     * @param innerSourceAccessToken The authorization token obtained from request header.
     * @param projectId              The ID of the project to retrieve branches for.
     * @return A list of branch details.
     */
    @Override
    public List<Branch> getAllBranches(String innerSourceAccessToken, Long projectId) {
        final String allBranchesApiUrl = ALL_BRANCHES_API
                .replace(PROJECT_ID, projectId.toString());

        // Log the API call
        LOGGER.info("Fetching all Branches for given Project ID");

        return innerSourceApiCallerUtil.callApi(innerSourceAccessToken, allBranchesApiUrl, Branch.class);
    }

    /**
     * Retrieves details for a specific branch within a project.
     *
     * @param innerSourceAccessToken The authorization token obtained from request header.
     * @param projectId              The ID of the project to retrieve the branch from.
     * @param branchName             The name of the branch to retrieve details for.
     * @return A list of branch details.
     */
    @Override
    public List<Branch> getBranchDetails(final String innerSourceAccessToken, Long projectId, final String branchName) {
        final String branchDetailsApiUrl = SINGLE_BRANCH_DETAIL_API
                .replace(PROJECT_ID, projectId.toString())
                .replace(BRANCH_NAME, branchName);

        // Log the API call
        LOGGER.info("Fetching Branch details for given Project ID & Branch Name");

        return innerSourceApiCallerUtil.callApi(innerSourceAccessToken, branchDetailsApiUrl, Branch.class);
    }

    /**
     * Retrieves all merge requests for a specific branch within a project.
     *
     * @param innerSourceAccessToken The authorization token obtained from request header.
     * @param projectId              The ID of the project to retrieve merge requests from.
     * @param branchName             The name of the branch to retrieve merge requests for.
     * @return A list of merge requests.
     */
    @Override
    public List<MergeRequest> getAllMergeRequestsBranchWise(final String innerSourceAccessToken, final Long projectId, final String branchName) {
        final String branchWiseMergeRequestsApiUrl = BRANCH_WISE_MERGE_REQUESTS_API
                .replace(PROJECT_ID, projectId.toString())
                .replace(BRANCH_NAME, branchName);

        // Log the API call
        LOGGER.info("Fetching all Merge Requests for given Project ID & Branch Name");

        return innerSourceApiCallerUtil.callApi(innerSourceAccessToken, branchWiseMergeRequestsApiUrl, MergeRequest.class);
    }

    /**
     * Retrieves all issues for a specific branch within a project.
     *
     * @param innerSourceAccessToken The authorization token obtained from request header.
     * @param projectId              The ID of the project to retrieve issues from.
     * @param branchName             The name of the branch to retrieve issues for.
     * @return A list of issues.
     */
    @Override
    public List<Issue> getAllIssuesBranchWise(final String innerSourceAccessToken, final Long projectId, final String branchName) {
        final String branchWiseIssuesApiUrl = BRANCH_WISE_ISSUES_API
                .replace(PROJECT_ID, projectId.toString())
                .replace(BRANCH_NAME, branchName);

        // Log the API call
        LOGGER.info("Fetching all Issues for given Project ID & Branch Name");

        return innerSourceApiCallerUtil.callApi(innerSourceAccessToken, branchWiseIssuesApiUrl, Issue.class);
    }

    /**
     * Retrieves all commits for a specific branch within a project.
     *
     * @param innerSourceAccessToken The authorization token obtained from request header.
     * @param projectId              The ID of the project to retrieve commits from.
     * @param branchName             The name of the branch to retrieve commits for.
     * @return A list of commits.
     */
    @Override
    public List<Commit> getAllCommitsBranchWise(final String innerSourceAccessToken, final Long projectId, final String branchName) {
        final String branchWiseCommitsApiUrl = BRANCH_WISE_COMMITS_API
                .replace(PROJECT_ID, projectId.toString())
                .replace(BRANCH_NAME, branchName);

        // Log the API call
        LOGGER.info("Fetching all Commits for given Project ID & Branch Name");

        return innerSourceApiCallerUtil.callApi(innerSourceAccessToken, branchWiseCommitsApiUrl, Commit.class);
    }
}
