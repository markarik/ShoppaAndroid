package com.example.homeactivity.ui.fragments;


import android.os.Bundle;

import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.homeactivity.R;
import com.example.homeactivity.adapters.MyRecyclerViewAdapter;
import com.example.homeactivity.models.Product;
import com.example.homeactivity.networking.RetrofitClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DynamicFragment extends Fragment {
    public DynamicFragment() {
        // Required empty public constructor
    }
    private List<Product> mProductList = new ArrayList<>();
    private ArrayList<Product> categoryProducts = new ArrayList<>();
    private MyRecyclerViewAdapter mMyRecyclerViewAdapter;
    int val;
    String fragment_name;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onResume() {
        super.onResume();
        getCategorisedProducts();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dynamic,container,false);
        RecyclerView recyclerView = view.findViewById(R.id.electronics_recycler_view);

        //populateRecyclerView();
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        mMyRecyclerViewAdapter = new MyRecyclerViewAdapter(getActivity(),categoryProducts);
        recyclerView.setAdapter(mMyRecyclerViewAdapter);
        TextView fragment_number = view.findViewById(R.id.fragment_number);
        val = getArguments().getInt("someInt",0);
        fragment_number.setText(" "+val);
        return view;
    }
    public static DynamicFragment newInstance(int val, String fragmentname) {
        DynamicFragment fragment = new DynamicFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", val);
        args.putString("fragment_name",fragmentname);
        fragment.setArguments(args);
        return fragment;
    }

    private  void getCategorisedProducts()
    {

        fragment_name = getArguments().getString("fragment_name",fragment_name);
        Call<List<Product>> call = RetrofitClient.getInstance(getActivity())
                .getApiConnector()
                .getProducts(fragment_name);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                if(response.code() == 200)
                {
                    categoryProducts.clear();
                    mProductList.clear();
                    mProductList.addAll(response.body());
                    categoryProducts.addAll(mProductList);
                    mMyRecyclerViewAdapter.notifyDataSetChanged();
                }
                else
                {
                    Toast.makeText(getActivity(), "Code: "+response.code(),
                            Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

                if (t instanceof IOException)
                {
                    Toast.makeText(getActivity(), "network failure", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }



}
