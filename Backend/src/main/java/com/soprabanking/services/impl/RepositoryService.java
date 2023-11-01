package com.soprabanking.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soprabanking.model.dto.RepositoryDto;
import com.soprabanking.constants.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class RepositoryService {

    private static final Logger logger = Logger.getLogger(RepositoryService.class.getName());

    /*
     * Retrieves a list of repositories for a given access token.
     * @param access_token The authorization token for authentication.
     */
    public ResponseEntity<RepositoryDto[]> getRepositories(String access_token) {
        StringBuilder url = new StringBuilder(Constants.REPOSITORY_SERVICE_URI);

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(url.toString()))
                .header(Constants.HEADER_KEY, access_token)
                .GET()
                .build();

        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> response = null;

        try {
            response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception ", e);
        }

        if (response != null && response.statusCode() == 200) {
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                RepositoryDto[] repositoryDTO = objectMapper.readValue(response.body(), RepositoryDto[].class);
                return ResponseEntity.ok(repositoryDTO);
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Error parsing JSON response: " + e.getMessage());
            }
        } else {
            logger.log(Level.SEVERE, "HTTP request failed with status code: " + response.statusCode());
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}