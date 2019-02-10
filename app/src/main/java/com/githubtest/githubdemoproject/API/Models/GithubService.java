package com.githubtest.githubdemoproject.API.Models;


import com.githubtest.githubdemoproject.API.GitHubClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class GithubService {
   // https://api.github.com/users/anirudh1092/repos

    public static final String base_URL="https://api.github.com/";

    @Provides

     public static Retrofit provideRetfofitClient(String base_URL){
        return new Retrofit.Builder().
                baseUrl(base_URL).
                client(new OkHttpClient()).
                addCallAdapterFactory(RxJavaCallAdapterFactory.create()).
                addConverterFactory(GsonConverterFactory.create()).
                build();

    }

    @Provides
    public static GitHubClient provideGitHubInstance(){
        return provideRetfofitClient(base_URL).create(GitHubClient.class);
    }
}
