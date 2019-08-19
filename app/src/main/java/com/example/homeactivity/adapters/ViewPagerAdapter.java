package com.example.homeactivity.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.homeactivity.R;



public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    public static String[] images;
// ={R.drawable.pager1, R.drawable.pager2, R.drawable.pager4}

    public ViewPagerAdapter(Context context) {
        this.context = context;


    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.content_product_details, null);

        ImageView imageView = view.findViewById(R.id.detail_product_image);
//        imageView.setImageResource(images[position]);
        Glide.with(context).load(images[position]).placeholder(R.drawable.cart)
                .into(imageView);
        ViewPager viewPager = (ViewPager)container;
        viewPager.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        ViewPager viewPager = (ViewPager)container;
        View view =(View)object;
        viewPager.removeView(view);

    }
}
