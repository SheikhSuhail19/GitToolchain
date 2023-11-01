package com.soprabanking.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class PipelineDto {

    private String id;

    private String iid;

    @JsonProperty("project_id")
    private String projectId;

    private String sha;

    private String ref;

    private String status;

    private String source;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("web_url")
    private String webUrl;

    @JsonProperty("before_sha")
    private String beforeSha;

    private boolean tag;

    @JsonProperty("yaml_errors")
    private String yamlErrors;

    @JsonProperty("started_at")
    private String startedAt;

    @JsonProperty("finished_at")
    private String finishedAt;

    @JsonProperty("committed_at")
    private String committedAt;

    private String duration;

    @JsonProperty("queued_duration")
    private String queuedDuration;

    private String coverage;

    @JsonProperty("name")
    private String pipelineName;

    private int userId;

    private String username;

    private String userFullName;

    private String userState;

    private String avatarUrl;

    private String userWebUrl;

    private String icon;

    private String text;

    private String label;

    private String group;

    private String tooltip;

    private boolean hasDetails;

    private String detailsPath;

    private String illustration;

    private String favicon;
    @JsonProperty("user")
    public void unpackNestedUser(Map<String, Object> user){
        this.userId = (Integer) user.get("id");
        this.username = (String) user.get("username");
        this.userFullName = (String) user.get("name");
        this.userState = (String) user.get("state");
        this.avatarUrl = (String) user.get("avatar_url");
        this.userWebUrl = (String) user.get("web_url");
    }

    @JsonProperty("detailed_status")
    public void unpackNestedDetailedStatus(Map<String, Object> detailedStatus){
        this.icon = (String) detailedStatus.get("icon");
        this.text = (String) detailedStatus.get("text");
        this.label = (String) detailedStatus.get("label");
        this.group = (String) detailedStatus.get("group");
        this.tooltip = (String) detailedStatus.get("tooltip");
        this.hasDetails = (Boolean) detailedStatus.get("has_details");
        this.detailsPath = (String) detailedStatus.get("details_path");
        this.illustration = (String) detailedStatus.get("illustration");
        this.favicon = (String) detailedStatus.get("favicon");
    }
}
