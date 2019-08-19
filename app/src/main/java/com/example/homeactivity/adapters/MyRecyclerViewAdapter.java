package com.example.homeactivity.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.homeactivity.R;
import com.example.homeactivity.models.Product;
import com.example.homeactivity.ui.activities.ProductDetailsActivity;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>
{
    public static final String CURRENT_POSITION_VALUE =" tech.muva.academy.android_shoppa.current_position" ;
    @NonNull
    private Context mContext;
    private LayoutInflater inflater;
    //public static  ArrayList<Testproduct> mProductArrayList;
    public static ArrayList<Product> mProductArrayList;
    public static final int CURRENT_POSITION = 361;
    public MyRecyclerViewAdapter(@NonNull Context context, ArrayList<Product> productArrayList) {
        mContext = context;
        mProductArrayList = productArrayList;
    }
    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product_list,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.ViewHolder holder, int position)
    {
        Product product = mProductArrayList.get(position);
        holder.product_price.setText(product.getUnitCost());
        holder.striked_product_price.setText(product.getUnitCost());
        Glide.with(mContext).load(product.getFeaturedImageUrl())
                .placeholder(R.drawable.cart).into(holder.productImage);
//        Toast.makeText(mContext, product.getFeaturedImageUrl(), Toast.LENGTH_LONG).show();
        holder.cart_icon.setImageResource(product.getCart_icon());
        holder.wishlist_icon.setImageResource(product.getWishlist());
        holder.mCurrentPosition = position;
    }
    @Override
    public int getItemCount()
    {
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
            cart_icon.setOnClickListener(this);
            wishlist_icon = itemView.findViewById(R.id.wish_list_icon);
         itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(mContext, ProductDetailsActivity.class);
                 intent.putExtra (CURRENT_POSITION_VALUE,mProductArrayList.get(mCurrentPosition));
                 mContext.startActivity(intent);
             }
         });
}
        @Override
        public void onClick(View view) {
            int itemId = view.getId();

        }
    }
}
