package com.example.covidapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<Model> mList;
    Context context;

    public MyAdapter(Context context , ArrayList<Model> mList){

        this.mList = mList;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.data , parent ,false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull  MyAdapter.MyViewHolder holder, int position) {
        Model model = mList.get(position);
        holder.name.setText(model.getname());
        holder.contactNo.setText(model.getcontactNo());
        holder.DoYouHaveCovid.setText(model.getDoYouHaveCovid());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static  class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name, contactNo, DoYouHaveCovid;
        public MyViewHolder(@NonNull  View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name_text);
            contactNo = itemView.findViewById(R.id.campusid_text);
            DoYouHaveCovid = itemView.findViewById(R.id.contact_text);
        }
    }
}
