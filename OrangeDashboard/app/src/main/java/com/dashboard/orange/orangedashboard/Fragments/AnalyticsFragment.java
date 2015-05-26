package com.dashboard.orange.orangedashboard.Fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dashboard.orange.orangedashboard.CustomLayout.SlidingTabLayout;
import com.dashboard.orange.orangedashboard.R;

/**
 * Created by Puneet on 15-04-2015.
 */
public class AnalyticsFragment extends Fragment
{

    /**
     * A custom {@link ViewPager} title strip which looks much like Tabs present in Android v4.0 and
     * above, but is designed to give continuous feedback to the user when scrolling.
     */
    private SlidingTabLayout mSlidingTabLayout;

    /**
     * A {@link ViewPager} which will be used in conjunction with the {@link SlidingTabLayout} above.
     */
    private ViewPager mViewPager;
    private Toolbar toolbar;

    public static AnalyticsFragment newInstance()
    {
        return new AnalyticsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        return inflater.inflate(R.layout.fragment_analytics, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {

        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle("Analytics");

        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mViewPager.setAdapter(new AnalyticsPagerAdapter());

        mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setViewPager(mViewPager);

        super.onViewCreated(view, savedInstanceState);


    }


/*    public void searchTest()
    {
        HashMap<String, Object> params = new HashMap<String, Object>();
        JSONArray mArr = new JSONArray();
        mArr.put("stores");
        mArr.put("running");
        params.put("meta", mArr);
        Log.d("Lematize", "Called lematize");
        ParseCloud.callFunctionInBackground("lemmatize", params, new FunctionCallback<ArrayList<String>>()
        {
            public void done(ArrayList<String> result, ParseException e)
            {
                if (e == null)
                {
                    Log.d("Lematize", "Result is  : " + result);
                    // result is "Hello world!"
                }
                else
                {
                    Log.d("Lematize", "Lematize error : " + e);
                }
            }
        });
    }


    public void getStoreDataByStoreID(String storeID)
    {
        ParseQuery<Store> query = Store.getQuery();
        query.whereEqualTo("objectId", storeID);
        //query.include("products");
        query.include("products.collections");
        query.include("followers");
        query.getFirstInBackground(new GetCallback<Store>()
        {
            public void done(Store mStore, ParseException e)
            {
                if (mStore == null)
                {
                    Log.d("Store", "The getFirst request failed.");
                }
                else
                {
                    Log.d("Store", "Query store: " + mStore);
                    manager.store = mStore;

                    Log.d("Store", "Name: " + manager.store.getName());
                    Log.d("Store", "Addr: " + manager.store.getAddress());
                    Log.d("Store", "Email: " + manager.store.getEmail());
                    //Log.d("Store", "Products list  " + store.getProducts().get(0).getName() + " and collection is  " + store.getProducts().get(0).getCollections().get(0).get("collection_name"));
                    Log.d("store", "Product Image is : " + manager.store.getProducts().get(0).getImage());
                    Log.d("Store", "Retrieved the store" + mStore);
                    Log.d("Store", "The user following this store is " + manager.store.getUsers().get(0).getUsersRealName());
                    imageView.setParseFile(manager.store.getProducts().get(0).getImage());
                    imageView.loadInBackground(new GetDataCallback()
                    {
                        @Override
                        public void done(byte[] data, ParseException e)
                        {
                            Log.d("ParseImageView", "Fetched!");
                        }
                    });
                }
            }
        });
        Log.d("Test", "Should have worked");
    }*/

    class AnalyticsPagerAdapter extends PagerAdapter
    {

        /**
         * @return the number of pages to display
         */
        @Override
        public int getCount()
        {
            return 2;
        }

        /**
         * Instantiate the {@link View} which should be displayed at {@code position}. Here we
         * inflate a layout from the apps resources and then change the text view to signify the position.
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position)
        {
            // Inflate a new layout from our resources
            if (position == 0)
            {
                View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_analytics_data,
                        container, false);
                // Add the newly created View to the ViewPager
                container.addView(view);

                return view;

            }
            // Retrieve a TextView from the inflated View, and update it's text
           /* TextView title = (TextView) view.findViewById(R.id.item_title);
            title.setText(String.valueOf(position + 1));

            Log.i(LOG_TAG, "instantiateItem() [position: " + position + "]");*/

            // Return the View
            else
            {
                return null;
            }
        }

        /**
         * Destroy the item from the {@link ViewPager}. In our case this is simply removing the
         * {@link View}.
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object)
        {
            container.removeView((View) object);
            Log.i("Analytics", "destroyItem() [position: " + position + "]");
        }

        /**
         * @return true if the value returned from {@link #instantiateItem(ViewGroup, int)} is the
         * same object as the {@link View} added to the {@link ViewPager}.
         */
        @Override
        public boolean isViewFromObject(View view, Object o)
        {
            return o == view;
        }

        /**
         * Return the title of the item at {@code position}. This is important as what this method
         * returns is what is displayed in the {@link SlidingTabLayout}.
         * <p/>
         * Here we construct one using the position value, but for real application the title should
         * refer to the item's contents.
         */
        @Override
        public CharSequence getPageTitle(int position)
        {
            if (position == 0)
            {
                return "All Time";
            }
            else
            {
                return "Today";
            }
        }

    }
}
