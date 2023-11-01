package com.soprabanking.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class CommitDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("web_url")
    private String webUrl;

    @JsonProperty("title")
    private String title;

    @JsonProperty("short_id")
    private String shortId;

    @JsonProperty("author_name")
    private String authorName;

    @JsonProperty("author_email")
    private String authorEmail;

    @JsonProperty("committer_name")
    private String committerName;

    @JsonProperty("committer_email")
    private String committerEmail;

    @JsonProperty("message")
    private String message;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private ZonedDateTime committed_date;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private ZonedDateTime created_at;


    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private ZonedDateTime authored_date;

    public CommitDto() {
    }

    public CommitDto(String id, String authorName, String authorEmail, String committerName, String committerEmail, String message, ZonedDateTime committedDate, ZonedDateTime createdAt, ZonedDateTime authoredDate, String webUrl) {
        this.id = id;
        this.authorName = authorName;
        this.authorEmail = authorEmail;
        this.committerName = committerName;
        this.committerEmail = committerEmail;
        this.message = message;
        this.committed_date = committedDate;
        this.created_at = createdAt;
        this.authored_date = authoredDate;
        this.webUrl = webUrl;
    }

    public CommitDto(String shortId) {
        this.shortId = shortId;
    }
}
