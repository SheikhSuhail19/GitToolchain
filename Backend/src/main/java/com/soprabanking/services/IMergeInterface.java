package com.soprabanking.services;

import com.soprabanking.model.dto.MergeDto;

import java.util.List;

public interface IMergeInterface {

    public List<MergeDto> getMergeDetails(String id, String access_token);
}
