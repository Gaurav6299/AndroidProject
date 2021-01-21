package com.example.foododeringapp.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foododeringapp.DBHelper;
import com.example.foododeringapp.DetailsActivity;
import com.example.foododeringapp.Models.OrderModels;
import com.example.foododeringapp.R;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.viewholder> {

    ArrayList<OrderModels> list;
    Context context;

    public OrderAdapter(ArrayList<OrderModels> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.sample_order,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        final OrderModels models=list.get(position);
        holder.orderImage.setImageResource(models.getOrderImage());
        holder.soldItemName.setText(models.getSoldItemName());
        holder.orderNumber.setText(models.getOrderNumber());
        holder.price.setText(models.getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailsActivity.class);
                intent.putExtra("id",Integer.parseInt(models.getOrderNumber()));
                intent.putExtra("type",2);
                context.startActivity(intent);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete Item")
                        .setMessage("Are you sure to delete this item")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DBHelper helper=new DBHelper(context);
                                if(helper.deleteOrder(models.getOrderNumber())>0){
                                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();

                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        ImageView orderImage;
        TextView soldItemName,orderNumber,price;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            orderImage= itemView.findViewById(R.id.orderImage);
            soldItemName=itemView.findViewById(R.id.orderItemName);
            price=itemView.findViewById(R.id.orderPrice);
            orderNumber=itemView.findViewById(R.id.orderNumber);
        }
    }
}
