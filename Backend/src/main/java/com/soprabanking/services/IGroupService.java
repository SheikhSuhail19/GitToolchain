package com.soprabanking.services;

import java.util.Map;

/*Group interface that for declaring methods that Group service must have to implements*/
public interface IGroupService {
    public Map<String,Object> getAllGroups(String access_token, boolean isPaginated, int perPage, int page, String sort, String orderBy);
}
