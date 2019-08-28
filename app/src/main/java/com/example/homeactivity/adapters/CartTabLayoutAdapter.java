package com.example.homeactivity.adapters;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.homeactivity.ui.fragments.CartFragment;


public class CartTabLayoutAdapter extends FragmentPagerAdapter {

    public CartTabLayoutAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new CartFragment();
            case 1:
                return new CartFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0:
                return "Cart";
            case 1:
                return "WishList";
            default:
                return null;
        }
    }
}