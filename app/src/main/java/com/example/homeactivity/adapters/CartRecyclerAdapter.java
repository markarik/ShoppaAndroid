package com.example.homeactivity.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.homeactivity.R;
import com.example.homeactivity.datamanagers.CartManager;
import com.example.homeactivity.models.CartResponse;

import java.util.List;


public class CartRecyclerAdapter extends RecyclerView.Adapter<CartRecyclerAdapter.ViewHolder> {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<CartResponse> mCartArrayList;
    CartManager cartManager;

    public CartRecyclerAdapter(Context context, List<CartResponse> list) {
        mContext = context;
        cartManager = new CartManager(context);
        mCartArrayList = list;
    }

    @NonNull
    @Override
    public CartRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartResponse cart = mCartArrayList.get(position);
        Toast.makeText(mContext, mCartArrayList.isEmpty() ? "empty" : cart.getName(), Toast.LENGTH_SHORT).show();
        holder.product_name.setText(cart.getName());
        holder.product_price.setText(cart.getUnitCost().toString());
        Glide.with(mContext).load("http://192.168.100.14:8000" + cart.getFeaturedUrl()
        ).placeholder(R.drawable.cart).into(holder.cart_image);
    }

    @Override
    public int getItemCount() {
        return mCartArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView cart_image;
        TextView product_name, product_price;
        Button btn_remove, btn_edit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cart_image = itemView.findViewById(R.id.cart_productImage);
            product_name = itemView.findViewById(R.id.cartProductName);
            product_price = itemView.findViewById(R.id.cartPriceTxt);
            btn_remove = itemView.findViewById(R.id.cartRemoveBtn);
            btn_edit = itemView.findViewById(R.id.cartEditBtn);
        }

        @Override
        public void onClick(View view) {
        }
    }
}
