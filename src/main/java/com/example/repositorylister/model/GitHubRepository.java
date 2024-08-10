package com.example.repositorylister.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubRepository {

    private String name;
    private GitHubOwner owner;
    private boolean fork;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GitHubOwner getOwner() {
        return owner;
    }

    public void setOwner(GitHubOwner owner) {
        this.owner = owner;
    }

    public boolean isFork() {
        return fork;
    }

    public void setFork(boolean fork) {
        this.fork = fork;
    }
}
