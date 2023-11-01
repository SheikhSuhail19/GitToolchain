package com.soprabanking.controllers;

import com.soprabanking.model.Commit;
import com.soprabanking.model.Issue;
import com.soprabanking.model.Member;
import com.soprabanking.model.MergeRequest;
import com.soprabanking.model.Project;
import com.soprabanking.services.ProjectDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.soprabanking.constants.ApiUrlConstants.ALL_PROJECTS_ENDPOINT;
import static com.soprabanking.constants.ApiUrlConstants.AUTHORIZATION;
import static com.soprabanking.constants.ApiUrlConstants.BASE_URL_ENDPOINT;
import static com.soprabanking.constants.ApiUrlConstants.PROJECT_COMMITS_ENDPOINT;
import static com.soprabanking.constants.ApiUrlConstants.PROJECT_DETAILS_ENDPOINT;
import static com.soprabanking.constants.ApiUrlConstants.PROJECT_ISSUES_ENDPOINT;
import static com.soprabanking.constants.ApiUrlConstants.PROJECT_MEMBERS_ENDPOINT;
import static com.soprabanking.constants.ApiUrlConstants.PROJECT_MERGE_REQUESTS_ENDPOINT;

/**
 * Controller for handling project-related endpoints.
 */
@RestController
@RequestMapping(BASE_URL_ENDPOINT)
public class ProjectDetailsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectDetailsController.class);
    private final ProjectDetailsService projectDetailsService;

    public ProjectDetailsController(ProjectDetailsService projectDetailsService) {
        this.projectDetailsService = projectDetailsService;
    }

    /**
     * Get a list of all projects.
     *
     * @param innerSourceAccessToken The authorization token obtained from request header.
     * @return List of projects.
     */
    @GetMapping(ALL_PROJECTS_ENDPOINT)
    public List<Project> getAllProjects(@RequestHeader(AUTHORIZATION) final String innerSourceAccessToken) {
        return projectDetailsService.getAllProjects(innerSourceAccessToken);
    }

    /**
     * Get details of a specific project by its ID.
     *
     * @param innerSourceAccessToken The authorization token obtained from request header.
     * @param projectId              The ID of the project to retrieve details for.
     * @return List of project details.
     */
    @GetMapping(PROJECT_DETAILS_ENDPOINT)
    public List<Project> getProjectDetails(@RequestHeader(AUTHORIZATION) final String innerSourceAccessToken, @PathVariable final Long projectId) {
        return projectDetailsService.getProjectDetails(innerSourceAccessToken, projectId);
    }

    /**
     * Get a list of merge requests for a specific project.
     *
     * @param innerSourceAccessToken The authorization token obtained from request header.
     * @param projectId              The ID of the project to retrieve merge requests for.
     * @return List of merge requests.
     */
    @GetMapping(PROJECT_MERGE_REQUESTS_ENDPOINT)
    public List<MergeRequest> getAllMergeRequestsProjectWise(@RequestHeader(AUTHORIZATION) final String innerSourceAccessToken, @PathVariable final Long projectId) {
        return projectDetailsService.getAllMergeRequestsProjectWise(innerSourceAccessToken, projectId);
    }

    /**
     * Get a list of issues for a specific project.
     *
     * @param innerSourceAccessToken The authorization token obtained from request header.
     * @param projectId              The ID of the project to retrieve issues for.
     * @return List of issues.
     */
    @GetMapping(PROJECT_ISSUES_ENDPOINT)
    public List<Issue> getAllIssuesProjectWise(@RequestHeader(AUTHORIZATION) final String innerSourceAccessToken, @PathVariable final Long projectId) {
        return projectDetailsService.getAllIssuesProjectWise(innerSourceAccessToken, projectId);
    }

    /**
     * Get a list of commits for a specific project.
     *
     * @param innerSourceAccessToken The authorization token obtained from request header.
     * @param projectId              The ID of the project to retrieve commits for.
     * @return List of commits.
     */
    @GetMapping(PROJECT_COMMITS_ENDPOINT)
    public List<Commit> getAllCommitsProjectWise(@RequestHeader(AUTHORIZATION) final String innerSourceAccessToken, @PathVariable final Long projectId) {
        return projectDetailsService.getAllCommitsProjectWise(innerSourceAccessToken, projectId);
    }

    /**
     * Get a list of members for a specific project.
     *
     * @param innerSourceAccessToken The authorization token obtained from request header.
     * @param projectId              The ID of the project to retrieve members for.
     * @return List of members.
     */
    @GetMapping(PROJECT_MEMBERS_ENDPOINT)
    public List<Member> getAllMembersProjectWise(@RequestHeader(AUTHORIZATION) final String innerSourceAccessToken, @PathVariable final Long projectId) {
        return projectDetailsService.getAllMembersProjectWise(innerSourceAccessToken, projectId);
    }
}