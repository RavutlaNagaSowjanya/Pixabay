package com.example.acer.pixabay1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<Datastore> data;

    public MyAdapter(MainActivity mainActivity, ArrayList<Datastore> datastore) {
        this.context=mainActivity;
        this.data=datastore;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(context).inflate(R.layout.listitem,parent,false);
        return new MyViewHolder(v);
        }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        Datastore datastores=data.get(position);
        holder.c.setText(datastores.comments);
        holder.l.setText(datastores.likes);
        holder.t.setText(datastores.tags);
        holder.v.setText(datastores.views);
     /*   if(datastores!=null)*/
        try{
            Picasso.with(context).load(datastores.image).placeholder(R.mipmap.ic_launcher).into(holder.i);
        }catch (Exception e){
            e.printStackTrace();
            Picasso.with(context).load(R.mipmap.ic_launcher).into(holder.i);
        }


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView v,c,l,t;
        ImageView i;
        public MyViewHolder(View itemView) {
            super(itemView);
           i=itemView.findViewById(R.id.img);
            v=itemView.findViewById(R.id.vi);
            c=itemView.findViewById(R.id.com);
            l=itemView.findViewById(R.id.li);
            t=itemView.findViewById(R.id.ta);
        }
    }
}
