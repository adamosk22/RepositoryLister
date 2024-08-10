package com.example.repositorylister.handlers;

import com.example.repositorylister.model.GitHubUserNotFoundException;
import com.example.repositorylister.model.GithubErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GitHubUserNotFoundException.class)
    public ResponseEntity<GithubErrorResponse> handleGitHubUserNotFoundException(GitHubUserNotFoundException ex) {
        GithubErrorResponse errorResponse = new GithubErrorResponse(ex.getStatus(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}