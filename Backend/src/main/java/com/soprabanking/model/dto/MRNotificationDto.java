package com.soprabanking.model.dto;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "filteredMergeRequests")
public class MRNotificationDto {

    private String id;
    private Long project_id;


    private String title;


    private String created_at;


    private String modified_at;

    private String state;

    private String web_url;

    private List<MergeDto.Reviewer> reviewers;



    public static class Reviewer {
        private long id;
        private String username;
        private String name;
        private String state;
        private String avatar_url;
        private String web_url;
    }
}





