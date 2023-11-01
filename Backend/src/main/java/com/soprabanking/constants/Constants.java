package com.soprabanking.constants;

import jakarta.servlet.http.PushBuilder;

public class Constants {
    public static final String HEADER_KEY = "Authorization";
    public static final String INNERSOURCE_URL = "https://innersource.soprasteria.com/";
    public static final String INNERSOURCE_BASE_API_URL = INNERSOURCE_URL + "api/v4/";

    // Members Service Constants
    public static final String INNERSOURCE_PROJECTS_API_BASE_URL = INNERSOURCE_BASE_API_URL + "projects/";
    public static final String GROUP_INNERSOURCE_URL = INNERSOURCE_BASE_API_URL + "groups?";
    // Repository Service Constants
    public static final String REPOSITORY_SERVICE_URI = INNERSOURCE_BASE_API_URL + "projects?membership=true&order_by=last_activity_at&sort=desc";
    // Merge Service Constants
    public static final String mergeServiceURIFirst = INNERSOURCE_BASE_API_URL + "projects/";
    public static final String PROJECT_ID_PATH_VARIABLE = "id";
    public static final String DEFAULT_PAGE = "0";
    public static final String EMPTY_STRING = "";
    public static final String MAP_TOTAL_LENGTH_FIELD = "totalLength";
    public static final String PAGE_PARAM = "?page=";
    public static final String PER_PAGE_PARAM = "&per_page=15";
    public static final String innersourceAPIversion = "api/v4/projects/";

    public static final String GROUP_SEARCH_PARAM_TEXT = "search";
    public static final String SORT_PARAM_TEXT = "sort";
    public static final String ORDER_BY_PARAM_TEXT = "order_by";
    public static final String DESC_TEXT = "desc";
    public static final String EQUAL_TEXT = "=";
    public static final String AMPERSAND_TEXT = "&";
    public static final String DATA_FETCHING_EXCEPTION_MESSAGE = "Unable to fetch data, try again later.";
    public static final String GROUPS_PER_PAGE_PARAM = "per_page";
    public static final String PAGE_PARAM_TEXT = "page";
    public static final String GROUPS_PAGINATED_VALUE = "isPaginated";
    public static final String GROUPS_PER_PAGE_VALUE = "per_page";
    public static final String GROUPS_PER_PAGE_DEFAULT_VALUE = "15";
    public static final String GROUPS_PAGE_VALUE = "page";
    public static final String GROUPS_PAGE_DEFAULT_VALUE = "1";
    public static final String GROUPS_TEXT = "groups";
    public static final String X_TOTAL_TEXT = "x-total";
    public static final String GROUPS_SORT_VALUE = "sort";
    public static final String GROUPS_ORDER_BY_VALUE = "order_by";
    public static final String GROUPS_SORT_DEFAULT_VALUE = "asc";
    public static final String GROUP_CONTROLLER_REQ_MAPPING = "/groups";
    // Branch Controller Constants
    public static final String CROSS_ORIGIN_URL = "http://localhost:4200";
    public static final String BRANCH_CONTROLLER_REQ_MAPPING = "/repositories/{id}/branches";
    public static final String BRANCH_CONTROLLER_GET_MAPPING_SEARCH = "/search";
    public static final String BRANCH_CONTROLLER_GET_MAPPING_FILTER = "/filter";
    public static final String IS_MERGED_VALUE = "merged";
    public static final String IS_PROTECTED_VALUE = "protected";
    public static final String IS_DEFAULT_VALUE = "default";
    public static final String DEV_CAN_PUSH_VALUE = "developers_can_push";
    public static final String DEV_CAN_MERGE_VALUE = "developers_can_merge";
    public static final boolean BRANCH_CONTROLLER_FILTER_REQUIRED = false;
    public static final String PAGINATION_DEFAULT_VALUE = "true";
    public static final String SEARCH_PARAMETER_NAME = "searchBranch";
    public static final String CALENDAR_PARAMETER_START = "start_date";
    public static final String BRANCH_CONTROLLER_GET_MAPPING_CALENDAR = "/calendar";
    public static final String CALENDAR_PARAMETER_END = "end_date";
    // Repository Controller Constants
    public static final String REPOSITORY_CONTROLLER_REQ_MAPPING = "/repositories";
    public static final String REPOSITORY_CONTROLLER_GET_MAPPING = "/getdropdownrepos";
    // Branch Service Constants
    public static final String INNERSOURCE_BRANCHES_API = "/repository/branches/";
    public static final String INNERSOURCE_BRANCHES_API_SEARCH_PARAM = INNERSOURCE_BRANCHES_API + "?search=";
    public static final String INNERSOURCE_MERGE_REQUEST_PAGE_URL = "/-/merge_requests/new?merge_request%5Bsource_branch%5D=";
    public static final int NUMBER_OF_BRANCHES_PER_PAGE = 15;
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
    public static final String BRANCH_MAP_FIELD_FIRST = "branches";
    // Members Controller Constants
    public static final String MEMBERS_CONTROLLER_REQ_MAPPING = "/projects/{id}/members";
    public static final String MEMBER_API_URL = "/members/all";

    public static final String MEMBERS_MAP_FIELD_FIRST = "members";
    public static final String MEMBERS_QUERY_PARAM = "&query=";
    public static final String MEMBERS_STATE_PARAM = "&state=";
    public static final String MEMBERS_API_TOTAL_MEMBERS_HEADER = "x-total";
    public static final String MEMBERS_PER_PAGE_PARAM = "&per_page=15";

    //Merge Controller Constants

    public static final String MERGE_CONTROLLER_REQ_MAPPING = "/merge";
    public static final String MERGE_CONTROLLER_FETCH = "/fetch";


    // Merge Service Constants
    public static final String MERGE_SERVICE_URI_FIRST = "https://innersource.soprasteria.com/api/v4/projects/";

    public static final String MERGE_AUTHORIZATION_CONST1 = "Authorization";

    public static final String MERGE_SERVICE_URI_SECOND = "/merge_requests";
    public static final String FILTERED_MERGE_REQUEST_MAPPING = "/filteredMergeRequest";

    // Commit Service Constants
    public static final String COMMIT_FETCH_URI = "/repository/commits?ref_name=";
    public static final String GET_COMMIT_REQUEST_MAPPING ="/commits";

    public static final String GET_COMMIT_TOTAL_NUMBER = "/totalcommits";
    // Notification Service Constants
    public static final String SUBJECT= "Gentle Reminder: Please Close the Open Merge Request";
    public static final String EMAIL_BODY0 = "Dear ";

    public static final String EMAIL_BODY1 = ",<p>We have an open merge request with this ID that requires your attention. Kindly review and close it at your earliest convenience to keep our workflow on track.</p>";
    public static final String EMAIL_BODY2 = "<p>Merged Request Id: ";
    public static final String EMAIL_BODY3 = "<p>Here is the link to open this Merge Request: ";

    public static final String EMAIL_BODY4 = "<p>Regards,</p>Git Toolchain Integration Team";

    public static final String END_PARA = "</p>";

//    Notification Endpoint
    public static final String NOTIFICATION_CONTROLLER_REQ_MAPPING = "/notification";
    public static final String NOTIFICATION_SEND = "/send";

    public static final String DOMAIN_NAME = "@soprabanking.com";

    public static final String EXCEPTION_MESSAGE = "Exception Occurred ";

    public static final String MAIL_SENT_SUCCESS_MESSAGE = "Mail sent successfully";
    public static final String MAIL_SENT_FAILED_MESSAGE ="Mail could not be sent.";
   
    // Member DTO Constants
    public static final String ACCESS_LEVEL = "access_level";
    public static final String CREATED_AT = "created_at";
    public static final String CREATED_BY = "created_by";
    public static final String EXPIRES_AT = "expires_at";
    public static final String AVATAR_URL = "avatar_url";
    public static final String WEB_URL = "web_url";
    public static final String MEMBERSHIP_STATE = "membership_state";
    public static final String NAME = "name";
    public static final String USERNAME = "user" + NAME;
    public static final String STATE = "state";

    // Pipeline Constants

    public static final String PIPELINES_GET_ALL_URL = "/pipelines/";
    public static final String PIPELINE_MAP_FIELD_FIRST = "pipelines";
    public static final String PROJECT_WISE_PIPELINES_API = "/projects/{id}/pipelines";

    public static final String GET_TOTAL_COMMIT_NUMBER = "/totalcommits";
    public static final String GET_TOTAL_COMMITS_NUMBER_API1 = "/repository/commits?page=";
    public static final String GET_TOTAL_COMMITS_NUMBER_API2 = "&per_page=";

    public static final String GET_LATEST_COMMITS ="/latestcommits";
    public static final String COMMITS_SINCE_URI = "&since=";

    public static final String STATUS_OPENED = "opened";
    public static final String STATUS_MERGED = "merged";


}