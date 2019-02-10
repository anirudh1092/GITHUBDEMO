package com.githubtest.githubdemoproject.Views;

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
import com.githubtest.githubdemoproject.API.GithubService;
import com.githubtest.githubdemoproject.API.Models.UserCommitDiffs;
import com.githubtest.githubdemoproject.API.Models.UserCommits;
import com.githubtest.githubdemoproject.R;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
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

   private final String TAG=MainActivity.class.getName();

    private ReposRecyclerViewAdapter adapter;
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
        getCommits();
        getDiffs();
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
                       adapter=new ReposRecyclerViewAdapter(getApplicationContext(),data);
                       recyclerView.setAdapter(adapter);

                   }
               });

    }


    private void getCommits(){
         final String user="PhilJay";
         String repoName="MPAndroidChart";
        final GitHubClient client =GithubService.provideGitHubInstance();
        Observable<List<UserCommits>> observable=client.getRepoCommits(user,repoName)
                                                .subscribeOn(Schedulers.io()).
                                                 observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new Subscriber<List<UserCommits>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: "+e.getMessage());
            }

            @Override
            public void onNext(List<UserCommits> userCommits) {
                Log.d(TAG, "onNext:SHA "+userCommits.get(0).getSha());
                Log.d(TAG, "onNext:Login "+userCommits.get(0).getAuthor().getLogin());
            }
        });


    }

    private void getDiffs(){
        String user="PhilJay";
        String repo="MPAndroidChart";
        String sha="aea2ff3417e30d6d4b1ce7e777cbd8bc83e1c95d";
        final GitHubClient client =GithubService.provideGitHubInstance();
        Observable<List<UserCommitDiffs>> observable= client.getDiffs(user,repo,sha).
                                                        subscribeOn(Schedulers.io()).
                                                        observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new Subscriber<List<UserCommitDiffs>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError:Error "+e.getMessage() );
            }

            @Override
            public void onNext(List<UserCommitDiffs> userCommitDiffs) {
                Log.d(TAG, "onNext: "+userCommitDiffs.get(0).getFiles());
            }
        });

    }
}
