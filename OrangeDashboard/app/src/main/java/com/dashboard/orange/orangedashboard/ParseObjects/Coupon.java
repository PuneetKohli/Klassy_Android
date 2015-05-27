package com.dashboard.orange.orangedashboard.ParseObjects;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Date;

/**
 * Created by Puneet on 26-05-2015.
 */

@ParseClassName("Coupons")
public class Coupon extends ParseObject
{

    public static ParseQuery<Coupon> getQuery()
    {
        return ParseQuery.getQuery(Coupon.class);
    }

    public boolean getIsActive()
    {
        return getBoolean("active");
    }

    public void setIsActive(boolean isActive)
    {
        put("active", isActive);
    }

    public int getRedeemLimit()
    {
        return getInt("claim_limit");
    }

    public void setRedeemLimit(int limit)
    {
        put("claim_limit", limit);
    }

    public String getCouponName()
    {
        return getString("coupon_name");
    }

    public void setCouponName(String couponName)
    {
        put("coupon_name", couponName);
    }

    public String getTerms()
    {
        return getString("terms");
    }

    public void setTerms(String terms)
    {
        put("terms", "terms");
    }

    public Date getStartDate()
    {
        return getDate("start_date");
    }

    public void setStartDate(Date startDate)
    {
        put("start_date", startDate);
    }

    public Store getStoreID()
    {
        return (Store) get("store_id");
    }

    public CouponCategory getCouponCategory()
    {
        return (CouponCategory) get("coupon_category");
    }


}
