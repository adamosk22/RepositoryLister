package com.example.repositorylister.model;

public class GitHubUserNotFoundException extends RuntimeException {
    private final int status;

    public GitHubUserNotFoundException(String message, int status) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}