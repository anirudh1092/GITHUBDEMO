package com.githubtest.githubdemoproject.API.Models;

public class UserCommits {

    private String sha;
    private CommitAuthor author;


    public UserCommits(String sha, CommitAuthor author) {
        this.sha = sha;
        this.author = author;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public CommitAuthor getAuthor() {
        return author;
    }

    public void setAuthor(CommitAuthor author) {
        this.author = author;
    }
}
