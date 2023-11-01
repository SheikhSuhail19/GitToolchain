package com.soprabanking.model.dto;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "audits")
public class AuditDto {

    @DBRef
    private MRNotificationDto mergeRequest;
    private String notificationSentTime;

}