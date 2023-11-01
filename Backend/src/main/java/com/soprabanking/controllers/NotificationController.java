package com.soprabanking.controllers;


import com.soprabanking.services.impl.NotificationService;
import com.soprabanking.constants.Constants;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(Constants.CROSS_ORIGIN_URL)
@RequestMapping(Constants.NOTIFICATION_CONTROLLER_REQ_MAPPING)
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping(Constants.NOTIFICATION_SEND)
    public String sendNotification(@RequestParam String id) throws MessagingException {

        String status = notificationService.sendNotification(id);
        if(status!=null){
            return Constants.MAIL_SENT_SUCCESS_MESSAGE;
        }
        else{
            return Constants.MAIL_SENT_FAILED_MESSAGE;
        }

    }
}
