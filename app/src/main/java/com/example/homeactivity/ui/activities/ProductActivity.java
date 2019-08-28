package com.example.homeactivity.ui.activities;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.homeactivity.R;
import com.example.homeactivity.adapters.TabAdapter;
import com.example.homeactivity.auth.LoginDialogFragment;
import com.example.homeactivity.ui.fragments.DynamicFragment;
import com.example.homeactivity.utils.SharedPreferencesConfig;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class ProductActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    public static ImageView bottoAppBarCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Toolbar mainToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mainToolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        bottoAppBarCart = findViewById(R.id.bottm_cartmage);

        bottoAppBarCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!new SharedPreferencesConfig(ProductActivity.this).isloggedIn()){
                    new LoginDialogFragment(ProductActivity.this).startDialog(getSupportFragmentManager());
                }else{
                    startActivity(new Intent(ProductActivity.this, CartActivity.class));
                }
            }
        });
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mainToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        ViewPager viewPager = findViewById(R.id.main_tabs_pager);
        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager(), ProductActivity.this);
        tabAdapter.notifyDataSetChanged();
        viewPager.setAdapter(tabAdapter);
        TabLayout tabLayout = findViewById(R.id.main_tab);
        tabLayout.setupWithViewPager(viewPager);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    public static DynamicFragment newInstance(int val) {
        DynamicFragment fragment = new DynamicFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", val);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            Intent i = new Intent(ProductActivity.this, SettingsActivity.class);
//            startActivity(i);
//        }
    return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.navigation_cart) {
            // Handle the camera action
        }
        else if (id == R.id.navigation_wishlist) {

        }
//        else if (id == R.id.nav_settings) {
//            Intent i = new Intent(ProductActivity.this, SettingsActivity.class);
//            startActivity(i);
//​
//        }
        else if (id == R.id.nav_share) {

        }
        else if (id == R.id.nav_send) {

        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
