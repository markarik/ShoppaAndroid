package com.example.homeactivity.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {
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
}