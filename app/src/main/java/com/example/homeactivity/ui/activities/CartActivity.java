package com.example.homeactivity.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.homeactivity.R;

import androidx.viewpager.widget.ViewPager;


import com.example.homeactivity.adapters.CartTabLayoutAdapter;
import com.google.android.material.tabs.TabLayout;



public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        CartTabLayoutAdapter cartTabLayoutAdapter =
                new CartTabLayoutAdapter(getSupportFragmentManager());
        ViewPager pager = findViewById(R.id.cart_tabs_pager);
        pager.setAdapter(cartTabLayoutAdapter);
        getWindowManager();

        TabLayout tabLayout = findViewById(R.id.cart_tab);
        tabLayout.setupWithViewPager(pager);
    }
}

