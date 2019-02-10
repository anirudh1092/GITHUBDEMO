package com.githubtest.githubdemoproject.API.Models;

import com.google.gson.annotations.SerializedName;

public class UserCommitFiles {

    @SerializedName("filename")
    private String filename;
    @SerializedName("patch")
    private String patch;

    public UserCommitFiles(String filename, String patch) {
        this.filename = filename;
        this.patch = patch;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getPatch() {
        return patch;
    }

    public void setPatch(String patch) {
        this.patch = patch;
    }
}
