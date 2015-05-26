package com.dashboard.orange.orangedashboard.ParseObjects;

import android.util.Log;

import com.parse.ParseClassName;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Puneet on 09-04-2015.
 */

@ParseClassName("Stores")
public class Store extends ParseObject
{

    public Store()
    {
        Log.d("test", "created store");
    }

    public static ParseQuery<Store> getQuery()
    {
        return ParseQuery.getQuery(Store.class);
    }

    //Store name
    public String getName()
    {
        return getString("name");
    }

    public void setName(String name)
    {
        put("name", name);
    }

    //Store handle
    public String getHandle()
    {
        return getString("store_handle");
    }

    public void setHandle(String handle)
    {
        put("store_handle", handle);
    }

    //Store Description
    public String getDescription()
    {
        return getString("description");
    }

    public void setDescription(String description)
    {
        put("description", description);
    }

    //Store Address
    public String getAddress()
    {
        return getString("address");
    }

    public void setAddress(String address)
    {
        put("address", address);
    }

    //Store Locality
    public String getLocalityName()
    {
        return getLocality().getString("locality_name");
    }

    public Locality getLocality()
    {
        return (Locality) getParseObject("locality");
    }

    public void setLocality(ParseObject locality)
    {
        put("locality", locality);
    }

    //Store Phone
    public String getPhoneNumberAt(int index)
    {
        ArrayList<String> phoneNumbers = (ArrayList<String>) get("phone");
        if (index > phoneNumbers.size())
        {
            Log.e("IMPORTANT", "Index out of bounds!");
            return phoneNumbers.get(phoneNumbers.size());
        }
        return phoneNumbers.get(index);
    }

    public String getPrimaryPhoneNumber()
    {
        return getPhoneNumberAt(0);
    }

    public void setPhoneNumbers(ArrayList<String> numbers)
    {
        put("phone", numbers);
    }

    //Primary Category
    public Category getPrimaryCategory()
    {
        return (Category) get("category");
    }

    public void setPrimaryCategory(Category category)
    {
        put("category", category);
    }

    //Tags related - need to set and get a full array of tags
    public ArrayList<Subcategory> getTags()
    {
        return (ArrayList<Subcategory>) get("tags");
    }

    public void setTags(ArrayList<Subcategory> tags)
    {
        put("tags", tags);
    }

    //Geolocation of store; No setter - can not be changed
    public ParseGeoPoint getGeolocation()
    {
        return getParseGeoPoint("geolocation");
    }



    //Store email
    public String getEmail()
    {
        return getString("email");
    }

    public void setEmail(String email)
    {
        put("email", email);
    }

    //Store website
    public String getWebsiteURL()
    {
        return getString("website_url");
    }

    public void setWebsiteURL(String website)
    {
        put("website_url", website);
    }

    //Store Twitter
    public String getTwitterHandle()
    {
        return getString("twitter_handler");
    }

    public void setTwitterHandle(String twitterHandle)
    {
        put("twitter_handler", twitterHandle);
    }

    //Store Facebook link
    public String getFacebookLink()
    {
        return getString("facebook_link");
    }

    public void setFacebookLink(String facebookLink)
    {
        put("facebook_link", facebookLink);
    }

    //Store Working hours
    public String getWorkingHours()
    {
        //Needs to be edited depending on what it is in the backend
        return getString("startTime") + "to " + getString("endTime") + "on " + get("workingDays");
    }

    public void setWorkingHours(String startTime, String endTime, ArrayList<String> days)
    {
        //Need to be editted depending on the backend names
        put("start_time", startTime);
        put("end_time", endTime);
        put("working_days", days);
    }

    //Get Followers
    public ArrayList<User> getUsers()
    {
        return (ArrayList<User>) get("followers");
    }

    //Store Get Products
    public ArrayList<Product> getProducts()
    {
        return (ArrayList<Product>) get("products");
    }

    //Need to create servicesclass
    public ArrayList<Product> getServices()
    {
        return (ArrayList<Product>) get("services");
    }

    //banner image remaniing
    //Collections is array of Collections

    public boolean getDirtyBit() {return getBoolean("dirty_bit"); }

    //Need to set dirty bit true every time make a change
    public void setDirtyBit(boolean dirtyBit) { put("dirty_bit", dirtyBit); }

    public ArrayList<String> getPaymentType() { return (ArrayList<String>) get("payment_type");}

    public void setPaymentType(ArrayList<String> type) { put("payment_type", type); }


}
