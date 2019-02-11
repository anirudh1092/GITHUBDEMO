package com.githubtest.githubdemoproject.Views.ReposModule;

import com.githubtest.githubdemoproject.API.Models.GitHubRepos;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class ReposActivityPresenter implements ReposActivityMVP.Presenter {

    ReposActivityMVP.Model model;
    ReposActivityMVP.View view;

    Observable<List<String>> observable;

    @Override
    public void loadData(String userName) {

        observable=model.getObservable(userName);

        observable.subscribe(new Subscriber<List<String>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<String> strings) {
                for(String str: strings){
                    view.updateData(str);
                }
            }
        });


    }

    @Override
    public void unsubscribeRx() {
        observable.unsubscribeOn(Schedulers.immediate());
    }

    @Override
    public void setView(ReposActivityMVP.View v) {
            this.view=v;
    }
}
