package com.soprabanking.services.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soprabanking.model.dto.BranchDto;
import com.soprabanking.model.dto.ProjectsDto;
import com.soprabanking.services.IBranchService;
import com.soprabanking.constants.Constants;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class BranchService implements IBranchService {

    private static final Logger logger = Logger.getLogger(BranchService.class.getName());
    // Create an HTTP client to send the request
    private final HttpClient client = HttpClient.newHttpClient();
    //ObjectMapper to map the DTO to JSON
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Gets all branches of a repository.
     *
     * @param id           the ID of the repository
     * @param access_token the access token of the user making the request
     * @param pagination   whether to paginate the results
     * @param page         the page number to return, if pagination is enabled
     * @return a map of branch names to branch objects, or a paged map of branch names to branch objects, if pagination is enabled
     */
    public Map<String, Object> getAllBranches(String id, String access_token, Boolean pagination, int page) {

        // Deserialize the JSON response into an array of BranchDto objects
        BranchDto[] branches = this.getBranchesHandler(Constants.INNERSOURCE_PROJECTS_API_BASE_URL + id + Constants.INNERSOURCE_BRANCHES_API, access_token);

        this.attachMergeUrl(id, access_token, branches);

        Map<String, Object> branchesMap = (pagination) ? this.getPagedBranches(branches, page) : this.convertBranchesArrayToMap(branches, branches.length);
        return branchesMap;
    }

    /**
     * Fetches branches from the API using an HTTP GET request.
     *
     * @param Url          the URL of the API endpoint to fetch branches from
     * @param access_token the access token of the user making the request
     * @return an array of BranchDto objects, or null if an error occurs
     */
    private BranchDto[] getBranchesHandler(String Url, String access_token) {
        try {
            // Create an HTTP GET request to fetch branches from the API
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Url))
                    .header(Constants.HEADER_KEY, access_token)
                    .GET()
                    .build();

            // Send the HTTP request and retrieve the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response != null) {
                // Deserialize the JSON response into an array of BranchDto objects
                return this.objectMapper.readValue(response.body(), BranchDto[].class);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception occurred", e);
        }
        // Return null if error occurs
        return null;
    }

    /**
     * Retrieves a paged list of BranchDto objects from the provided array based on the specified page number.
     *
     * @param branches The array of BranchDto objects.
     * @param page     The page number.
     * @return A map containing a page of BranchDto objects and the total length of the original array.
     */
    public Map<String, Object> getPagedBranches(BranchDto[] branches, int page) {

        int start = page * Constants.NUMBER_OF_BRANCHES_PER_PAGE;
        int end = Math.min(start + Constants.NUMBER_OF_BRANCHES_PER_PAGE, branches.length);

        // If Branches array is empty, 'null' will be returned
        if (start >= branches.length) {
            return null;
        }

        BranchDto[] pageBranches = Arrays.copyOfRange(branches, start, end);

        return this.convertBranchesArrayToMap(pageBranches, branches.length);
    }


    /**
     * Attaches a merge URL to each branch in the given array.
     * <p>
     * The merge URL is calculated using the following formula:
     * `Constants.BRANCH_SERVICE_URI_FIRST + projectPath + Constants.BRANCH_SERVICE_URI_FIFTH + branchName`
     * <p>
     * The project path is fetched from the projects API using the given access token.
     *
     * @param id           the ID of the repository
     * @param access_token the access token of the user making the request
     * @param branches     the array of branches to attach merge URLs to
     */
    public void attachMergeUrl(String id, String access_token, BranchDto[] branches) {
        try {
            // Request to projects api to fetch the project path.
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Constants.INNERSOURCE_PROJECTS_API_BASE_URL + id))
                    .header(Constants.HEADER_KEY, access_token)
                    .GET()
                    .build();

            HttpResponse<String> response = this.client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response != null) {
                // Deserialize JSON into a java object while ignoring unwanted properties, which in this case is "id".
                this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                ProjectsDto project = this.objectMapper.readValue(response.body(), ProjectsDto.class);

                for (BranchDto branch : branches) {
                    branch.setMergeUrl(Constants.INNERSOURCE_URL + project.getProjectPath() + Constants.INNERSOURCE_MERGE_REQUEST_PAGE_URL + branch.getName());
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception occurred", e);
        }
    }


    /**
     * Gets all branches of a repository that match the given search name.
     * The search name is used to filter the branches returned by the API.
     *
     * @param id           the ID of the repository
     * @param access_token the access token of the user making the request
     * @param page         the page number to return, if pagination is enabled
     * @param searchName   the search name to use to filter the branches
     * @return a map of branch names to branch objects, or a paged map of branch names to branch objects, if pagination is enabled
     */
    public Map<String, Object> getSearchedBranches(String id, String access_token, int page, String searchName) {

        BranchDto[] branches = this.getBranchesHandler(Constants.INNERSOURCE_PROJECTS_API_BASE_URL + id + Constants.INNERSOURCE_BRANCHES_API_SEARCH_PARAM + searchName, access_token);

        this.attachMergeUrl(id, access_token, branches);

        Map<String, Object> branchesMap = this.getPagedBranches(branches, page);
        return branchesMap;
    }


    /**
     * Gets all branches of a repository that match the given filters.
     * <p>
     * The following filters are supported:
     * <p>
     * * `isMerged`: Whether the branch is merged or not.
     * * `isProtected`: Whether the branch is protected or not.
     * * `isDefault`: Whether the branch is the default branch or not.
     * * `developersCanPush`: Whether developers can push to the branch or not.
     * * `developersCanMerge`: Whether developers can merge to the branch or not.
     * <p>
     * If a filter is not specified, all branches that match the remaining filters will be returned.
     *
     * @param id                 the ID of the repository
     * @param access_token       the access token of the user making the request
     * @param page               the page number to return, if pagination is enabled
     * @param isMerged           whether the branch is merged or not
     * @param isProtected        whether the branch is protected or not
     * @param isDefault          whether the branch is the default branch or not
     * @param developersCanPush  whether developers can push to the branch or not
     * @param developersCanMerge whether developers can merge to the branch or not
     * @return a map of branch names to branch objects, or a paged map of branch names to branch objects, if pagination is enabled
     */
    public Map<String, Object> getFilteredBranches(String id, String access_token, int page, Boolean isMerged, Boolean isProtected, Boolean isDefault, Boolean developersCanPush, Boolean developersCanMerge) {

        BranchDto[] allBranches = this.getBranchesHandler(Constants.INNERSOURCE_PROJECTS_API_BASE_URL + id + Constants.INNERSOURCE_BRANCHES_API, access_token);

        BranchDto[] matchedBranches = Arrays.stream(allBranches)
                .filter(obj ->
                        (isMerged == null || obj.getMerged().equals(isMerged)) &&
                                (isProtected == null || obj.getIsProtected().equals(isProtected)) &&
                                (isDefault == null || obj.getIsDefault().equals(isDefault)) &&
                                (developersCanPush == null || obj.getDevelopersCanPush().equals(developersCanPush)) &&
                                (developersCanMerge == null || obj.getDevelopersCanMerge().equals(developersCanMerge)))
                .toArray(BranchDto[]::new);

        this.attachMergeUrl(id, access_token, matchedBranches);

        Map<String, Object> branchesMap = this.getPagedBranches(matchedBranches, page);

        return branchesMap;
    }

    /**
     * Gets all branches of a repository that were created between the given start and end dates.
     * <p>
     * The start and end dates must be in the format `yyyy-MM-ddTHH:mm:ssZ`.
     *
     * @param id           the ID of the repository
     * @param access_token the access token of the user making the request
     * @param page         the page number to return, if pagination is enabled
     * @param startDate    the start date in the format `yyyy-MM-ddTHH:mm:ssZ`
     * @param endDate      the end date in the format `yyyy-MM-ddTHH:mm:ssZ`
     * @return a map of branch names to branch objects, or a paged map of branch names to branch objects, if pagination is enabled
     */
    public Map<String, Object> timeRangeBranches(String id, String access_token, int page, String startDate, String endDate) {

        BranchDto[] allBranches = this.getBranchesHandler(Constants.INNERSOURCE_PROJECTS_API_BASE_URL + id + Constants.INNERSOURCE_BRANCHES_API, access_token);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_TIME_PATTERN);
        LocalDateTime startRange = LocalDateTime.parse(startDate, formatter);
        LocalDateTime endRange = LocalDateTime.parse(endDate, formatter);

        BranchDto[] matchedBranches = Arrays.stream(allBranches)
                .filter(obj -> (LocalDateTime.parse(obj.getCommitCommittedAt(), formatter).isAfter(startRange) &&
                        LocalDateTime.parse(obj.getCommitCommittedAt(), formatter).isBefore(endRange)))
                .toArray(BranchDto[]::new);

        this.attachMergeUrl(id, access_token, matchedBranches);

        Map<String, Object> branchesMap = this.getPagedBranches(matchedBranches, page);

        return branchesMap;
    }


    /**
     * Converts an array of BranchDto objects into a map.
     * <p>
     * The map contains two key-value pairs:
     * <p>
     * * `Constants.BRANCH_MAP_FIELD_FIRST`: An array of BranchDto objects.
     * * `Constants.BRANCH_MAP_FIELD_SECOND`: The number of BranchDto objects in the array.
     *
     * @param branches    the array of BranchDto objects to convert
     * @param totalLength the length of the array without pagination
     * @return a map containing the array of BranchDto objects and the number of BranchDto objects in the array
     */
    private Map<String, Object> convertBranchesArrayToMap(BranchDto[] branches, int totalLength) {
        Map<String, Object> map = new HashMap<>();
        map.put(Constants.BRANCH_MAP_FIELD_FIRST, branches);
        map.put(Constants.MAP_TOTAL_LENGTH_FIELD, totalLength);
        return map;
    }
}