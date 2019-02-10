package com.githubtest.githubdemoproject.API;

import com.githubtest.githubdemoproject.API.Models.GitHubRepos;
import com.githubtest.githubdemoproject.API.Models.UserCommitDiffs;
import com.githubtest.githubdemoproject.API.Models.UserCommitFiles;
import com.githubtest.githubdemoproject.API.Models.UserCommits;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface  GitHubClient {

    @GET("/users/{user}/repos")
    Observable<List<GitHubRepos>>getUserRepos (@Path("user") String user);

    @GET("/repos/{user}/{repoName}/commits")
    Observable<List<UserCommits>>getRepoCommits(@Path("user")String user, @Path("repoName")String repoName);


    @GET("/repos/{user}/{repoName}/commits/{sha}")
    Observable<List<UserCommitDiffs>> getDiffs(@Path("user")String user,
                                               @Path("repoName")String repoName,
                                               @Path("sha")String sha);




}
