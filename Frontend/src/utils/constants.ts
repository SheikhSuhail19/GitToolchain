export const BASE_URL = 'http://localhost:8080';

export const SEARCH_BRANCH_TEXT = 'Search by Name';
export const SELECT_REPO_TEXT = 'Select Repository';

export const BOOLEAN_OPTIONS = ['None', 'Yes', 'No'];
export const SORTING_OPTIONS = ['Name', 'Id'];
export const DEFAULT_SORT_VALUE = 'asc';
export const DEFAULT_ORDERBY_VALUE = 'name';
export const DIFFERENT_SORT_VALUE = 'desc';
export const REPOSITORY_ENDPOINT = '/repositories/';
export const BRANCHES_ENDPOINT = '/branches';
export const BRANCHES_OVERVIEW = '/overview';
export const PROJECT_ID = 329389; //THIS WILL BE DYNAMIC ONCE THE REPOSITORY CONTROLLER IS CONFIGURED WITH THE APPLICATION
export const NAVIGATE_TO_COMMITS = '/home/commits/commits';
export const SHA_COPY_ALERT = 'SHA copied to clipboard : ';
export const TEXT_AREA = 'textarea';
export const COPY = 'copy';
export const COMMITSPROJECT = '/commits?projectId=';
export const BRANCHPARAM = '&branchName=';
export const PRIVATETOKENPARAM = '&privateToken=';
export const ISSUE_PAGE_ENDPOINT = '/issues/filteredIssues';

// Merge Service Constants
export const MERGEURL = '/merge/fetch?id=';

//Notification Service Constants
export const NOTIFICATIONURL = '/merge/filteredMergeRequest?projectId='
export const MAILSENDINGURL = '/notification/send?id='

//Commit Service Constants
export const TOTALCOMMITSINPROJECT = '/totalcommits?projectId=';

export const ROLE_DESCRIPTIONS_NO_ACCESS = 'No access';
export const ROLE_DESCRIPTIONS_MINIMAL_ACCESS = 'Minimal access';
export const ROLE_DESCRIPTIONS_GUEST = 'Guest';
export const ROLE_DESCRIPTIONS_REPORTER = 'Reporter';
export const ROLE_DESCRIPTIONS_DEVELOPER = 'Developer';
export const ROLE_DESCRIPTIONS_MAINTAINER = 'Maintainer';
export const ROLE_DESCRIPTIONS_OWNER = 'Owner';

export const PRIMARY_TEXT = 'primary';
export const SUCCESS_TEXT = 'success';
export const INFO_TEXT = 'info';
export const WARNING_TEXT = 'warning';
export const DANGER_TEXT = 'danger';
export const FAILED_TEXT = 'failed';

export const PROJECTS_TEXT = 'projects';
export const PIPELINES_TEXT = 'pipelines';
export const PIPELINE_AGO_LABEL = 'ago';
export const PIPELINE_HOUR_LABEL = 'hour';
export const PIPELINE_MINUTE_LABEL = 'minute';
export const PIPELINE_SECOND_LABEL = 'second';

export const MR_TEXT = 'MR';
export const NOTIFICATION_TEXT = 'noti';
export const GET_MR_COUNT_TEXT = 'getMrCount';
export const DATA_PARAM = 'data';

export const ISSUE_TEXT = 'issues'
export const GET_ISSUE_TEXT = 'issueCount'
export const MESSAGE_REVIEWER = 'Hello, {Reviewer} Please review the pending merge requests that have been open for more than 7 days.';
export const EMAIL_REVIEWER = `Dear Reviewer, \nWe have an open merge request with this ID that requires your attention. Kindly review and close it at your earliest convenience to keep our workflow on track. \n\nMerged Request Id: <Merge Request ID> \n Here is the link to open this Merge Request: <Merge URL> \n\nRegards, \nGit Toolchain Integration Team`;

export const SOPRA_BANKING_EMAIL = '@soprabanking.com';
export const DEFAULT_MESSAGE_HEY = 'Hey ';

export const BRANCHES_TEXT = 'Branches';

export const PENDING_MERGE_REQUESTS = "Pending Merge Requests";
export const IS_PENDING = " is pending from ";
export const WITH_ID = " with Id: ";
export const YOUR_MR = "Your MR "
export const REPO_CONTRIBUTORS = "/repositories/contributors"


