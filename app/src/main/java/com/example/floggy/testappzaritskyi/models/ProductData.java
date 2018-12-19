package com.example.floggy.testappzaritskyi.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProductData implements Parcelable {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("price")
    @Expose
    private String price;

    @SerializedName("Images")
    @Expose
    private ArrayList<ImageData> imgURLData;
    //I don't know why etsy returns sku (A list of distinct SKUs applied to a listing) to me
    @SerializedName("sku")
    @Expose
    private ArrayList<String> sku;

    ProductData() {

    }


    protected ProductData(Parcel in) {
        title = in.readString();
        description = in.readString();
        price = in.readString();
        imgURLData = in.createTypedArrayList(ImageData.CREATOR);
        sku = in.createStringArrayList();
    }

    public static final Creator<ProductData> CREATOR = new Creator<ProductData>() {
        @Override
        public ProductData createFromParcel(Parcel in) {
            return new ProductData(in);
        }

        @Override
        public ProductData[] newArray(int size) {
            return new ProductData[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImgURLData() {
        if (imgURLData != null)
        return this.imgURLData.get(0).getImg_url();
        else return "";
    }

    public void setImgURLData(String imgURLData) {
        ImageData imageData = new ImageData(imgURLData);
        this.imgURLData.set(0,imageData);
    }



    @Override
    public String toString () {
        return "title = " + title + " " + "description = " + description + " " + "price = " + price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(price);
        parcel.writeTypedList(imgURLData);
        parcel.writeStringList(sku);
    }
}
