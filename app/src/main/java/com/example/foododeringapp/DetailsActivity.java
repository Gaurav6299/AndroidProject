package com.example.foododeringapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.foododeringapp.databinding.ActivityDetailsBinding;

public class DetailsActivity extends AppCompatActivity {

    ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final DBHelper helper = new DBHelper(this);

        if(getIntent().getIntExtra("type",0)==1) {
            final int image = getIntent().getIntExtra("image", 0);
            final int price = Integer.parseInt(getIntent().getStringExtra("price"));
            final String name = getIntent().getStringExtra("name");
            final String description = getIntent().getStringExtra("desc");

            binding.detailImage.setImageResource(image);
            binding.detailPrice.setText(String.format("%d", price));
            binding.detailName.setText(name);
            binding.foodDetail.setText(description);


            binding.insertBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isInserted = helper.insertOrder(
                            binding.nameBox.getText().toString(),
                            binding.phoneBox.getText().toString(),
                            price,
                            image,
                            description,
                            name,
                            Integer.parseInt(binding.quantity.getText().toString())
                    );
                    if (isInserted) {
                        Toast.makeText(DetailsActivity.this, "Data Add successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DetailsActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
           final int id=getIntent().getIntExtra("id",0);
            final Cursor cursor=helper.getOrderById(id);
            final int image=cursor.getInt(4);
            binding.detailImage.setImageResource(image);
            binding.detailPrice.setText(String.format("%d", cursor.getInt(3)));
            binding.detailName.setText(cursor.getString(7));
            binding.foodDetail.setText(cursor.getString(6));
            binding.nameBox.setText(cursor.getString(1));
            binding.phoneBox.setText(cursor.getString(2));
            binding.insertBtn.setText("Update Now");
            binding.insertBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isUpdated=helper.updateOrder(
                            binding.nameBox.getText().toString(),
                            binding.phoneBox.getText().toString(),
                            Integer.parseInt(binding.detailPrice.getText().toString()),
                            image,
                            binding.foodDetail.getText().toString(),
                            binding.detailName.getText().toString(),
                            1,
                            id
                    );
                    if(isUpdated){
                        Toast.makeText(DetailsActivity.this, "Data Update", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(DetailsActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                    }

                }
            });


        }



    }
}