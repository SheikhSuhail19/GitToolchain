package com.soprabanking.controllers;

import com.soprabanking.services.impl.BranchService;
import com.soprabanking.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/* A controller method that handles GET requests for fetching all branch details of a project by ID */
@RestController
@CrossOrigin(Constants.CROSS_ORIGIN_URL)
@RequestMapping(Constants.BRANCH_CONTROLLER_REQ_MAPPING)
public class BranchesController {

    @Autowired
    private BranchService branchService;


    @GetMapping()
    public ResponseEntity<Map<String, Object>> getBranches(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String access_token,
                                                           @PathVariable(Constants.PROJECT_ID_PATH_VARIABLE) String id,
                                                           @RequestParam(defaultValue = Constants.DEFAULT_PAGE) int page,
                                                           @RequestParam(defaultValue = Constants.PAGINATION_DEFAULT_VALUE) Boolean pagination) {

        Map<String, Object> res = this.branchService.getAllBranches(id, access_token, pagination, page);

        return ResponseEntity.ok(res);

    }

    @GetMapping(Constants.BRANCH_CONTROLLER_GET_MAPPING_SEARCH)
    public ResponseEntity<Map<String, Object>> searchBranchesName(@PathVariable(Constants.PROJECT_ID_PATH_VARIABLE) String id,
                                                                  @RequestHeader(value = HttpHeaders.AUTHORIZATION) String access_token,
                                                                  @RequestParam(Constants.SEARCH_PARAMETER_NAME) String name,
                                                                  @RequestParam(defaultValue = Constants.DEFAULT_PAGE) int page) {

        Map<String, Object> branchesMap = this.branchService.getSearchedBranches(id, access_token, page, name);

        return ResponseEntity.ok(branchesMap);
    }

    @GetMapping(Constants.BRANCH_CONTROLLER_GET_MAPPING_FILTER)
    public ResponseEntity<Map<String, Object>> applyFilters(@PathVariable(Constants.PROJECT_ID_PATH_VARIABLE) String id,
                                                            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String access_token,
                                                            @RequestParam(defaultValue = Constants.DEFAULT_PAGE) int page,
                                                            @RequestParam(value = Constants.IS_MERGED_VALUE, required = Constants.BRANCH_CONTROLLER_FILTER_REQUIRED) Boolean isMerged,
                                                            @RequestParam(value = Constants.IS_PROTECTED_VALUE, required = Constants.BRANCH_CONTROLLER_FILTER_REQUIRED) Boolean isProtected,
                                                            @RequestParam(value = Constants.IS_DEFAULT_VALUE, required = Constants.BRANCH_CONTROLLER_FILTER_REQUIRED) Boolean isDefault,
                                                            @RequestParam(value = Constants.DEV_CAN_PUSH_VALUE, required = Constants.BRANCH_CONTROLLER_FILTER_REQUIRED) Boolean developersCanPush,
                                                            @RequestParam(value = Constants.DEV_CAN_MERGE_VALUE, required = Constants.BRANCH_CONTROLLER_FILTER_REQUIRED) Boolean developersCanMerge) {


        Map<String, Object> branchesMap = this.branchService.getFilteredBranches(id, access_token, page, isMerged, isProtected, isDefault, developersCanPush, developersCanMerge);


        return ResponseEntity.ok(branchesMap);
    }

    @GetMapping(Constants.BRANCH_CONTROLLER_GET_MAPPING_CALENDAR)
    public ResponseEntity<Map<String, Object>> calendarFilter(@PathVariable(Constants.PROJECT_ID_PATH_VARIABLE) String id,
                                                              @RequestHeader(value = HttpHeaders.AUTHORIZATION) String access_token,
                                                              @RequestParam(Constants.CALENDAR_PARAMETER_START) String startDate,
                                                              @RequestParam(value = Constants.CALENDAR_PARAMETER_END) String endDate,
                                                              @RequestParam(defaultValue = Constants.DEFAULT_PAGE) int page) {

        Map<String, Object> branchesMap = this.branchService.timeRangeBranches(id, access_token, page, startDate, endDate);

        return ResponseEntity.ok(branchesMap);
    }
}
