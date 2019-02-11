package com.githubtest.githubdemoproject.Views.ReposModule;

import com.githubtest.githubdemoproject.API.GitHubClient;
import com.githubtest.githubdemoproject.API.GithubService;
import com.githubtest.githubdemoproject.API.Models.GitHubRepos;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class ReposActivityModel implements  ReposActivityMVP.Model {

    @Override
    public GitHubClient getGiHubClient() {
        return GithubService.provideGitHubInstance();
    }

    public Observable<List<String>> getObservable(String userName){
        return getGiHubClient().getUserRepos(userName).
                map(new Func1<List<GitHubRepos>, List<String>>() {
                    @Override
                    public List<String> call(List<GitHubRepos> gitHubRepos) {
                        List<String> strList=new ArrayList<>();
                        for(int i=0;i<gitHubRepos.size();i++){
                            strList.add(gitHubRepos.get(i).getName());
                        }
                        return strList;
                    }
                }).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread());

    }
}
