package com.githubtest.githubdemoproject.API;

import com.githubtest.githubdemoproject.API.Models.GitHubRepos;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface GitHubClient {

    @GET("/users/{user}/repos")
    Observable<List<GitHubRepos>>getUserRepos (@Path("user") String user);
}
