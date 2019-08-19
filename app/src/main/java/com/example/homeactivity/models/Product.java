package com.example.homeactivity.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product implements Parcelable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("short_description")

    @Expose
    private String shortDescription;
    @SerializedName("brand")
    @Expose
    private Integer brand;
    @SerializedName("unit_cost")
    @Expose
    private String unitCost;
    @SerializedName("featured_image_url")
    @Expose
    private String featuredImageUrl;
    private int cart_icon;
    private int wishlist;

    protected Product(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        shortDescription = in.readString();
        if (in.readByte() == 0) {
            brand = null;
        } else {
            brand = in.readInt();
        }
        unitCost = in.readString();
        featuredImageUrl = in.readString();
        cart_icon = in.readInt();
        wishlist = in.readInt();
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

    public int getCart_icon() {
        return cart_icon;
    }
    public void setCart_icon(int cart_icon) {
        this.cart_icon = cart_icon;
    }
    public int getWishlist() {
        return wishlist;
    }
    public void setWishlist(int wishlist) {
        this.wishlist = wishlist;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getShortDescription() {
        return shortDescription;
    }
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
    public Integer getBrand() {
        return brand;
    }
    public void setBrand(Integer brand) {
        this.brand = brand;
    }
    public CharSequence getUnitCost() {
        return unitCost;
    }
    public void setUnitCost(String unitCost) {
        this.unitCost = unitCost;
    }
    public String getFeaturedImageUrl() {
        return featuredImageUrl;
    }
    public void setFeaturedImageUrl(String featuredImageUrl) {
        this.featuredImageUrl = featuredImageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(name);
        dest.writeString(shortDescription);
        if (brand == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(brand);
        }
        dest.writeString(unitCost);
        dest.writeString(featuredImageUrl);
        dest.writeInt(cart_icon);
        dest.writeInt(wishlist);
    }
}