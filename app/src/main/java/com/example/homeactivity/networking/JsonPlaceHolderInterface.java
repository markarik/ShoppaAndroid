package com.example.homeactivity.networking;
import com.example.homeactivity.models.Category;
import com.example.homeactivity.models.Product;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonPlaceHolderInterface {
    //    @FormUrlEncoded
    @GET("android/category")
    Call<List<Category>> getCategories();
    @GET("android/product")
    Call<List<Product>> getProducts();
}
