package com.githubtest.githubdemoproject.Views.ReposModule;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.githubtest.githubdemoproject.R;
import com.githubtest.githubdemoproject.Views.CommitsModule.CommitsActivity;

import java.util.List;

import butterknife.BindView;

import static android.content.ContentValues.TAG;

public class ReposRecyclerViewAdapter extends RecyclerView.Adapter<ReposRecyclerViewAdapter.ViewHolder> {

    List<String> dataList;
    Context context;

    public ReposRecyclerViewAdapter(Context context, List<String> dataList) {
        this.dataList = dataList;
        this.context=context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View itemView= LayoutInflater.from(viewGroup.getContext()).
               inflate(R.layout.recyclerview_item_list,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            String name= dataList.get(i);
            viewHolder.textView.setText(name);
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

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
                   intent.putExtra("SelectedRepo",dataList.get(position));
                    context.startActivity(intent);
                }
            });
        }
    }


}
