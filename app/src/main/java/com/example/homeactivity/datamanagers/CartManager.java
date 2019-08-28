package com.example.homeactivity.datamanagers;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.homeactivity.R;
import com.example.homeactivity.models.Cart;
import com.example.homeactivity.models.CartResponse;
import com.example.homeactivity.networking.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartManager {
    Context mContext;
    String orderId;

    public static List<CartResponse> mCartList;

    public CartManager(Context context) {
        mCartList = new ArrayList<>();
        mContext = context;

    }


    public List<CartResponse> add(int productId, int quantity) {
        mCartList.clear();
        Cart cart = new Cart(quantity);
        Call<List<CartResponse>> call = RetrofitClient.getInstance(mContext)
                .getApiConnector()
                .addToCart(productId, cart);
        call.enqueue(new Callback<List<CartResponse>>() {
            @Override
            public void onResponse(Call<List<CartResponse>> call, Response<List<CartResponse>> response) {

            }

            @Override
            public void onFailure(Call<List<CartResponse>> call, Throwable t) {

            }
        });
        return mCartList;
    }

    public List<CartResponse> remove(int orderId) {
        mCartList.clear();
        Call<List<CartResponse>> call = RetrofitClient.getInstance(mContext)
                .getApiConnector()
                .removeCart(orderId);
        call.enqueue(new Callback<List<CartResponse>>() {
            @Override
            public void onResponse(Call<List<CartResponse>> call, Response<List<CartResponse>> response) {
                if (response.code() == 201) {
                    mCartList.addAll(response.body());
                } else {

                }
            }

            @Override
            public void onFailure(Call<List<CartResponse>> call, Throwable t) {

            }
        });
        return mCartList;
    }

    public List<CartResponse> update(int orderId, int quantity) {
        mCartList.clear();
        Call<List<CartResponse>> call = RetrofitClient.getInstance(mContext)
                .getApiConnector()
                .updateCart(orderId, quantity);
        call.enqueue(new Callback<List<CartResponse>>() {
            @Override
            public void onResponse(Call<List<CartResponse>> call, Response<List<CartResponse>> response) {
                if (response.code() == 201) {
                    mCartList.addAll(response.body());
                } else {

                }
            }

            @Override
            public void onFailure(Call<List<CartResponse>> call, Throwable t) {

            }
        });
        return mCartList;
    }

    public List<CartResponse> get() {
        mCartList.clear();
        Call<List<CartResponse>> call = RetrofitClient.getInstance(mContext)
                .getApiConnector()
                .getCart();
        call.enqueue(new Callback<List<CartResponse>>() {
            @Override
            public void onResponse(Call<List<CartResponse>> call, Response<List<CartResponse>> response) {
                if (response.code() == 200) {
                    mCartList.addAll(response.body());
                    Toast.makeText(mContext, mCartList.get(0).getName(), Toast.LENGTH_SHORT).show();
                } else {

                }
            }

            @Override
            public void onFailure(Call<List<CartResponse>> call, Throwable t) {
            }
        });
        return mCartList;
    }

}
