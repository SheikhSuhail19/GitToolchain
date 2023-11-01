package com.soprabanking.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.soprabanking.constants.Constants;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class MemberDto {

    @JsonProperty(Constants.ACCESS_LEVEL)
    private int accessLevel;

    @JsonProperty(Constants.CREATED_AT)
    private String createdAt;

    private int createdById;

    private String createdByUsername;

    private String createdByName;

    private String createdByState;

    private String createdByAvatarUrl;

    private String createdByWebUrl;

    @JsonProperty(Constants.EXPIRES_AT)
    private String expiresAt;

    private int id;

    private String username;

    private String name;

    private String state;

    @JsonProperty(Constants.AVATAR_URL)
    private String avatarUrl;

    @JsonProperty(Constants.WEB_URL)
    private String webUrl;

    @JsonProperty(Constants.MEMBERSHIP_STATE)
    private String membershipState;

    @JsonProperty(Constants.CREATED_BY)
    public void unpackNested(Map<String, Object> createdBy) {
        this.createdById = (int) createdBy.get(Constants.PROJECT_ID_PATH_VARIABLE);
        this.createdByUsername = (String) createdBy.get(Constants.USERNAME);
        this.createdByName = (String) createdBy.get(Constants.NAME);
        this.createdByState = (String) createdBy.get(Constants.STATE);
        this.createdByAvatarUrl = (String) createdBy.get(Constants.AVATAR_URL);
        this.createdByWebUrl = (String) createdBy.get(Constants.WEB_URL);
    }

}
