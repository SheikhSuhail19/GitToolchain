package com.soprabanking.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soprabanking.model.dto.GroupDto;
import com.soprabanking.services.IGroupService;
import com.soprabanking.constants.Constants;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.soprabanking.constants.Constants.*;

@Service
public class GroupService implements IGroupService {

    private final HttpClient client = HttpClient.newHttpClient();

    private static final Logger logger = Logger.getLogger(GroupService.class.getName());

    //ObjectMapper to map the DTO to JSON
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
    * This method is used to send http get request to gitlab's api for fetching the groups.
    * It is uses several params for filtering the results, these are:
    * @param sort: for sorting list in ascending and descending order, valid values: "asc" and "desc"
    * @param orderBy: It sorts the group list on the basis of name, path, id, similarity.
    * @param isPaginated: It holds "true" for paginated result and "false" for all fetching all groups(max groups that can be fetched=100 due to gitlab's api limitation)
    * @param perPage: It stores the integer value which tells how many groups you want to fetch on a single page
    * @param page: It holds the page number*/
    @Override
    public Map<String, Object> getAllGroups(
            String access_token,
            boolean isPaginated,
            int perPage,
            int page,
            String sort,
            String orderBy)
    {

        StringBuilder apiUrl=new StringBuilder(GROUP_INNERSOURCE_URL);
        if(isPaginated){
            apiUrl.append(GROUPS_PER_PAGE_PARAM)
                    .append(EQUAL_TEXT)
                    .append(perPage)
                    .append(AMPERSAND_TEXT)
                    .append(PAGE_PARAM_TEXT)
                    .append(EQUAL_TEXT)
                    .append(page)
                    .append(AMPERSAND_TEXT);

        }
        if(sort.equals(DESC_TEXT)){
            apiUrl.append(SORT_PARAM_TEXT)
                    .append(EQUAL_TEXT)
                    .append(sort)
                    .append(AMPERSAND_TEXT);

        }
        if(orderBy!=null){
            apiUrl.append(ORDER_BY_PARAM_TEXT)
                    .append(EQUAL_TEXT)
                    .append(orderBy)
                    .append(AMPERSAND_TEXT);
        }


        try {

            // Create an HTTP GET request to fetch groups from the API
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl.toString()))
                    .header(Constants.HEADER_KEY, access_token)
                    .GET()
                    .build();

            // Send the HTTP request and retrieve the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response != null) {
                Map<String, Object> resp=new HashMap<>();
                resp.put(GROUPS_TEXT,this.objectMapper.readValue(response.body(), GroupDto[].class));
                resp.put(MAP_TOTAL_LENGTH_FIELD,response.headers().firstValue(Constants.X_TOTAL_TEXT).get());
                // Deserialize the JSON response into an array of GroupDto objects
                return resp;
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception occurred", e);
        }

        // Return null if error occurs
        return null;

    }


    /**
     * This method is used to send http get request to gitlab's api for fetching the groups.
     * It is uses several params for filtering the results, these are:
     * @param search_keyword: for searching the groups by there name
     * @param sort: for sorting list in ascending and descending order, valid values: "asc" and "desc"
     * @param orderBy: It sorts the group list on the basis of name, path, id, similarity.
     * @param isPaginated: It holds "true" for paginated result and "false" for all fetching all groups(max groups that can be fetched=100 due to gitlab's api limitation)
     * @param perPage: It stores the integer value which tells how many groups you want to fetch on a single page
     * @param page: It holds the page number*/
    public Map<String, Object> getSearchedGroups(
                        String access_token,
                        String search_keyword,
                        boolean isPaginated,
                        int perPage,
                        int page,
                        String sort,
                        String orderBy)
    {

        StringBuilder apiUrl=new StringBuilder(GROUP_INNERSOURCE_URL+GROUP_SEARCH_PARAM_TEXT);
        apiUrl.append(EQUAL_TEXT)
                .append(search_keyword)
                .append(AMPERSAND_TEXT);

        if(isPaginated){
            apiUrl.append(GROUPS_PER_PAGE_PARAM)
                    .append(EQUAL_TEXT)
                    .append(perPage)
                    .append(AMPERSAND_TEXT)
                    .append(PAGE_PARAM_TEXT)
                    .append(EQUAL_TEXT)
                    .append(page)
                    .append(AMPERSAND_TEXT);

        }
        if(sort.equals(DESC_TEXT)){
            apiUrl.append(SORT_PARAM_TEXT)
                    .append(EQUAL_TEXT)
                    .append(sort)
                    .append(AMPERSAND_TEXT);

        }
        if(orderBy!=null){
            apiUrl.append(ORDER_BY_PARAM_TEXT)
                    .append(EQUAL_TEXT)
                    .append(orderBy)
                    .append(AMPERSAND_TEXT);
        }

        try {

            // Create an HTTP GET request to fetch groups from the API
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl.toString()))
                    .header(Constants.HEADER_KEY, access_token)
                    .GET()
                    .build();

            // Send the HTTP request and retrieve the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response != null) {
                Map<String, Object> resp=new HashMap<>();
                resp.put(GROUPS_TEXT,this.objectMapper.readValue(response.body(), GroupDto[].class));
                resp.put(MAP_TOTAL_LENGTH_FIELD,response.headers().firstValue(X_TOTAL_TEXT).get());

                // Deserialize the JSON response into an array of GroupDto objects
                return resp;
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, DATA_FETCHING_EXCEPTION_MESSAGE, e);
        }

        // Return null if error occurs
        return null;

    }




}
