package com.soprabanking.constants;

/**
 * Constants for defining InnerSource API endpoints and placeholders.
 *
 */
public final class InnerSourceClientUrlConstants {

    private InnerSourceClientUrlConstants() { }

    // Project-related API endpoints
    public static final String ALL_PROJECTS_API = "/projects?membership=true";
    public static final String SINGLE_PROJECT_DETAIL_API = "/projects/{projectId}";
    public static final String PROJECT_WISE_ISSUES_API = "/projects/{projectId}/issues";
    public static final String PROJECT_WISE_MERGE_REQUESTS_API = "/projects/{projectId}/merge_requests";
    public static final String PROJECT_WISE_COMMITS_API = "/projects/{projectId}/repository/commits";
    public static final String PROJECT_WISE_MEMBERS_API = "/projects/{projectId}/members";


    // Branch-related API endpoints
    public static final String ALL_BRANCHES_API = "/projects/{projectId}/repository/branches";
    public static final String SINGLE_BRANCH_DETAIL_API = "/projects/{projectId}/repository/branches/{branchName}";
    public static final String BRANCH_WISE_MERGE_REQUESTS_API = "/projects/{projectId}/merge_requests?source_branch={branchName}";
    public static final String BRANCH_WISE_ISSUES_API = "/projects/{projectId}/issues?source_branch={branchName}";
    public static final String BRANCH_WISE_COMMITS_API = "/projects/{projectId}/repository/commits?ref_name={branchName}";


    // Project ID & Branch Name -> Placeholders
    public static final String PROJECT_ID = "{projectId}";
    public static final String BRANCH_NAME = "{branchName}";


}