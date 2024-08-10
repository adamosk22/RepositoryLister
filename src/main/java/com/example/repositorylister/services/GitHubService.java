package com.example.repositorylister.services;

import com.example.repositorylister.dtos.RepositoryInfo;
import reactor.core.publisher.Mono;

import java.util.List;

public interface GitHubService {
    Mono<List<RepositoryInfo>> getNonForkRepositories(String username);
}
