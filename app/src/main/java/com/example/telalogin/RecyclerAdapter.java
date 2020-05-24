package com.example.telalogin;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    ArrayList<User> userList;


    public RecyclerAdapter(ArrayList<User> userList) {
        this.userList = userList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_model , parent , false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {

        User user = userList.get(position);
        Log.i("laranja", user.toString());

       holder.emailList.setText("Email: " + user.getEmail()+"");
       holder.usernameList.setText("Username : " + user.getName()+"");
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.emailList)
        TextView emailList;

        @BindView(R.id.usernameList)
        TextView usernameList;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            emailList = itemView.findViewById(R.id.emailList);
            usernameList = itemView.findViewById(R.id.usernameList);
        }
    }
}
