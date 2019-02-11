package com.githubtest.githubdemoproject.Views.ReposModule;

import com.githubtest.githubdemoproject.API.GitHubClient;
import com.githubtest.githubdemoproject.API.Models.GitHubRepos;

import java.util.List;

import rx.Observable;

public interface ReposActivityMVP {

    interface Model{
        public GitHubClient getGiHubClient();

        public Observable<List<String>>getObservable(String userName);
    }

    interface Presenter{

        public void loadData(String userName);

        public void unsubscribeRx();


        public void setView(View v);
    }

    interface  View{

        public void updateData(String data);
    }
}
