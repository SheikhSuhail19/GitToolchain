package com.soprabanking.services.impl;

import com.soprabanking.model.dto.AuditDto;
import com.soprabanking.model.dto.MRNotificationDto;
import com.soprabanking.model.dto.MergeDto;
import com.soprabanking.repositories.AuditRepository;
import com.soprabanking.repositories.NotificationRepository;
import com.soprabanking.constants.Constants;
import com.soprabanking.utility.SmtpMailSender;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final SmtpMailSender mailSender;
    private final AuditRepository auditRepository;
    private static final Logger logger = Logger.getLogger(NotificationService.class.getName());

    public NotificationService(NotificationRepository notificationRepository, SmtpMailSender mailSender, AuditRepository auditRepository) {
        this.notificationRepository = notificationRepository;
        this.mailSender = mailSender;
        this.auditRepository = auditRepository;
    }

    void storeData(List<MergeDto> pendingInactiveMergeRequests) {
        if (!pendingInactiveMergeRequests.isEmpty()) {
            List<MRNotificationDto> database = notificationRepository.findAll();

            for (MergeDto mergeRequest : pendingInactiveMergeRequests) {

                    // Mapping
                    MRNotificationDto payload = new MRNotificationDto();
                    payload.setId(mergeRequest.getId());
                    payload.setTitle(mergeRequest.getTitle());
                    payload.setState(mergeRequest.getState());
                    payload.setCreated_at(mergeRequest.getCreated_at());
                    payload.setModified_at("");
                    payload.setReviewers(mergeRequest.getReviewers());
                    payload.setWeb_url(mergeRequest.getWeb_url());
                    payload.setProject_id(mergeRequest.getProject_id());


                    notificationRepository.save(payload);
                }

        } else {
            logger.log(Level.SEVERE , Constants.EXCEPTION_MESSAGE);
        }
    }


    public String sendNotification(String id) throws MessagingException {

        try {

            LocalDateTime localTime = LocalDateTime.now();
            MRNotificationDto mrToUpdate = notificationRepository.findById(id).orElse(null);

            if (mrToUpdate != null) {

                mrToUpdate.setModified_at(localTime.toString());

                notificationRepository.save(mrToUpdate);
            }


            for (MergeDto.Reviewer reviewer : mrToUpdate.getReviewers()) {
                mailSender.sendMail(reviewer.getUsername().concat(Constants.DOMAIN_NAME), Constants.SUBJECT, Constants.EMAIL_BODY0
                        .concat(reviewer.getName())
                        .concat(Constants.EMAIL_BODY1)
                        .concat(Constants.EMAIL_BODY2)
                        .concat(mrToUpdate.getId())
                        .concat(Constants.END_PARA)
                        .concat(Constants.EMAIL_BODY3)
                        .concat(mrToUpdate.getWeb_url())
                        .concat(Constants.END_PARA)
                        .concat(Constants.EMAIL_BODY4));
            }

            AuditDto auditPayload = new AuditDto();
            auditPayload.setNotificationSentTime(localTime.toString());
            auditPayload.setMergeRequest(mrToUpdate);
            auditRepository.save(auditPayload);

            return Constants.MAIL_SENT_SUCCESS_MESSAGE;

        } catch (Exception e) {
            logger.log(Level.SEVERE , Constants.EXCEPTION_MESSAGE, e);
            return null;
        }
    }
}

