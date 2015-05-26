package com.dashboard.orange.orangedashboard.ParseObjects;

import com.parse.ParseClassName;
import com.parse.ParseUser;

import java.util.ArrayList;

/**
 * Created by Puneet on 13-04-2015.
 */

@ParseClassName("_User")
public class User extends ParseUser
{
    public User()
    {}

    //No setters - as we can not set username details from the dashboard app
    public String getUsername()
    {
        return getString("username");
    }

    public String getUsersRealName()
    {
        return getString("name");
    }

    public boolean isAdmin() { return getBoolean("is_admin"); }

    //Admin can not add, must be done by data entry
    public ArrayList<Store> getStoresList()
    {
        return (ArrayList<Store>) get("store_ids");
    }

    public String getPhone() { return getString("phone"); }

    //Need to do parselogin
}
