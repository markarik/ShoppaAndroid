package com.example.homeactivity.models;

import android.os.Parcel;
import android.os.Parcelable;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity public class Product implements Parcelable {
    @Id long id;
    private  String category,name,price,strikedprice,description;

    private  int image;

    public Product() {
    }

    protected Product(Parcel in) {
        category = in.readString();
        name = in.readString();
        price = in.readString();
        strikedprice = in.readString();
        description = in.readString();
        image = in.readInt();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getStrikedprice() {
        return strikedprice;
    }

    public String getDescription() {
        return description;
    }

    public int getImage() {
        return image;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setStrikedprice(String strikedprice) {
        this.strikedprice = strikedprice;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(category);
        dest.writeString(name);
        dest.writeString(price);
        dest.writeString(strikedprice);
        dest.writeString(description);
        dest.writeInt(image);
    }
}
