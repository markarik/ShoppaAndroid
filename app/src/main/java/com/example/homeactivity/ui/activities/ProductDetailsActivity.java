package com.example.homeactivity.ui.activities;

import android.app.Activity;
import android.content.Context;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.example.homeactivity.R;
import com.example.homeactivity.adapters.ProductRecyclerViewAdapter;
import com.example.homeactivity.adapters.ViewPagerAdapter;
import com.example.homeactivity.models.Product;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;


import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class ProductDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int DEFAULT_INITIAL_POSITION = -1;
    private RelativeLayout mRelativeLayout;
    private Context mContext;
    private Activity mActivity;
    ViewPager viewPager;
    TabLayout tabLayout;
    private View contentView;
    private View loadingView;
    private Toolbar mTopToolbar;
    private PopupWindow mPopupWindow;
    private Product product;
    private ImageView productImage;
    private TextView productName;
    private TextView productPrice;
    private TextView productStrikedPrice;
    private TextView productDescription;
    private TextView toolbar_title;
    ViewPagerAdapter viewPagerAdapter;
    NumberPicker numberPicker;
    private int maxInventoryQuantity = 20, minInventoryQuantity = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);


        BottomNavigationView navView = findViewById(R.id.nav_view);
        Button buyProduct = findViewById(R.id.buy_product);
        Button toggleDescription = findViewById(R.id.toggle_description);
        contentView = findViewById(R.id.content_description);
        loadingView = findViewById(R.id.content_image);
        viewPager = findViewById(R.id.myViewPager);
        tabLayout = findViewById(R.id.tab_layout);
        productImage = findViewById(R.id.detail_product_image);
        ImageButton backPressed = findViewById(R.id.action_back);
        RatingBar ratingBar = findViewById(R.id.rating);
        ImageView wishlist = findViewById(R.id.product_add_wishlist);
        ImageView cart = findViewById(R.id.product_add_cart);
        productStrikedPrice = findViewById(R.id.product_former_price);
        productDescription = findViewById(R.id.product_detailed_description);
        productName = findViewById(R.id.product_name);
        productPrice = findViewById(R.id.product_price_recy);

        toolbar_title=findViewById(R.id.toolbar_title);
        viewPagerAdapter = new ViewPagerAdapter(this);
        //ViewPagerAdapter.images = new Integer[]{R.drawable.pager1,R.drawable.pager2,R.drawable.pager4};
        //viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager, true);

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        buyProduct.setOnClickListener(this);
        toggleDescription.setOnClickListener(this);
        ratingBar.setOnClickListener(this);
        backPressed.setOnClickListener(this);
        wishlist.setOnClickListener(this);
        cart.setOnClickListener(this);


        // Initially hide the content view.
        contentView.setVisibility(View.GONE);


        mTopToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mTopToolbar);

        // Get the application context
        mContext = getApplicationContext();

        // Get the activity
        mActivity = ProductDetailsActivity.this;
        mRelativeLayout = findViewById(R.id.rl);


    }


    @Override
    protected void onResume() {
        super.onResume();
        if (getIntent().hasExtra(ProductRecyclerViewAdapter.CURRENT_POSITION_VALUE)) {
            product = getIntent().getParcelableExtra(ProductRecyclerViewAdapter.CURRENT_POSITION_VALUE);
            fillData();
        }
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent(ProductDetailsActivity.this, ProductActivity.class);
                    startActivity(intent);
                    finish();

                    break;
                case R.id.heart:
                    Toast.makeText(ProductDetailsActivity.this, "This goes somewhere", Toast.LENGTH_SHORT).show();

                    break;
                case R.id.navigation_cart:
                    Toast.makeText(ProductDetailsActivity.this, "This goes somewhere", Toast.LENGTH_SHORT).show();

                    break;
            }
            return false;
        }
    };


    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.buy_product) {
            addingItemQuantityPopUp();

        } else if (id == R.id.toggle_description) {
            toggleDescriptionVisibility();
        } else if (id == R.id.rating) {
            Toast.makeText(ProductDetailsActivity.this, "You can only rate it when you buy it", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.product_add_wishlist) {
            Toast.makeText(ProductDetailsActivity.this, "Added to Wish List", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.action_back) {
            Intent intent = new Intent(ProductDetailsActivity.this, ProductActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.product_add_cart) {
            addingItemQuantityPopUp();
        }
    }


    private void addingItemQuantityPopUp() {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

        // Inflate the custom layout/view
        View customView = inflater.inflate(R.layout.item_quantity_pop_up_layout, null);

        numberPicker = customView.findViewById(R.id.numberPicker);


        numberPicker.setMaxValue(maxInventoryQuantity);
        numberPicker.setMinValue(minInventoryQuantity);


        // Initialize a new instance of popup window
        mPopupWindow = new PopupWindow(
                customView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        // Set an elevation value for popup window
        // Call requires API level 21
        if (Build.VERSION.SDK_INT >= 21) {
            mPopupWindow.setElevation(5.0f);
        }

        Button closePopUpButton = customView.findViewById(R.id.close_pop_up);
        Button addQuantityFromPopUp = customView.findViewById(R.id.button_add_quantity);

        addQuantityFromPopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProductDetailsActivity.this, "Added to Cart", Toast.LENGTH_SHORT).show();
                mPopupWindow.dismiss();
            }
        });


        // Set a click listener for the popup window close button
        closePopUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Dismiss the popup window
                mPopupWindow.dismiss();
            }
        });


        mPopupWindow.showAtLocation(mRelativeLayout, Gravity.CENTER, 0, 0);
    }


    private void toggleDescriptionVisibility() {
        Button toggleDescription = findViewById(R.id.toggle_description);

        if (toggleDescription.getText().toString().equals("Description")) {
            contentView.setAlpha(1f);
            contentView.setVisibility(View.VISIBLE);
            loadingView.scrollTo(0, loadingView.getBottom());
            toggleDescription.setText("Hide Description");
        } else {
            contentView.setAlpha(0f);
            contentView.setVisibility(View.GONE);
            loadingView.scrollTo(0, loadingView.getBottom());
            toggleDescription.setText("Description");
        }
    }


    private void fillData() {

        ViewPagerAdapter.images = new String[]{product.getFeaturedImageUrl()};
        productName.setText(product.getName());
        productPrice.setText(product.getUnitCost());
        productStrikedPrice.setText(product.getUnitCost());
        productDescription.setText(product.getShortDescription());
toolbar_title.setText(product.getName());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager, true);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

}
