package com.soprabanking.model.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RepositoryDto {
    private Long id;
    private String name;
    private String http_url_to_repo;
}
