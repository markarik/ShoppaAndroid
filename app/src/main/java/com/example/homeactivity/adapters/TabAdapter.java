package com.example.homeactivity.adapters;

public class TabAdapter extends FragmentPagerAdapter {
    private List<Category> categories = new ArrayList<>();
    private Context mContext;
    public TabAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm);
        mContext=context;
        TabAdapter.this.notifyDataSetChanged();
        getCategoryList();
    }
​
        ​
    @NonNull
    @Override
    public Fragment getItem(int position) {
        for(int index = 1; index<categories.size()+1; index++){
            String fragmentname = categories.get(position).getName();
            Toast.makeText(mContext, "frag: "+fragmentname,Toast.LENGTH_LONG).show();
            Log.d("getItem",categories.get(index).getName());
            return DynamicFragment.newInstance(position,fragmentname);
        }
        return null;
    }
​
    @Override
    public int getCount() {
        return categories.size();
    }
​
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        Log.d("adapttt","getpagetitle");
        Log.d("getTitile",categories.get(position).getName());
        return categories.get(position).getName();
    }
​
    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
​
    public void getCategoryList(){
​
        Call<List<Category>> call = RetrofitClient.getInstance(mContext)
                .getApiConnector()
                .getCategories();
​
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.code() == 200)
                {
                    categories.clear();
                    categories.addAll(response.body());
                    Toast.makeText(mContext, categories.get(1).getName(), Toast.LENGTH_SHORT).show();
                    TabAdapter.this.notifyDataSetChanged();
                }
                else
                {
                    Toast.makeText(mContext, response.code(), Toast.LENGTH_SHORT).show();
                }
            }
​
            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(mContext, "this is an actual network failure :( " +
                            "inform the user and possibly retry", Toast.LENGTH_LONG).show();
                    // logging probably not necessary
                }
                Toast.makeText(mContext, "Error: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
​
​
    }
​
}
