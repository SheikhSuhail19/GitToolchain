package com.soprabanking.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Collections;
import java.util.List;

import static com.soprabanking.constants.ApiUrlConstants.AUTHORIZATION;

/**
 * Utility class for making API calls to InnerSource.
 */
@Service
public class InnerSourceApiCallerUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(InnerSourceApiCallerUtil.class);
    private final WebClient webClient;

    /**
     * Constructor to initialize the WebClient with the base URL for the InnerSource API.
     *
     * @param INNER_SOURCE_API_BASE_URL The base URL of the InnerSource API.
     */
    @Autowired
    public InnerSourceApiCallerUtil(@Value(value = "${innerSourceApi.baseUrl}") final String INNER_SOURCE_API_BASE_URL) {
        this.webClient = WebClient.builder()
                .baseUrl(INNER_SOURCE_API_BASE_URL)
                .build();
    }

    /**
     * Makes an API call to InnerSource.
     *
     * @param innerSourceAccessToken The access token for authentication.
     * @param apiUrl                 The URL of the API endpoint.
     * @param returnModelClass       The class representing the expected response model.
     * @param <T>                    The type of the response model.
     * @return A list of response objects.
     */
    public <T> List<T> callApi(final String innerSourceAccessToken, final String apiUrl, final Class<T> returnModelClass) {
        try {
            WebClient.RequestHeadersSpec<?> webClientRequestUri = webClient.get()
                    .uri(apiUrl)
                    .header(AUTHORIZATION, innerSourceAccessToken);

            List<T> responseList = webClientRequestUri
                    .retrieve()
                    .bodyToFlux(returnModelClass)
                    .collectList()
                    .block();

            // Log successful API call
            LOGGER.info("Successfully called InnerSource API for given URL");

            return responseList != null ? responseList : Collections.emptyList();
        } catch (WebClientResponseException e) {
            // Log error for WebClient response exception
            LOGGER.error("Error while calling InnerSource API. Status code: {}, Response: {}",
                    e.getStatusCode(), e.getResponseBodyAsString(), e);
            return Collections.emptyList();
        } catch (Exception e) {
            // Log error for general exceptions
            LOGGER.error("Exception: Failed to fetch data from InnerSource API.", e);
            return Collections.emptyList();
        }
    }
}
