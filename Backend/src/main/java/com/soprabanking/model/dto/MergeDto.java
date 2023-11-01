package com.soprabanking.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MergeDto {
    private String id;
    private Long iid;
    private Long project_id;
    private String title;
    private Author author;

    private String created_at; // Date as a string
    private String state;
    private String web_url;
    private String description;
    private List<Reviewer> reviewers;


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Author {
        private int id;
        private String username;
        private String name;
        private String state;
        private String avatar_url;
        private String web_url;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Reviewer {
        private long id;
        private String username;
        private String name;
        private String state;
        private String avatar_url;
        private String web_url;
    }
}
