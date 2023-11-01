package com.soprabanking.services.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soprabanking.model.dto.PipelineDto;
import com.soprabanking.services.IPipelineService;
import com.soprabanking.constants.Constants;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PipelineService implements IPipelineService {

    HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = Logger.getLogger(BranchService.class.getName());

    /**
     * Retrieves a list of pipelines for a given project with optional pagination.
     *
     * @param id The project ID.
     * @param access_token The access token for authentication.
     * @param page The page number for pagination.
     * @return A {@link Map} containing the list of {@link PipelineDto} objects and the total number of pipelines,
     *         or null if an exception occurs.
     */
    public Map<String, Object> getAllPipelines(String id, String access_token, int page) {

        try {
            StringBuilder uriSbr = new StringBuilder();
            uriSbr.append(Constants.INNERSOURCE_PROJECTS_API_BASE_URL)
                    .append(id)
                    .append(Constants.PIPELINES_GET_ALL_URL);
            // Send the HTTP request and retrieve the response
            HttpResponse<String> response = getPipelinesHandler(access_token, uriSbr.toString());

            if (response != null) {
                // Deserialize the JSON response into an array of PipelineDto objects
                this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                PipelineDto[] res = this.objectMapper.readValue(response.body(), PipelineDto[].class);
                List<PipelineDto> pipelines = new ArrayList<>();

                for (PipelineDto pipeline : res) {
                    PipelineDto individualPipeline = getPipeline(id, access_token, pipeline.getId());
                    pipelines.add(individualPipeline);
                }
                return this.convertPipelinesArrayToMap(pipelines.toArray(new PipelineDto[res.length]), Integer.parseInt(String.valueOf(res.length)));

            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception occurred", e);
        }
        return null;
    }

    /**
     * Retrieves a specific pipeline by its ID for a given project.
     *
     * @param id The project ID.
     * @param access_token The access token for authentication.
     * @param pipelineId The ID of the pipeline to retrieve.
     * @return A {@link PipelineDto} object representing the retrieved pipeline, or null if an exception occurs.
     */
    public PipelineDto getPipeline(String id, String access_token, String pipelineId) {

        try {
            StringBuilder uriSbr = new StringBuilder();
            uriSbr.append(Constants.INNERSOURCE_PROJECTS_API_BASE_URL)
                    .append(id)
                    .append(Constants.PIPELINES_GET_ALL_URL)
                    .append(pipelineId);
            // Send the HTTP request and retrieve the response
            HttpResponse<String> response = getPipelinesHandler(access_token,uriSbr.toString());

            if (response != null) {
                // Deserialize the JSON response into an array of PipelineDto objects
                return this.objectMapper.readValue(response.body(), PipelineDto.class);

            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception occurred", e);
        }
        return null;
    }

    /**
     * Converts an array of PipelineDto objects into a map.
     * <p>
     * The map contains two key-value pairs:
     * <p>
     * * `Constants.PIPELINE_MAP_FIELD_FIRST`: An array of PipelineDto objects.
     * * `Constants.MAP_TOTAL_LENGTH_FIELD_`: The number of PipelineDto objects in the array.
     *
     * @param pipelines    the array of PipelineDto objects to convert
     * @param totalLength the length of the array without pagination
     * @return a map containing the array of PipelineDto objects and the number of PipelineDto objects in the array
     */
    private Map<String, Object> convertPipelinesArrayToMap(PipelineDto[] pipelines, int totalLength) {
        Map<String, Object> map = new HashMap<>();
        map.put(Constants.PIPELINE_MAP_FIELD_FIRST, pipelines);
        map.put(Constants.MAP_TOTAL_LENGTH_FIELD, totalLength);
        return map;
    }

    /**
     * Sends an HTTP GET request to the specified URI with the provided access token.
     *
     * @param access_token The access token to include in the request header.
     * @param uri The URI to send the GET request to.
     * @return An {@link HttpResponse} containing the response from the HTTP request, or null if an exception occurs.
     */
    private HttpResponse<String> getPipelinesHandler(String access_token, String uri) {
        try{
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header(Constants.HEADER_KEY, access_token)
                .GET()
                .build();

        // Send the HTTP request and retrieve the response
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception occurred", e);
        }
        return null;
    }

}
