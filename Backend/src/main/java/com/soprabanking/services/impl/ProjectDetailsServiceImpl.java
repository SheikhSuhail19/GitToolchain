package com.soprabanking.services.impl;


import com.soprabanking.model.Commit;
import com.soprabanking.model.Issue;
import com.soprabanking.model.Member;
import com.soprabanking.model.MergeRequest;
import com.soprabanking.model.Project;
import com.soprabanking.services.ProjectDetailsService;
import com.soprabanking.utility.InnerSourceApiCallerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.soprabanking.constants.InnerSourceClientUrlConstants.ALL_PROJECTS_API;
import static com.soprabanking.constants.InnerSourceClientUrlConstants.PROJECT_ID;
import static com.soprabanking.constants.InnerSourceClientUrlConstants.PROJECT_WISE_COMMITS_API;
import static com.soprabanking.constants.InnerSourceClientUrlConstants.PROJECT_WISE_ISSUES_API;
import static com.soprabanking.constants.InnerSourceClientUrlConstants.PROJECT_WISE_MEMBERS_API;
import static com.soprabanking.constants.InnerSourceClientUrlConstants.PROJECT_WISE_MERGE_REQUESTS_API;
import static com.soprabanking.constants.InnerSourceClientUrlConstants.SINGLE_PROJECT_DETAIL_API;


/**
 * Implementation of the {@link ProjectDetailsService} interface for retrieving project-related details.
 */
@Service
public class ProjectDetailsServiceImpl implements ProjectDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectDetailsServiceImpl.class);

    private final InnerSourceApiCallerUtil innerSourceApiCallerUtil;

    /**
     * Constructor to initialize the service with the {@link InnerSourceApiCallerUtil}.
     *
     * @param innerSourceApiCallerUtil The utility for making InnerSource API calls.
     */
    public ProjectDetailsServiceImpl(InnerSourceApiCallerUtil innerSourceApiCallerUtil) {
        this.innerSourceApiCallerUtil = innerSourceApiCallerUtil;
    }

    /**
     * Retrieves a list of all projects.
     *
     * @param innerSourceAccessToken The access token for authentication.
     * @return A list of all projects.
     */
    @Override
    public List<Project> getAllProjects(final String innerSourceAccessToken) {
        final String allProjectsApiUrl = ALL_PROJECTS_API;

        // Log the API call
        LOGGER.info("Fetching all projects.");

        return innerSourceApiCallerUtil.callApi(innerSourceAccessToken, allProjectsApiUrl, Project.class);
    }

    /**
     * Retrieves details for a specific project.
     *
     * @param innerSourceAccessToken The access token for authentication.
     * @param projectId              The ID of the project to retrieve details for.
     * @return A list of project details.
     */
    @Override
    public List<Project> getProjectDetails(final String innerSourceAccessToken, final Long projectId) {
        final String projectDetailsApiUrl = SINGLE_PROJECT_DETAIL_API
                .replace(PROJECT_ID, String.valueOf(projectId));

        // Log the API call
        LOGGER.info("Fetching Project details for given Project ID");

        return innerSourceApiCallerUtil.callApi(innerSourceAccessToken, projectDetailsApiUrl, Project.class);
    }

    /**
     * Retrieves a list of all merge requests for a specific project.
     *
     * @param innerSourceAccessToken The access token for authentication.
     * @param projectId              The ID of the project to retrieve merge requests for.
     * @return A list of merge requests for the project.
     */
    @Override
    public List<MergeRequest> getAllMergeRequestsProjectWise(final String innerSourceAccessToken, final Long projectId) {
        final String projectWiseMergeRequestsApiUrl = PROJECT_WISE_MERGE_REQUESTS_API
                .replace(PROJECT_ID, String.valueOf(projectId));

        // Log the API call
        LOGGER.info("Fetching Merge Requests for given Project ID");

        return innerSourceApiCallerUtil.callApi(innerSourceAccessToken, projectWiseMergeRequestsApiUrl, MergeRequest.class);
    }

    /**
     * Retrieves a list of all issues for a specific project.
     *
     * @param innerSourceAccessToken The access token for authentication.
     * @param projectId              The ID of the project to retrieve issues for.
     * @return A list of issues for the project.
     */
    @Override
    public List<Issue> getAllIssuesProjectWise(final String innerSourceAccessToken, final Long projectId) {
        final String projectWiseIssuesApiUrl = PROJECT_WISE_ISSUES_API
                .replace(PROJECT_ID, String.valueOf(projectId));

        // Log the API call
        LOGGER.info("Fetching Issues for given Project ID");

        return innerSourceApiCallerUtil.callApi(innerSourceAccessToken, projectWiseIssuesApiUrl, Issue.class);
    }

    /**
     * Retrieves a list of all commits for a specific project.
     *
     * @param innerSourceAccessToken The access token for authentication.
     * @param projectId              The ID of the project to retrieve commits for.
     * @return A list of commits for the project.
     */
    @Override
    public List<Commit> getAllCommitsProjectWise(final String innerSourceAccessToken, final Long projectId) {
        final String projectWiseCommitsApiUrl = PROJECT_WISE_COMMITS_API
                .replace(PROJECT_ID, String.valueOf(projectId));

        // Log the API call
        LOGGER.info("Fetching Commits for given Project ID");

        return innerSourceApiCallerUtil.callApi(innerSourceAccessToken, projectWiseCommitsApiUrl, Commit.class);
    }

    /**
     * Retrieves a list of all members associated with a specific project.
     *
     * @param innerSourceAccessToken The access token for authentication.
     * @param projectId              The ID of the project to retrieve members for.
     * @return A list of members for the project.
     */
    @Override
    public List<Member> getAllMembersProjectWise(final String innerSourceAccessToken, final Long projectId) {
        final String projectWiseMembersApiUrl = PROJECT_WISE_MEMBERS_API
                .replace(PROJECT_ID, String.valueOf(projectId));

        // Log the API call
        LOGGER.info("Fetching Members for given Project ID");

        return innerSourceApiCallerUtil.callApi(innerSourceAccessToken, projectWiseMembersApiUrl, Member.class);
    }
}

