package com.example.driveandlog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {

    class UserViewHolder extends RecyclerView.ViewHolder {
        private final TextView userItemView;

        private UserViewHolder(View itemView){
            super(itemView);
            userItemView = itemView.findViewById(R.id.textView);
        }
    }


    private final LayoutInflater mInflater;
    private List<User> mUsers;

    UserListAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = mInflater.inflate(R.layout.recyclerview_user,parent, false);
        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position){
        if(mUsers != null){
            User current = mUsers.get(position);
            holder.userItemView.setText(current.getUser());
        } else {
            // If data is not processed

            holder.userItemView.setText("No user");
        }
    }

    void setUsers(List<User> users){
        mUsers = users;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount(){
        if (mUsers!= null){
            return mUsers.size();
        } else {
            return 0;
        }
    }
}
