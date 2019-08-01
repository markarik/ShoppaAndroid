package com.example.homeactivity.networking;

import com.example.homeactivity.models.Category;
import com.example.homeactivity.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface JsonPlaceHolderInterface {

    @GET("api/categories")
    Call<List<Category>> getCategories();


    @GET("api/category/products/{category}")
    Call<List<Product>> getProducts(@Path("category") String category);
}
