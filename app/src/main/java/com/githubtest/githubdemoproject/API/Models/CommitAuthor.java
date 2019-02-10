package com.githubtest.githubdemoproject.API.Models;

public class CommitAuthor {

    private String login;

    public CommitAuthor(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
