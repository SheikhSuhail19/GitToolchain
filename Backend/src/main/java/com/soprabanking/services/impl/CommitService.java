package com.soprabanking.services.impl;

import com.soprabanking.model.dto.CommitDto;
import com.soprabanking.model.dto.NumberOfCommitsDto;
import com.soprabanking.services.ICommitService;
import com.soprabanking.constants.Constants;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class CommitService implements ICommitService {

    private static final Logger logger = Logger.getLogger(CommitService.class.getName());
    public List<CommitDto> getCommits(String projectId, String branchName, String privateToken) {
        try {
            List<CommitDto> commitDataToBeReturned = null;

            String commitsUrl = Constants.INNERSOURCE_PROJECTS_API_BASE_URL
                    .concat(projectId)
                    .concat(Constants.COMMIT_FETCH_URI)
                    .concat(branchName);
            String encodedUrl = UriComponentsBuilder.fromUriString(commitsUrl).build().toUriString();

            HttpHeaders headers = new HttpHeaders();
            headers.set(Constants.HEADER_KEY, privateToken);
            HttpEntity<?> entity = new HttpEntity<>(headers);


            RestTemplate restTemplate = new RestTemplate();
            ParameterizedTypeReference<List<CommitDto>> responseType = new ParameterizedTypeReference<List<CommitDto>>() {
            };
            ResponseEntity<List<CommitDto>> response = restTemplate.exchange(encodedUrl, HttpMethod.GET, entity, responseType);

            commitDataToBeReturned = response.getBody();

            return commitDataToBeReturned;
        }
        catch (Exception e){
            logger.log(Level.SEVERE , "Exception occured", e);
        }
        return null;
    }


    public Integer getNumberOfCommits(String projectId, String privateToken){
        try{

            Integer numberOfCommits = 0;
            Integer currentPage = 1;
            Integer perPage = 100;
            Integer currentCommitsCount = 0;



            HttpHeaders headers = new HttpHeaders();
            headers.set(Constants.HEADER_KEY, privateToken);
            HttpEntity<?> entity = new HttpEntity<>(headers);

            RestTemplate restTemplate = new RestTemplate();
            ParameterizedTypeReference<List<NumberOfCommitsDto>> responseType = new ParameterizedTypeReference<List<NumberOfCommitsDto>>() {};
            do{
                String currentPageString = currentPage.toString();
                String perPageString = perPage.toString();
                String numberOfCommitsUrl = Constants.INNERSOURCE_URL.concat(Constants.innersourceAPIversion).concat(projectId).concat(Constants.GET_TOTAL_COMMITS_NUMBER_API1).concat(currentPageString).concat(Constants.GET_TOTAL_COMMITS_NUMBER_API2).concat(perPageString);
                String encodedUrl = UriComponentsBuilder.fromUriString(numberOfCommitsUrl).build().toUriString();

                ResponseEntity<List<NumberOfCommitsDto>> response = restTemplate.exchange(encodedUrl, HttpMethod.GET, entity, responseType);


                numberOfCommits = response.getBody().size() + numberOfCommits;
                currentCommitsCount = response.getBody().size();
                currentPage++;
            }while(currentCommitsCount>=perPage);

            return numberOfCommits;


        }catch(Exception e){
            logger.log(Level.SEVERE , "Exception occured", e);
        }
        return null;

    }

    public Integer getLatestCommit(String projectId, String privateToken) {
        Integer numberOfCommits = 0;
        Integer currentPage = 1;
        Integer perPage = 100;
        Integer currentCommitsCount = 0;

        // Calculate the date for one day prior to the current date
        LocalDateTime currentDateTime = LocalDateTime.now(ZoneOffset.UTC);
        LocalDateTime previousDayDateTime = currentDateTime.minus(1, ChronoUnit.DAYS);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String previousDayFormatted = previousDayDateTime.format(formatter);

        HttpHeaders headers = new HttpHeaders();
        headers.set(Constants.HEADER_KEY, privateToken);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ParameterizedTypeReference<List<NumberOfCommitsDto>> responseType = new ParameterizedTypeReference<List<NumberOfCommitsDto>>() {};

        do {
            String currentPageString = currentPage.toString();
            String perPageString = perPage.toString();
            String numberOfCommitsUrl = Constants.INNERSOURCE_URL.concat( Constants.innersourceAPIversion).concat( projectId).concat( Constants.GET_TOTAL_COMMITS_NUMBER_API1).concat( currentPageString).concat( Constants.GET_TOTAL_COMMITS_NUMBER_API2).concat( perPageString).concat( Constants.COMMITS_SINCE_URI).concat( previousDayFormatted);
            String encodedUrl = UriComponentsBuilder.fromUriString(numberOfCommitsUrl).build().toUriString();

            ResponseEntity<List<NumberOfCommitsDto>> response = restTemplate.exchange(encodedUrl, HttpMethod.GET, entity, responseType);

            numberOfCommits = response.getBody().size() + numberOfCommits;
            currentCommitsCount = response.getBody().size();
            currentPage++;
        } while (currentCommitsCount >= perPage);

        return numberOfCommits;
    }public Integer getLatestWeekCommit(String projectId, String privateToken) {
        Integer numberOfCommits = 0;
        Integer currentPage = 1;
        Integer perPage = 100;
        Integer currentCommitsCount = 0;

        // Calculate the date for one day prior to the current date
        LocalDateTime currentDateTime = LocalDateTime.now(ZoneOffset.UTC);
        LocalDateTime previousDayDateTime = currentDateTime.minus(7, ChronoUnit.DAYS);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String previousDayFormatted = previousDayDateTime.format(formatter);

        HttpHeaders headers = new HttpHeaders();
        headers.set(Constants.HEADER_KEY, privateToken);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ParameterizedTypeReference<List<NumberOfCommitsDto>> responseType = new ParameterizedTypeReference<List<NumberOfCommitsDto>>() {};

        do {
            String currentPageString = currentPage.toString();
            String perPageString = perPage.toString();
            String numberOfCommitsUrl = Constants.INNERSOURCE_URL.concat( Constants.innersourceAPIversion).concat( projectId ).concat( Constants.GET_TOTAL_COMMITS_NUMBER_API1).concat( currentPageString).concat( Constants.GET_TOTAL_COMMITS_NUMBER_API2).concat( perPageString).concat(Constants.COMMITS_SINCE_URI).concat( previousDayFormatted);
            String encodedUrl = UriComponentsBuilder.fromUriString(numberOfCommitsUrl).build().toUriString();

            ResponseEntity<List<NumberOfCommitsDto>> response = restTemplate.exchange(encodedUrl, HttpMethod.GET, entity, responseType);

            numberOfCommits = response.getBody().size() + numberOfCommits;
            currentCommitsCount = response.getBody().size();
            currentPage++;
        } while (currentCommitsCount >= perPage);

        return numberOfCommits;
    }

}