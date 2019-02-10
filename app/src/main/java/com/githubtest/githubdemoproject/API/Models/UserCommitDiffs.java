package com.githubtest.githubdemoproject.API.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserCommitDiffs {

    @SerializedName("files")
    private List<UserCommitFiles> files;

    public UserCommitDiffs(List<UserCommitFiles> files) {
        this.files = files;
    }

    public List<UserCommitFiles> getFiles() {
        return files;
    }
}
