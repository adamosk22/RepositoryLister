package com.example.repositorylister.services;

import com.example.repositorylister.dtos.BranchInfo;
import com.example.repositorylister.dtos.RepositoryInfo;
import com.example.repositorylister.model.GitHubBranch;
import com.example.repositorylister.model.GitHubRepository;
import com.example.repositorylister.model.GitHubUserNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class GitHubServiceImpl implements GitHubService {

    private final WebClient webClient;

    public GitHubServiceImpl(@Value("${github.api-url}") String apiUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(apiUrl)
                .defaultHeader(HttpHeaders.ACCEPT, "application/vnd.github.v3+json")
                .build();
    }

    @Override
    public Mono<List<RepositoryInfo>> getNonForkRepositories(String username) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/users/{username}/repos")
                        .queryParam("type", "owner")
                        .build(username))
                .retrieve()
                .onStatus(HttpStatusCode::isError, response ->
                        response.bodyToMono(String.class)
                                .flatMap(body -> Mono.error(new GitHubUserNotFoundException(
                                        "GitHub user not found: " + username,
                                        HttpStatus.NOT_FOUND.value()))))
                .bodyToFlux(GitHubRepository.class)
                .filter(repo -> !repo.isFork())
                .flatMap(repo -> getBranches(repo.getOwner().getLogin(), repo.getName())
                        .collectList()
                        .map(branches -> new RepositoryInfo(repo.getName(), repo.getOwner().getLogin(), branches)))
                .collectList(); // Collect the Flux<RepositoryInfo> into a Mono<List<RepositoryInfo>>
    }




    private Flux<BranchInfo> getBranches(String owner, String repo) {
        return webClient.get()
                .uri("/repos/{owner}/{repo}/branches", owner, repo)
                .retrieve()
                .bodyToFlux(GitHubBranch.class)
                .map(branch -> new BranchInfo(branch.getName(), branch.getCommit().getSha()));
    }
}
