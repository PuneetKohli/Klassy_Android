package com.dashboard.orange.orangedashboard.ParseObjects;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by Puneet on 13-04-2015.
 */

@ParseClassName("CouponCat")
public class CouponCategory extends ParseObject
{
    public CouponCategory(){}

    //Category name
    public String getCategoryName()
    {
        return getString("categoryName");
    }

    public String getImageLink()
    {
        return getString("image_link");
    }

    public static ParseQuery<CouponCategory> getQuery()
    {
        return ParseQuery.getQuery(CouponCategory.class);
    }

}
