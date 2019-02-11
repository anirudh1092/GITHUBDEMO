package com.githubtest.githubdemoproject.Views.CommitsModule;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.githubtest.githubdemoproject.API.Models.UserCommits;
import com.githubtest.githubdemoproject.R;
import com.githubtest.githubdemoproject.Views.ReposModule.ReposRecyclerViewAdapter;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;

import static android.content.ContentValues.TAG;

public class CommitsRecyclerViewAdapter extends RecyclerView.Adapter<CommitsRecyclerViewAdapter.ViewHolder> {

    List<UserCommits> dataList;
    Context context;

    public CommitsRecyclerViewAdapter(Context context, List<UserCommits> dataList) {
        this.dataList = dataList;
        this.context=context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.recyclerview_item_list,viewGroup,false);
        CommitsRecyclerViewAdapter.ViewHolder viewHolder=new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.textView.setText(dataList.get(i).getAuthor().getLogin());
    }



    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.textView_githubRepos)
        TextView textView;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.textView_githubRepos);
            //  Activity activity=(Activity)context;
            Log.d(TAG, "ViewHolder: "+context);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getLayoutPosition();
                    Intent intent= new Intent(context, CommitsActivity.class);
                    Gson gson =new Gson();
                    String commitDataJson= gson.toJson(dataList.get(position));
                    intent.putExtra("SelectedRepo",commitDataJson);
                    context.startActivity(intent);
                }
            });
        }
    }


}
