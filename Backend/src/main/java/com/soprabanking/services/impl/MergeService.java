package com.soprabanking.services.impl;

import com.soprabanking.model.dto.MRNotificationDto;
import com.soprabanking.model.dto.MergeDto;

import com.soprabanking.repositories.NotificationRepository;
import com.soprabanking.services.IMergeInterface;
import com.soprabanking.constants.Constants;
import jakarta.mail.MessagingException;
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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class MergeService implements IMergeInterface {

    private static final Logger logger = Logger.getLogger(MergeService.class.getName());

    private final NotificationRepository notificationRepository;

    private final NotificationService notificationService;

    public MergeService(NotificationRepository notificationRepository, NotificationService notificationService) {
        this.notificationRepository = notificationRepository;
        this.notificationService = notificationService;
    }



    public List<MergeDto> getMergeDetails(String id, String access_token) {

        try {


            String mergeUrl = Constants.INNERSOURCE_PROJECTS_API_BASE_URL.concat(id).concat(Constants.MERGE_SERVICE_URI_SECOND);

            String encodedUrl = UriComponentsBuilder.fromUriString(mergeUrl).build().toUriString();

            HttpHeaders headers = new HttpHeaders();

            headers.set(Constants.MERGE_AUTHORIZATION_CONST1, access_token);


            HttpEntity<?> entity = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();

            ParameterizedTypeReference<List<MergeDto>> responseType = new ParameterizedTypeReference<List<MergeDto>>() {
            };
            ResponseEntity<List<MergeDto>> response = restTemplate.exchange(encodedUrl, HttpMethod.GET, entity, responseType);


            List<MergeDto> responseData = response.getBody();
            return responseData;

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception occurred", e);
        }

        return null;
    }

    public List<MRNotificationDto> filterMergeRequest(String projectID, String accessToken) throws MessagingException {

        List<MergeDto> responseData = getMergeDetails(projectID, accessToken);

        LocalDateTime oneWeekAgo = LocalDateTime.now(ZoneOffset.UTC).minus(7, ChronoUnit.DAYS);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_TIME_PATTERN);

        List<MRNotificationDto> returnData = notificationRepository.findAll();
        List<MergeDto> pendingInactiveMergeRequests = responseData.stream()
                .filter(mergeRequest ->
                        Constants.STATUS_OPENED.equals(mergeRequest.getState()) &&
                                LocalDateTime.parse(mergeRequest.getCreated_at(), formatter).isBefore(oneWeekAgo) &&
                                !returnData.stream().anyMatch(dbMergeRequest ->
                                        dbMergeRequest.getId().equals(mergeRequest.getId())
                                )
                )
                .collect(Collectors.toList());

        List<MRNotificationDto> mergedMergeRequests = notificationRepository.findByState(Constants.STATUS_MERGED);


        List<MRNotificationDto> mergedMRsToRemove = new ArrayList<>();

        for (MRNotificationDto dbMergeRequest : mergedMergeRequests) {
            for (MergeDto responseDataMR : responseData) {
                if (dbMergeRequest.getId().equals(responseDataMR.getId())) {

                    mergedMRsToRemove.add(dbMergeRequest);
                    break;
                }
            }
        }

        for (MRNotificationDto mergedMR : mergedMRsToRemove) {
            notificationRepository.delete(mergedMR);
        }

        notificationService.storeData(pendingInactiveMergeRequests);

        return returnData;
    }
}



