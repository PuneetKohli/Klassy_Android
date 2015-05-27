package com.dashboard.orange.orangedashboard;

import android.app.Application;
import android.util.Log;

import com.dashboard.orange.orangedashboard.ParseObjects.Category;
import com.dashboard.orange.orangedashboard.ParseObjects.Coupon;
import com.dashboard.orange.orangedashboard.ParseObjects.CouponCategory;
import com.dashboard.orange.orangedashboard.ParseObjects.Subcategory;
import com.dashboard.orange.orangedashboard.ParseObjects.Locality;
import com.dashboard.orange.orangedashboard.ParseObjects.Product;
import com.dashboard.orange.orangedashboard.ParseObjects.Store;
import com.dashboard.orange.orangedashboard.ParseObjects.User;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Puneet on 05-04-2015.
 */
public class AppManager extends Application
{

    public Store store;
    public ArrayList<Locality> localities;
    public ArrayList<Category> categories;
    public ArrayList<CouponCategory> couponCategories;
    public ArrayList<Subcategory> subcategories;
    public ArrayList<Coupon> coupons;

    @Override
    public void onCreate()
    {
        // Enable Local Datastore.
        //Parse.enableLocalDatastore(this);
        ParseUser.registerSubclass(User.class);
        ParseObject.registerSubclass(Store.class);
        ParseObject.registerSubclass(Product.class);
        ParseObject.registerSubclass(Category.class);
        ParseObject.registerSubclass(Subcategory.class);
        ParseObject.registerSubclass(Locality.class);
        ParseObject.registerSubclass(Coupon.class);
        ParseObject.registerSubclass(CouponCategory.class);
        Parse.initialize(this, "ZaHk6NzbXLus8EmO0DetfsigR3I2zi4O9D8u5iIG", "UykVZpwQn2tGW9zCndOh9FLdYkBuVhmsKS3lfKFz");

        localities = new ArrayList<>();
        categories = new ArrayList<>();
        subcategories  = new ArrayList<>();
        coupons = new ArrayList<>();
        couponCategories = new ArrayList<>();
        store = new Store();


        fetchLocalityList();
        fetchCategoryList();
        fetchCouponCategoryList();
        fetchSubcategoryList(null);
        fetchStoreByID("XP1wlBhs4p");
        super.onCreate();
    }

    public ArrayList<Locality> getLocalityList()
    {
        if(localities.isEmpty())
        {
            fetchLocalityList();
        }
        return localities;
    }

    public ArrayList<Category> getCategoryList()
    {
        if(categories.isEmpty())
        {
            fetchCategoryList();
        }
        return categories;
    }

    public ArrayList<CouponCategory> getCouponCategoryList()
    {
        if(couponCategories.isEmpty())
        {
            fetchCouponCategoryList();
        }
        return couponCategories;
    }

    public ArrayList<Subcategory> getSubcategoryList()
    {
        if(subcategories.isEmpty())
        {
            fetchSubcategoryList(null);
        }
        return subcategories;
    }

    public Store getStoreByID()
    {
        if(store == null)
        {
            fetchStoreByID("XP1wlBhs4p");
        }
        return store;
    }

    public void fetchLocalityList()
    {
        ParseQuery<Locality> query = Locality.getQuery();
        query.findInBackground(new FindCallback<Locality>()
        {
            public void done(List<Locality> localityList, ParseException e)
            {
                if (e == null)
                {
                    localities.addAll(localityList);
                    Log.d("Locality", "Got all Localities");
                }
                else
                {
                    Log.d("Locality", "Error: " + e.getMessage());
                }
            }
        });
    }

    public void fetchCategoryList()
    {
        ParseQuery<Category> query = Category.getQuery();
        query.findInBackground(new FindCallback<Category>()
        {
            public void done(List<Category> categoryList, ParseException e)
            {
                if (e == null)
                {
                    categories.addAll(categoryList);
                    Log.d("Category", "Got all Categories");
                }
                else
                {
                    Log.d("Locality", "Error: " + e.getMessage());
                }
            }
        });
    }

    public void fetchCouponCategoryList()
    {
        ParseQuery<CouponCategory> query = CouponCategory.getQuery();
        query.findInBackground(new FindCallback<CouponCategory>()
        {
            public void done(List<CouponCategory> categoryList, ParseException e)
            {
                if (e == null)
                {
                    couponCategories.addAll(categoryList);
                    Log.d("Category", "Got all Coupon Categories w size is " + couponCategories.size());
                }
                else
                {
                    Log.d("Locality", "Error: " + e.getMessage());
                }
            }
        });
    }

    public void fetchSubcategoryList(Category category)
    {
        ParseQuery<Subcategory> query = Subcategory.getQuery();
        if(category != null)
        {
            query.whereEqualTo("tag_category", category);
        }
        query.findInBackground(new FindCallback<Subcategory>()
        {
            public void done(List<Subcategory> tagsList, ParseException e)
            {
                if (e == null)
                {
                    subcategories.addAll(tagsList);
                    Log.d("Subcat", "Got all Subcategories");
                    Log.d("Subcat", "Size is : " + subcategories.size());
                }
                else
                {
                    Log.d("Locality", "Error: " + e.getMessage());
                }
            }
        });
    }

    public void fetchStoreByID(String ID)
    {
        ParseQuery<Store> query = Store.getQuery();
        query.whereEqualTo("objectId", ID);
        query.getFirstInBackground(new GetCallback<Store>()
        {
            public void done(Store storeReturned, ParseException e)
            {
                if (e == null)
                {
                    store = storeReturned;
                    Log.d("Store", "Got the store with handle is " + store.getHandle());
                    Log.d("Store", "Got the store with name is " + storeReturned.getName());
                    fetchCouponsForStore(store);
                }
                else
                {
                    Log.d("Store", "Error: " + e.getMessage());
                }
            }
        });
    }

    public void fetchCouponsForStore(Store store)
    {
        ParseQuery<Coupon> query = Coupon.getQuery();
        query.whereEqualTo("store_id", store);
        query.include("coupon_category");
        query.findInBackground(new FindCallback<Coupon>()
        {
            public void done(List<Coupon> couponsList, ParseException e)
            {
                if (e == null)
                {
                    coupons.addAll(couponsList);
                    Log.d("Coupons", "Got all Coupons");
                    Log.d("Coupons", "Size is : " + couponsList.size());
                }
                else
                {
                    Log.d("Locality", "Error: " + e.getMessage());
                }
            }
        });
    }

    public ArrayList<Coupon> getCoupons()
    {
        if(coupons.isEmpty())
        {
            fetchCouponsForStore(store);
        }
        return coupons;
    }


}
