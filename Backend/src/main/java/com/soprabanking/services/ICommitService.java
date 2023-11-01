package com.soprabanking.services;

import com.soprabanking.model.dto.CommitDto;

import java.util.List;

public interface ICommitService {
    List<CommitDto> getCommits(String projectId, String branchName, String privateToken);
}
