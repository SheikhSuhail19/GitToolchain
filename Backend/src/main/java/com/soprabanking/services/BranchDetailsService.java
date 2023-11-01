package com.soprabanking.services;

import com.soprabanking.model.Branch;
import com.soprabanking.model.Commit;
import com.soprabanking.model.Issue;
import com.soprabanking.model.MergeRequest;

import java.util.List;

/**
 * Service interface for branch-related operations within a project.
 */
public interface BranchDetailsService {

    /**
     * Retrieves details for all branches of a specific project.
     *
     * @param innerSourceAccessToken The authorization token obtained from request header.
     * @param projectId              The ID of the project to retrieve branches for.
     * @return List of branch details.
     */
    List<Branch> getAllBranches(final String innerSourceAccessToken, final Long projectId);

    /**
     * Retrieves details for a specific branch within a project.
     *
     * @param innerSourceAccessToken The authorization token obtained from request header.
     * @param projectId              The ID of the project to retrieve the branch from.
     * @param branchName             The name of the branch to retrieve details for.
     * @return List of branch details.
     */
    List<Branch> getBranchDetails(final String innerSourceAccessToken, final Long projectId, final String branchName);

    /**
     * Retrieves all merge requests for a specific branch within a project.
     *
     * @param innerSourceAccessToken The authorization token obtained from request header.
     * @param projectId              The ID of the project to retrieve merge requests from.
     * @param branchName             The name of the branch to retrieve merge requests for.
     * @return List of merge requests.
     */
    List<MergeRequest> getAllMergeRequestsBranchWise(final String innerSourceAccessToken, final Long projectId, final String branchName);

    /**
     * Retrieves all issues for a specific branch within a project.
     *
     * @param innerSourceAccessToken The authorization token obtained from request header.
     * @param projectId              The ID of the project to retrieve issues from.
     * @param branchName             The name of the branch to retrieve issues for.
     * @return List of issues.
     */
    List<Issue> getAllIssuesBranchWise(final String innerSourceAccessToken, final Long projectId, final String branchName);

    /**
     * Retrieves all commits for a specific branch within a project.
     *
     * @param innerSourceAccessToken The authorization token obtained from request header.
     * @param projectId              The ID of the project to retrieve commits from.
     * @param branchName             The name of the branch to retrieve commits for.
     * @return List of commits.
     */
    List<Commit> getAllCommitsBranchWise(final String innerSourceAccessToken, final Long projectId, final String branchName);
}
