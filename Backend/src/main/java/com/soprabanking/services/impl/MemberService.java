package com.soprabanking.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soprabanking.model.dto.MemberDto;
import com.soprabanking.services.IMemberService;
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

@Service
public class MemberService implements IMemberService {
    private static final Logger logger = Logger.getLogger(MemberService.class.getName());
    private final HttpClient client = HttpClient.newHttpClient();
    //ObjectMapper to map the DTO to JSON
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Gets all members of a repository.
     *
     * @param id           the ID of the repository
     * @param access_token the access token of the user making the request
     * @param page         the page number to return, if pagination is enabled
     * @param searchText   the name of the member to search
     * @param state        the state of the members to filter
     * @return a paged map with members array and total members
     */
    public Map<String, Object> getAllMembers(String id, String access_token, int page, String searchText, String state) {

        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(Constants.INNERSOURCE_PROJECTS_API_BASE_URL)
                .append(id)
                .append(Constants.MEMBER_API_URL)
                .append(Constants.PAGE_PARAM)
                .append(page)
                .append(Constants.MEMBERS_PER_PAGE_PARAM)
                .append(Constants.PER_PAGE_PARAM)
                .append(Constants.MEMBERS_QUERY_PARAM)
                .append(searchText);

        if (!state.isEmpty()) {
            urlBuilder.append(Constants.MEMBERS_STATE_PARAM)
                    .append(state);
        }

        String url = urlBuilder.toString();

        // Deserialize the JSON response into an array of MemberDto objects
        Map<String, Object> members = this.getMembersHandler(url, access_token);

        return members;
    }

    /**
     * Fetches members from the API using an HTTP GET request.
     *
     * @param Url          the URL of the API endpoint to fetch members from
     * @param access_token the access token of the user making the request
     * @return a paged map with members array and total members
     */
    private Map<String, Object> getMembersHandler(String Url, String access_token) {
        try {
            // Create an HTTP GET request to fetch members from the API
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Url))
                    .header(Constants.HEADER_KEY, access_token)
                    .GET()
                    .build();

            // Send the HTTP request and retrieve the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response != null) {
                // Deserialize the JSON response into an array of MemberDto objects
                MemberDto[] members = this.objectMapper.readValue(response.body(), MemberDto[].class);
                Map<String, Object> map = new HashMap<>();
                map.put(Constants.MEMBERS_MAP_FIELD_FIRST, members);
                map.put(Constants.MAP_TOTAL_LENGTH_FIELD, response.headers().firstValue(Constants.MEMBERS_API_TOTAL_MEMBERS_HEADER).get());

                return map;
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception occurred", e);
        }
        // Return null if error occurs
        return null;
    }
}
