package com.example.homeactivity.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homeactivity.models.Product;

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
    private Box<Testproduct> mTestproductBox;
    private List<Testproduct> allproducts;
    public MyRecyclerViewAdapter(@NonNull Context context, ArrayList<Product> productArrayList) {
        mContext = context;
        mProductArrayList = productArrayList;
​
    }
​
    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_products,parent,false);
        return new ViewHolder(view);
    }
​
    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.ViewHolder holder, int position)
    {
​
        Product product = mProductArrayList.get(position);
        holder.product_price.setText(product.getUnitCost());
        holder.striked_product_price.setText(product.getUnitCost());
        Glide.with(mContext).load("http://192.168.100.9:8000"+product.getFeaturedImageUrl())
                .placeholder(R.drawable.ic_add_shopping_cart).into(holder.productImage);
        Toast.makeText(mContext, product.getFeaturedImageUrl(), Toast.LENGTH_LONG).show();
        holder.cart_icon.setImageResource(product.getCart_icon());
        holder.wishlist_icon.setImageResource(product.getWishlist());
        holder.mCurrentPosition = position;
    }
​
    @Override
    public int getItemCount()
    {
        return mProductArrayList.size();
    }
​
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView productImage, cart_icon, wishlist_icon;
        TextView product_price;
        TextView product_name;
        TextView striked_product_price;
        public int mCurrentPosition;
​
        ​
        public ViewHolder(View itemView)
        {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image_recy);
            product_price = itemView.findViewById(R.id.product_price_recy);
​
            striked_product_price = itemView.findViewById(R.id.striked_product_price);
            cart_icon = itemView.findViewById(R.id.cart_icon);
            wishlist_icon = itemView.findViewById(R.id.wish_list_icon);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    Log.d("SHOPPY:",""+mProductArrayList.get(mCurrentPosition).getDescription());
//                    Intent intent = new Intent(mContext,ViewProductDetails.class);
//                    intent.putExtra(CURRENT_POSITION_VALUE,mProductArrayList.get(mCurrentPosition));
//                    mContext.startActivity(intent);
//                }
//            });
            cart_icon.setOnClickListener(this);
​
        }
​
        @Override
        public void onClick(View view) {
            int itemId=view.getId();
//        if(itemId==R.id.cart_icon){
//            Intent intent = new Intent(mContext,CartActivity.class);
//            intent.putExtra(CURRENT_POSITION_VALUE,mProductArrayList.get(mCurrentPosition));
//            Toast.makeText(mContext, "vvvv", Toast.LENGTH_SHORT).show();
//            mContext.startActivity(new Intent(mContext,CartActivity.class));
//           // selectQuantity();
//        }
​
        }
​
//        private void selectQuantity() {
//            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
//
//            // Inflate the custom layout/view
//            View customView = inflater.inflate(R.layout.item_quantity_pop_up_layout,null);
//
//
//            // Initialize a new instance of popup window
//           mPopupWindow = new PopupWindow(
//                    customView,
//                    ViewGroup.LayoutParams.WRAP_CONTENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT
//            );
//
//            // Set an elevation value for popup window
//            // Call requires API level 21
//            if(Build.VERSION.SDK_INT>=21){
//                mPopupWindow.setElevation(5.0f);
//            }
//
//            Button closePopUpButton = (Button) customView.findViewById(R.id.close_pop_up);
//
//            // Set a click listener for the popup window close button
//            closePopUpButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    // Dismiss the popup window
//                    mPopupWindow.dismiss();
//                }
//            });
//
//
//            mPopupWindow.showAtLocation(mRelativeLayout, Gravity.CENTER,0,0);
//        }
    }
}
