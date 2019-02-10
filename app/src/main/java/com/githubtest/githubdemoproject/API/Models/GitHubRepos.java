package com.githubtest.githubdemoproject.API.Models;

public class GitHubRepos {

    String name;

    public GitHubRepos(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
