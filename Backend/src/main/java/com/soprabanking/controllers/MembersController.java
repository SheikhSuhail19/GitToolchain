package com.soprabanking.controllers;

import com.soprabanking.services.impl.MemberService;
import com.soprabanking.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/* A controller method that handles GET requests for fetching members detail of a project by ID */
@RestController
@CrossOrigin(Constants.CROSS_ORIGIN_URL)
@RequestMapping(Constants.MEMBERS_CONTROLLER_REQ_MAPPING)
public class MembersController {
    @Autowired
    private MemberService memberService;


    /**
     * Gets all members of a repository.
     *
     * @param id         the ID of the repository
     * @param page       the page number to return, if pagination is enabled
     * @param searchText the name of the member to search
     * @param state      the state of the members to filter
     * @return a paged map with members array and total members
     */
    @GetMapping()
    public ResponseEntity<Map<String, Object>> getMembers(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String access_token,
                                                          @PathVariable(Constants.PROJECT_ID_PATH_VARIABLE) String id,
                                                          @RequestParam(defaultValue = Constants.DEFAULT_PAGE) int page,
                                                          @RequestParam(defaultValue = Constants.EMPTY_STRING) String searchText,
                                                          @RequestParam(defaultValue = Constants.EMPTY_STRING) String state) {

        Map<String, Object> res = this.memberService.getAllMembers(id, access_token, page + 1, searchText, state);

        return ResponseEntity.ok(res);

    }

}
