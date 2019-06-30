package com.pari.cakecollections.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pari.cakecollections.R;
import com.pari.cakecollections.model.CakeDetail;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CakeAdapter extends RecyclerView.Adapter<CakeAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<CakeDetail> cake = new ArrayList<>();

    public  CakeAdapter(Context context,ArrayList<CakeDetail> cake){
        this.context = context;
        this.cake = cake;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CakeAdapter.MyViewHolder holder, int position) {
        Log.v("cake name",cake.get(position).getTitle());

        holder.cakeTitleName.setText(cake.get(position).getTitle());
        Picasso.get().load(cake.get(position).getImage()).into(holder.cakeImageView);

    }

    @Override
    public int getItemCount() {
        Log.v("cake count",cake.toString());

        return cake.size();

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView cakeTitleName;
        ImageView cakeImageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            cakeTitleName = (TextView)itemView.findViewById(R.id.cake_title);
            cakeImageView = (ImageView)itemView.findViewById(R.id.cake_img);
        }
    }
}
