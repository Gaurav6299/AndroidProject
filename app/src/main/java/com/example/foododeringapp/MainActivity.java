package com.example.foododeringapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.foododeringapp.Adapters.MainAdapters;
import com.example.foododeringapp.Models.MainModels;
import com.example.foododeringapp.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<MainModels> list=new ArrayList<>();
        list.add(new MainModels(R.drawable.food1,"Kadhai Paneer","80","Kadhai Paneer with extra cheese"));
        list.add(new MainModels(R.drawable.food4,"Egg Chawmeeen","120","Egg Chawmeen with  monchurian"));
        list.add(new MainModels(R.drawable.food5,"Chicken Tanduri","60","Chicken tanduri with extra chicken"));
        list.add(new MainModels(R.drawable.mushrooms,"Chicken Mushrooms","75","This is very tasty and chicken mushrooms"));
        list.add(new MainModels(R.drawable.tikka,"Chicken Tikka","120","Four chicken tikka only."));
        list.add(new MainModels(R.drawable.roti,"Tanduri Roti","80","Delicious Tanduri roti"));
        list.add(new MainModels(R.drawable.momo,"Momos","20","Delicious vag momos"));

        list.add(new MainModels(R.drawable.butternaan,"Butter Naan","25","Delicious Butter Naan with large butter."));
        list.add(new MainModels(R.drawable.dhosa,"Dhosa","20","Mysore dhosa."));
        list.add(new MainModels(R.drawable.momochi,"Chicken Momo","40","Delicious chicken momos"));


        list.add(new MainModels(R.drawable.food3,"Egg Roll","50","Two egg rolls"));
        list.add(new MainModels(R.drawable.chimoglai,"Chicken Muglai","30","Delicious chicken muglai."));
        list.add(new MainModels(R.drawable.burger,"Burger","40","Veg Burger with extra cheese"));
        list.add(new MainModels(R.drawable.food7,"Pizza","65","Pizza with extra cheese only"));
        list.add(new MainModels(R.drawable.food2,"Cheese Burger","70","Veg Burger with extra large cheese"));
        list.add(new MainModels(R.drawable.roll,"Pizza Roll","65","This is very delicious pizza roll."));
        list.add(new MainModels(R.drawable.food8,"Pizza Burger","125","Delicious Pizza Buger."));

       MainAdapters adapters=new MainAdapters(list , this);
       binding.recyclerview.setAdapter(adapters);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(layoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.orders:
                startActivity(new Intent(MainActivity.this,OrderActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void Upload(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),SignIn_Activity.class));
        finish();
    }
}