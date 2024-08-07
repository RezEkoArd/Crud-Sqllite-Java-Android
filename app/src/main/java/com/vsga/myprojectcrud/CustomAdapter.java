package com.vsga.myprojectcrud;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    final private Context context;
    Activity activity;
    final private ArrayList siswa_id,siswa_name,siswa_age,siswa_address;



    CustomAdapter(Activity activity, Context context,
                  ArrayList siswa_id,
                  ArrayList siswa_name,
                  ArrayList siswa_age,
                  ArrayList siswa_address) {

        this.activity = activity;
        this.context = context;
        this.siswa_id = siswa_id;
        this.siswa_name = siswa_name;
        this.siswa_age = siswa_age;
        this.siswa_address = siswa_address;
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.siswa_id_txt.setText(String.valueOf(siswa_id.get(position)));
        holder.siswa_name_txt.setText(String.valueOf(siswa_name.get(position)));
        holder.siswa_age_txt.setText(String.valueOf(siswa_age.get(position)));
        holder.siswa_address_txt.setText(String.valueOf(siswa_address.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(siswa_id.get(position)));
                intent.putExtra("name", String.valueOf(siswa_name.get(position)));
                intent.putExtra("age", String.valueOf(siswa_age.get(position)));
                intent.putExtra("address", String.valueOf(siswa_address.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return siswa_id.size();

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView siswa_id_txt, siswa_name_txt, siswa_age_txt, siswa_address_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            siswa_id_txt = itemView.findViewById(R.id.siswa_id_txt);
            siswa_name_txt = itemView.findViewById(R.id.siswa_name_txt);
            siswa_age_txt = itemView.findViewById(R.id.siswa_age_txt);
            siswa_address_txt = itemView.findViewById(R.id.siswa_address_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
