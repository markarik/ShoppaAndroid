package com.example.homeactivity.ui.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.homeactivity.R;
import com.example.homeactivity.adapters.CartRecyclerAdapter;
import com.example.homeactivity.models.CartResponse;
import com.example.homeactivity.models.Product;
import com.example.homeactivity.networking.RetrofitClient;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {


    public CartFragment() {
        // Required empty public constructor
    }

    private ArrayList<Product> mProductArrayList = new ArrayList<>();
    private List<Product> mProductList = new ArrayList<>();
    private CartRecyclerAdapter mCartRecyclerAdapter;
    private ArrayList<CartResponse> mCartResponses=new ArrayList<>();
    Integer buyer_id = 5;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.cart_recycler_view);
        recyclerView.hasFixedSize();

        mCartRecyclerAdapter = new CartRecyclerAdapter(getActivity(),mCartResponses);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mCartRecyclerAdapter);
        getCart();
        return view;
    }

    public void getCart(){
        ArrayList<CartResponse> cartResponses;
        mCartResponses.clear();
        Call<List<CartResponse>> call = RetrofitClient.getInstance(getActivity())
                .getApiConnector()
                .getCart();
        call.enqueue(new Callback<List<CartResponse>>() {
            @Override
            public void onResponse(Call<List<CartResponse>> call, Response<List<CartResponse>> response) {
                if(response.code()==200){
                    mCartResponses.addAll(response.body());
                    mCartRecyclerAdapter.notifyDataSetChanged();

                }
                else{

                }
            }

            @Override
            public void onFailure(Call<List<CartResponse>> call, Throwable t) {
            }

        });

    }
}
