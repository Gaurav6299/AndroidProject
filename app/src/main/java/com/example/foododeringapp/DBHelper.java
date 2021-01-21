package com.example.foododeringapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.foododeringapp.Models.OrderModels;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    final static String DBNAME="RedFood.db";
    final static int DBVERSION=1;
    public DBHelper(@Nullable Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table shopping"+
                        "(id integer primary key autoincrement,"+
                        "name text,"+
                        "phone text,"+
                        "price int,"+
                        "image int,"+
                        "quantity int,"+
                        "description text,"+
                        "foodname text)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP table if exists shopping");
        onCreate(db);

    }

   public boolean insertOrder(String name,String phone,int price,int image,String desc,String foodName,int quantity)
   {
       SQLiteDatabase db=getWritableDatabase();
       ContentValues values=new ContentValues();
       values.put("name",name);
       values.put("phone",phone);
       values.put("price",price);
       values.put("image",image);
       values.put("description",desc);
       values.put("foodname",foodName);
       values.put("quantity",quantity);
       long id=db.insert("shopping",null,values);
       if(id<=0){
           return false;
       }
       else{
           return true;
       }
   }

   public ArrayList<OrderModels> getOrders()
   {
       ArrayList<OrderModels> orders=new ArrayList<>();
       SQLiteDatabase db=this.getWritableDatabase();
       Cursor cursor=db.rawQuery("select id,foodname,image,price from shopping",null);
       if(cursor.moveToFirst()){
           while (cursor.moveToNext()){
               OrderModels models=new OrderModels();
               models.setOrderNumber(cursor.getInt(0)+"");
               models.setSoldItemName(cursor.getString(1));
               models.setOrderImage(cursor.getInt(2));
               models.setPrice(cursor.getInt(3)+"");
               orders.add(models);
           }
       }
       cursor.close();
       db.close();
       return orders;
   }

   public Cursor getOrderById(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from shopping where id="+id,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        return  cursor;
   }

    public boolean updateOrder(String name,String phone,int price,int image,String desc,String foodName,int quantity,int id)
    {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",name);
        values.put("phone",phone);
        values.put("price",price);
        values.put("image",image);
        values.put("description",desc);
        values.put("foodname",foodName);
        values.put("quantity",quantity);
        long row=db.update("shopping",values,"id="+id,null);
        if(row<=0){
            return false;
        }
        else{
            return true;
        }
    }

    public int deleteOrder(String id){
      SQLiteDatabase db=this.getWritableDatabase();
      return db.delete("shopping","id="+id,null);
    }



}
