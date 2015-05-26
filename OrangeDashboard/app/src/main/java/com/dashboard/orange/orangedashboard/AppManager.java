package com.dashboard.orange.orangedashboard;

import android.app.Application;
import android.util.Log;

import com.dashboard.orange.orangedashboard.ParseObjects.Category;
import com.dashboard.orange.orangedashboard.ParseObjects.Subcategory;
import com.dashboard.orange.orangedashboard.ParseObjects.Locality;
import com.dashboard.orange.orangedashboard.ParseObjects.Product;
import com.dashboard.orange.orangedashboard.ParseObjects.Store;
import com.dashboard.orange.orangedashboard.ParseObjects.User;
import com.parse.FindCallback;
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
    public ArrayList<Subcategory> subcategories;

    @Override
    public void onCreate()
    {
        // Enable Local Datastore.
        //Parse.enableLocalDatastore(this);
        ParseObject.registerSubclass(Store.class);
        ParseObject.registerSubclass(Product.class);
        ParseObject.registerSubclass(Category.class);
        ParseObject.registerSubclass(Subcategory.class);
        ParseObject.registerSubclass(Locality.class);
        ParseUser.registerSubclass(User.class);
        Parse.initialize(this, "ZaHk6NzbXLus8EmO0DetfsigR3I2zi4O9D8u5iIG", "UykVZpwQn2tGW9zCndOh9FLdYkBuVhmsKS3lfKFz");

        localities = new ArrayList<>();
        categories = new ArrayList<>();
        subcategories  = new ArrayList<>();
        store = new Store();

        fetchLocalityList();
        fetchCategoryList();
        fetchSubcategoryList(null);
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

    public ArrayList<Subcategory> getSubcategoryList()
    {
        if(subcategories.isEmpty())
        {
            fetchSubcategoryList(null);
        }
        return subcategories;
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
}
