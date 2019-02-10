package com.githubtest.githubdemoproject.Views;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.githubtest.githubdemoproject.API.GitHubClient;
import com.githubtest.githubdemoproject.API.Models.GitHubRepos;
import com.githubtest.githubdemoproject.API.Models.GithubService;
import com.githubtest.githubdemoproject.R;


import com.githubtest.githubdemoproject.databinding.RecyclerviewItemListBinding;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    @BindView(R.id.editText_getUserName)
    EditText editText;
   @BindView(R.id.progress_circular)
    ProgressBar progressBar;


    private RecyclerViewAdapter adapter;
    List<String> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        recyclerView=(RecyclerView)findViewById(R.id.reposRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        data=new ArrayList<>();
        loadData();
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    private void loadData() {
        String temp = "PhilJay";
        final GitHubClient client = GithubService.provideGitHubInstance();
        progressBar.setVisibility(View.VISIBLE);
        Observable<List<String>> observable = client.getUserRepos(temp).
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

               observable.subscribe(new Subscriber<List<String>>() {
                   @Override
                   public void onCompleted() {
                        progressBar.setVisibility(View.GONE);
                   }

                   @Override
                   public void onError(Throwable e) {

                   }

                   @Override
                   public void onNext(List<String> strings) {
                       Log.d("temp", "onNext: "+strings.get(0));
                       for(String str:strings)
                           data.add(str);
                       adapter=new RecyclerViewAdapter(getApplicationContext(),data);
                       recyclerView.setAdapter(adapter);

                   }
               });

    }
}
