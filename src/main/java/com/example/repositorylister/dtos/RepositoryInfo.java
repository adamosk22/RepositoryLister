package com.example.repositorylister.dtos;
import java.util.List;

public record RepositoryInfo(String repositoryName, String ownerLogin, List<BranchInfo> branches) {}