package com.soprabanking.services;

import com.soprabanking.model.Commit;
import com.soprabanking.model.Issue;
import com.soprabanking.model.Member;
import com.soprabanking.model.MergeRequest;
import com.soprabanking.model.Project;

import java.util.List;

/**
 * Service interface for retrieving project-related details from InnerSource.
 */
public interface ProjectDetailsService {

    /**
     * Retrieves a list of all projects.
     *
     * @param innerSourceAccessToken The access token for authentication.
     * @return A list of all projects.
     */
    List<Project> getAllProjects(final String innerSourceAccessToken);

    /**
     * Retrieves details for a specific project.
     *
     * @param innerSourceAccessToken The access token for authentication.
     * @param projectId              The ID of the project to retrieve details for.
     * @return A list of project details.
     */
    List<Project> getProjectDetails(final String innerSourceAccessToken, final Long projectId);

    /**
     * Retrieves a list of all merge requests for a specific project.
     *
     * @param innerSourceAccessToken The access token for authentication.
     * @param projectId              The ID of the project to retrieve merge requests for.
     * @return A list of merge requests for the project.
     */
    List<MergeRequest> getAllMergeRequestsProjectWise(final String innerSourceAccessToken, final Long projectId);

    /**
     * Retrieves a list of all issues for a specific project.
     *
     * @param innerSourceAccessToken The access token for authentication.
     * @param projectId              The ID of the project to retrieve issues for.
     * @return A list of issues for the project.
     */
    List<Issue> getAllIssuesProjectWise(final String innerSourceAccessToken, final Long projectId);

    /**
     * Retrieves a list of all commits for a specific project.
     *
     * @param innerSourceAccessToken The access token for authentication.
     * @param projectId              The ID of the project to retrieve commits for.
     * @return A list of commits for the project.
     */
    List<Commit> getAllCommitsProjectWise(final String innerSourceAccessToken, final Long projectId);

    /**
     * Retrieves a list of all members associated with a specific project.
     *
     * @param innerSourceAccessToken The access token for authentication.
     * @param projectId              The ID of the project to retrieve members for.
     * @return A list of members for the project.
     */
    List<Member> getAllMembersProjectWise(final String innerSourceAccessToken, final Long projectId);
}

