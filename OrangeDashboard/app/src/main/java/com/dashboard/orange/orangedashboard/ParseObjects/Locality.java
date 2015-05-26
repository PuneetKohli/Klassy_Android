package com.dashboard.orange.orangedashboard.ParseObjects;

import com.parse.ParseClassName;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by Puneet on 13-04-2015.
 */

@ParseClassName("Locality")
public class Locality extends ParseObject
{
    public Locality(){}

    //No setters - we do not want to modify the locality

    public static ParseQuery<Locality> getQuery()
    {
        return ParseQuery.getQuery(Locality.class);
    }

    public String getLocalityName()
    {
        return getString("locality_name");
    }

    public String getCity()
    {
        return getString("city");
    }

    public String getState()
    {
        return getString("state");
    }

    public String getCountry()
    {
        return getString("country");
    }

    public ParseGeoPoint getLocation()
    {
        return getParseGeoPoint("location");
    }

}
