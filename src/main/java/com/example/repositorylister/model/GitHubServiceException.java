package com.example.repositorylister.model;

public class GitHubServiceException extends RuntimeException{
    private final int status;

    public GitHubServiceException(String message, int status) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
