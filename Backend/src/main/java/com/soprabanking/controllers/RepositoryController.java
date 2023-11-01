package com.soprabanking.controllers;
import com.soprabanking.model.dto.RepositoryDto;
import com.soprabanking.services.impl.RepositoryService;
import com.soprabanking.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/* The RepositoryController class handles HTTP requests related to repositories. */
@RestController
@CrossOrigin(Constants.CROSS_ORIGIN_URL)
@RequestMapping(Constants.REPOSITORY_CONTROLLER_REQ_MAPPING)

public class RepositoryController {

    private RepositoryService repositoryService;

    /* Sets the RepositoryService for this controller. */
    @Autowired
    public void getRepository(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    /* Retrieves a list of repositories associated with a project.*/
    @GetMapping(Constants.REPOSITORY_CONTROLLER_GET_MAPPING)
    public ResponseEntity<RepositoryDto[]> getProject(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String access_token) {
        return repositoryService.getRepositories(access_token);
    }
}