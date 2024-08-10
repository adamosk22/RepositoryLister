package com.example.repositorylister.model;

public class GithubErrorResponse {
    private final int status;
    private final String message;

    public GithubErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}