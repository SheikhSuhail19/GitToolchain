package com.soprabanking.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupDto {
    private String id;
    private String web_url;
    private String name;
    private String path;
    private String description;
    private String visibility;
    private String share_with_group_lock;
    private String require_two_factor_authentication;
    private String two_factor_grace_period;
    private String project_creation_level;
    private String auto_devops_enabled;
    private String subgroup_creation_level;
    private String emails_disabled;
    private String mentions_disabled;
    private String lfs_enabled;
    private String default_branch_protection;
    private String avatar_url;
    private String request_access_enabled;
    private String full_name;
    private String full_path;
    private String created_at;
    private String parent_id;
    private String shared_runners_setting;
    private String ldap_cn;
    private String ldap_access;
    private String marked_for_deletion_on;
    private String wiki_access_level;
    private String repository_storage;

}
