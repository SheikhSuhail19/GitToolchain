package com.soprabanking.constants;

/**
 * Constants for defining API endpoints and headers.
 *
 */
public final class ApiUrlConstants {

    private ApiUrlConstants() { }

    // Extracting Access Token from Client-side API calls
    public static final String AUTHORIZATION = "Authorization";


    // Base URL for project-related API endpoints
    public static final String BASE_URL_ENDPOINT = "/api/team2/v1/projects";

    // Project-related endpoints
    public static final String ALL_PROJECTS_ENDPOINT = "/all/details";
    public static final String PROJECT_DETAILS_ENDPOINT = "/{projectId}/details";
    public static final String PROJECT_MERGE_REQUESTS_ENDPOINT = "/{projectId}/merge_requests";
    public static final String PROJECT_ISSUES_ENDPOINT = "/{projectId}/issues";
    public static final String PROJECT_COMMITS_ENDPOINT = "/{projectId}/commits";
    public static final String PROJECT_MEMBERS_ENDPOINT = "/{projectId}/members";


    // Base URL for branch-related API endpoints
    public static final String BRANCHES_BASE_URL_ENDPOINT = BASE_URL_ENDPOINT + "/{projectId}/branches";

    // Branch-related endpoints
    public static final String ALL_BRANCHES_ENDPOINT = "/all/details";
    public static final String BRANCH_DETAILS_ENDPOINT = "/details";
    public static final String BRANCH_MERGE_REQUESTS_ENDPOINT = "/merge_requests";
    public static final String BRANCH_ISSUES_ENDPOINT = "/issues";
    public static final String BRANCH_COMMITS_ENDPOINT = "/commits";


}
