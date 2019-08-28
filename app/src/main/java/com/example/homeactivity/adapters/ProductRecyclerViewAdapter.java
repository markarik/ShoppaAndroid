package com.example.homeactivity.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.homeactivity.R;
import com.example.homeactivity.auth.LoginDialogFragment;
import com.example.homeactivity.datamanagers.CartManager;
import com.example.homeactivity.models.Product;
import com.example.homeactivity.ui.activities.ProductDetailsActivity;
import com.example.homeactivity.utils.SharedPreferencesConfig;

import java.util.ArrayList;

public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewAdapter.ViewHolder> {
    public static final String CURRENT_POSITION_VALUE = " tech.muva.academy.android_shoppa.current_position";
    @NonNull
    private Context mContext;
    private LayoutInflater inflater;
    //public static  ArrayList<Testproduct> mProductArrayList;
    public static ArrayList<Product> mProductArrayList;
    public static final int CURRENT_POSITION = 361;

    private FragmentManager mFragmentManager;

    public ProductRecyclerViewAdapter(@NonNull Context context, ArrayList<Product> productArrayList,FragmentManager fragmentManager) {
        mContext = context;
        mProductArrayList = productArrayList;
        mFragmentManager = fragmentManager;

    }

    @Override
    public ProductRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductRecyclerViewAdapter.ViewHolder holder, int position) {
        Product product = mProductArrayList.get(position);
        holder.product_price.setText(product.getUnitCost() + "kSH");
        holder.striked_product_price.setText(product.getUnitCost() + "kSH");
        Glide.with(mContext).load(product.getFeaturedImageUrl())
                .placeholder(R.drawable.cart).into(holder.productImage);
//        Toast.makeText(mContext, product.getFeaturedImageUrl(), Toast.LENGTH_LONG).show();
        holder.product_name.setText(product.getName());

        holder.mCurrentPosition = position;
    }

    @Override
    public int getItemCount() {
        return mProductArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView productImage, cart_icon, wishlist_icon;
        TextView product_price;
        TextView product_name;
        TextView striked_product_price;
        public int mCurrentPosition;

        public ViewHolder(View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image_recy);
            product_price = itemView.findViewById(R.id.product_price_recy);
            striked_product_price = itemView.findViewById(R.id.striked_product_price);
            cart_icon = itemView.findViewById(R.id.cart_icon);
            wishlist_icon = itemView.findViewById(R.id.wish_list_icon);
            product_name =itemView.findViewById(R.id.product_name_recy);
            cart_icon.setOnClickListener(this);
            wishlist_icon = itemView.findViewById(R.id.wish_list_icon);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ProductDetailsActivity.class);
                    intent.putExtra(CURRENT_POSITION_VALUE, mProductArrayList.get(mCurrentPosition));
                    mContext.startActivity(intent);
                }
            });


        }

        @Override
        public void onClick(View view) {
            int itemId = view.getId();

            if (itemId == R.id.cart_icon){
                if (!new SharedPreferencesConfig(mContext).isloggedIn()){
                    new LoginDialogFragment(mContext).startDialog(mFragmentManager);
                }else{
                    //add to cart
                    new CartManager(mContext).add(mProductArrayList.get(mCurrentPosition).getId(),1);

                }
            }

        }
    }
}
