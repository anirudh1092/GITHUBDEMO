package com.githubtest.githubdemoproject.API.Models;

public class UserCommitFiles {


    private String filename;
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
