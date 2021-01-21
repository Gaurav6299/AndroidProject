package com.example.foododeringapp.Models;

public class OrderModels {
int orderImage;
String soldItemName,orderNumber,price;

    public OrderModels(int orderImage, String soldItemName, String orderNumber, String price) {
        this.orderImage = orderImage;
        this.soldItemName = soldItemName;
        this.orderNumber = orderNumber;
        this.price = price;
    }

    public OrderModels() {

    }

    public int getOrderImage() {
        return orderImage;
    }

    public void setOrderImage(int orderImage) {
        this.orderImage = orderImage;
    }

    public String getSoldItemName() {
        return soldItemName;
    }

    public void setSoldItemName(String soldItemName) {
        this.soldItemName = soldItemName;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
