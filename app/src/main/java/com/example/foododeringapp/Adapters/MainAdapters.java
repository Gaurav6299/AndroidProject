package com.example.foododeringapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foododeringapp.DetailsActivity;
import com.example.foododeringapp.Models.MainModels;
import com.example.foododeringapp.R;

import java.util.ArrayList;

public class MainAdapters extends RecyclerView.Adapter<MainAdapters.viewholder> {

    ArrayList<MainModels> list;
    Context context;

    public MainAdapters(ArrayList<MainModels> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.sample_food,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        final MainModels models=list.get(position);
        holder.foodImage.setImageResource(models.getImage());
        holder.name.setText(models.getName());
        holder.price.setText(models.getPrice());
        holder.description.setText(models.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailsActivity.class);
                intent.putExtra("image",models.getImage());
                intent.putExtra("price",models.getPrice());
                intent.putExtra("desc",models.getDescription());
                intent.putExtra("name",models.getName());
                intent.putExtra("type",1);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        ImageView foodImage;
        TextView name,price,description;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            foodImage=itemView.findViewById(R.id.imageView);
            name=itemView.findViewById(R.id.name);
            price=itemView.findViewById(R.id.order);
            description=itemView.findViewById(R.id.description);
        }
    }

}
