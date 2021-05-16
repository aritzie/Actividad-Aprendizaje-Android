package com.svalero.vintedandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.svalero.vintedandroid.R;
import com.svalero.vintedandroid.beans.User;

import java.util.ArrayList;

public class ListUserAdapter extends RecyclerView.Adapter<ListUserAdapter.UserViewHolder> {

    private ArrayList<User> listUsers;
    private Context context;

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        public TextView userName;
        public TextView lastName;
        public TextView numberSales;

        public UserViewHolder(View v) {
            super(v);
            userName = v.findViewById(R.id.txtUserName);
            lastName = v.findViewById(R.id.txtLastName);
            numberSales = v.findViewById(R.id.txtSales);
        }
    }

    public ListUserAdapter(ArrayList<User> listUsers, Context context){
        this.listUsers = listUsers;
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_user, parent, false);
        return new UserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = listUsers.get(position);

        holder.userName.setText(user.getName());
        holder.lastName.setText((user.getLastName()));
        holder.numberSales.setText(String.valueOf(user.getNumberSales()));;
    }

    @Override
    public int getItemCount() {
        return listUsers.size();
    }

}
