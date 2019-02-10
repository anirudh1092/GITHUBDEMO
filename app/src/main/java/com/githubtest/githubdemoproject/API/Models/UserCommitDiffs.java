package com.githubtest.githubdemoproject.API.Models;

public class UserCommitDiffs {

    private UserCommitFiles files;

    public UserCommitDiffs(UserCommitFiles files) {
        this.files = files;
    }

    public UserCommitFiles getFiles() {
        return files;
    }
}
