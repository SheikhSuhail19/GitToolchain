package com.soprabanking.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/* The BranchDto class represents a branch in a version control system. */
@Getter
@Setter
@ToString
public class BranchDto {

    private String name;
    private Boolean merged;

    @JsonProperty("protected")
    private Boolean isProtected;

    @JsonProperty("default")
    private Boolean isDefault;

    @JsonProperty("developers_can_push")
    private Boolean developersCanPush;

    @JsonProperty("developers_can_merge")
    private Boolean developersCanMerge;

    @JsonProperty("can_push")
    private Boolean canPush;

    @JsonProperty("web_url")
    private String branchUrl;

    private String shortId;

    private String commitTitle;

    private String commitMessage;

    private String authorName;

    private String commitCommittedAt;

    private String committerName;

    private String authorEmail;

    private String committerEmail;

    private String commitUrl;

    private String commitId;

    private String commitCreatedAt;

    private List<String> parentIds;

    private String commitAuthoredDate;

    private Object trailers;

    private String mergeUrl;

    @JsonProperty("commit")
    @SuppressWarnings("unchecked")
    public void unpackNested(Map<String, Object> commit){
        this.commitId = (String) commit.get("id");
        this.shortId = (String) commit.get("short_id");
        this.commitTitle = (String) commit.get("title");
        this.commitMessage = (String) commit.get("message");
        this.authorName = (String) commit.get("author_name");
        this.commitCommittedAt = (String) commit.get("committed_date");
        this.commitCreatedAt = (String) commit.get("created_at");
        this.commitAuthoredDate = (String) commit.get("authored_date");
        this.committerName = (String) commit.get("committer_name");
        this.authorEmail = (String) commit.get("author_email");
        this.committerEmail = (String) commit.get("committer_email");
        this.commitUrl = (String) commit.get("web_url");
        this.parentIds = (List<String>) commit.get("parent_ids");
        this.trailers = (Object) commit.get("trailers");
    }




}