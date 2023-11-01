package com.soprabanking.controllers;

import com.soprabanking.services.impl.GroupService;
import com.soprabanking.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * A controller method that handles GET requests for fetching all groups associated with
 * user's access token. It returns groups and total length. There are some parameters which will help to fetch particular group
 * from all the groups. These params are:
 * search: for searching the groups by there name
 * sort: for sorting list in ascending and descending order, valid values: "asc" and "desc"
 * order_by: It sorts the group list on the basis of name, path, id, similarity.
 * isPaginated: It holds "true" for paginated result and "false" for all fetching all groups(max groups that can be fetched=100 due to gitlab's api limitation)
 * per_page: It stores the integer value which tells how many groups you want to fetch on a single page
 * page: It holds the page number
 * */
@RestController
@CrossOrigin(Constants.CROSS_ORIGIN_URL)
@RequestMapping(Constants.GROUP_CONTROLLER_REQ_MAPPING)
public class GroupController {

    @Autowired
    GroupService groupService;

/*  Returns the array of GroupDto. This method is called when there is get request for fetching groups.*/
    @GetMapping()
    public ResponseEntity<Map<String,Object>> fetchGroups(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String access_token,
            @RequestParam(value = Constants.GROUP_SEARCH_PARAM_TEXT,required = false) String search_keyword,
            @RequestParam(value = Constants.GROUPS_PAGINATED_VALUE,required = false,defaultValue = Constants.PAGINATION_DEFAULT_VALUE) boolean pagination,
            @RequestParam(value = Constants.GROUPS_PER_PAGE_VALUE,required = false,defaultValue = Constants.GROUPS_PER_PAGE_DEFAULT_VALUE) int perPage,
            @RequestParam(value = Constants.GROUPS_PAGE_VALUE,required = false, defaultValue = Constants.GROUPS_PAGE_DEFAULT_VALUE) int page,
            @RequestParam(value = Constants.GROUPS_SORT_VALUE,required = false, defaultValue = Constants.GROUPS_SORT_DEFAULT_VALUE) String sort,
            @RequestParam(value = Constants.GROUPS_ORDER_BY_VALUE,required = false) String orderBy
            ){

        Map<String,Object> response;

/*        Checks whether user want to fetch all groups, if yes than sets the maximum possible number
          that gitlab's api allow*/
        if(!pagination){
            perPage=100;
        }

        /*Checks whether to show serached result or not. If yes then calls the method to fetch searched result*/
        if(search_keyword!=null){
            response= this.groupService.getSearchedGroups(access_token,search_keyword,pagination,perPage,page,sort,orderBy);
        }else {
            response=this.groupService.getAllGroups(access_token,pagination,perPage,page,sort,orderBy);
        }

        return ResponseEntity.ok(response);
    }
}
