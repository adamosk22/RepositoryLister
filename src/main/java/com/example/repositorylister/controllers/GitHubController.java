package com.example.repositorylister.controllers;

import com.example.repositorylister.dtos.RepositoryInfo;
import com.example.repositorylister.services.GitHubService;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/github")
public class GitHubController {

    @Autowired
    private GitHubService gitHubService;

    @GetMapping("/users/{username}/repos")
    public Mono<List<RepositoryInfo>> getRepositories(@PathVariable String username) {
        return gitHubService.getNonForkRepositories(username);
    }
}
