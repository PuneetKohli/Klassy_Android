package com.dashboard.orange.orangedashboard.ParseObjects;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

import java.util.ArrayList;

/**
 * Created by Puneet on 12-04-2015.
 */
@ParseClassName("Products")
public class Product extends ParseObject
{



    public String getName()
    {
        return getString("name");
    }

    public void setName(String name)
    {
        put("name", name);
    }

    public Double getCostPrice()
    {
        return getDouble("cprice");
    }

    public void setCostPrice(Double cprice)
    {
        put("cprice", cprice);
    }

    public Double getSalePrice()
    {
        return getDouble("sprice");
    }

    public void setSalePrice(Double sprice)
    {
        put("sprice", sprice);
    }

    public String getDescription()
    {
        return getString("description");
    }

    public void setDescription(String description)
    {
        put("description", description);
    }

    public Boolean isOnSale()
    {
        return getBoolean("isSale");
    }

    public void setSale(Boolean sale)
    {
        put("isSale", sale);
    }

    public Boolean isVisible()
    {
        return getBoolean("is_visible");
    }

    public void setVisibility(Boolean visibility)
    {
        put ("is_visible", visibility);
    }

    public Store getStoreOfProduct()
    {
        return (Store) getParseObject("store_id");
    }

    public ParseFile getImage()
    {
        return getParseFile("image");
    }

    public void setImage()
    {
        //Save this for later, have to write a full query here
    }

    public ArrayList<ParseObject> getGalleryImages()
    {
        return (ArrayList<ParseObject>) get("gallery_ids");
    }

    public ArrayList<ParseObject> getCollections()
    {
        return (ArrayList<ParseObject>) get("collections");
    }

    public boolean getChanged() { return getBoolean("changed"); }

    //Set changed true when edit a product
    public void setChanged(boolean changed) { put("changed", changed); }
}
