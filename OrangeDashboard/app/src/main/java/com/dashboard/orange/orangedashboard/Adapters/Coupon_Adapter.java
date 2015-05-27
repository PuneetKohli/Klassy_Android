package com.dashboard.orange.orangedashboard.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dashboard.orange.orangedashboard.ParseObjects.Coupon;
import com.dashboard.orange.orangedashboard.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Puneet on 28-05-2015.
 */
public class Coupon_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    Context context;
    ArrayList<Coupon> coupons;
    View view = null;

    public Coupon_Adapter(Context context, ArrayList<Coupon> coupons)
    {
        this.context = context;
        this.coupons = coupons;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        view = LayoutInflater.from(context).inflate(R.layout.coupon_item, parent, false);
        return new VHCouponItem(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
         Coupon dataItem = coupons.get(position);
        ((VHCouponItem) holder).mCouponCategory.setText(dataItem.getCouponCategory().getCategoryName());
        ((VHCouponItem) holder).mCouponTitle.setText(dataItem.getCouponName());
        ((VHCouponItem) holder).mDaysLeft.setText("blank");
        ((VHCouponItem) holder).mRedeems.setText(""+dataItem.getRedeemLimit());
        ((VHCouponItem) holder).mClaims.setText(""+dataItem.getRedeemLimit());
    }

    @Override
    public int getItemCount()
    {
        return coupons.size();
    }

    public void addItem(Coupon newCoupon)
    {
        coupons.add(newCoupon);
    }


    class VHCouponItem extends RecyclerView.ViewHolder
    {
        TextView mCouponCategory;
        TextView mCouponTitle;
        TextView mDaysLeft;
        TextView mRedeems;
        TextView mClaims;

        public VHCouponItem(View itemView)
        {
            super(itemView);
            mCouponCategory = (TextView) itemView.findViewById(R.id.textView_coupon_category);
            mCouponTitle = (TextView) itemView.findViewById(R.id.textView_coupon_title);
            mDaysLeft = (TextView) itemView.findViewById(R.id.textView_days_left);
            mRedeems = (TextView) itemView.findViewById(R.id.textView_redeems);
            mClaims = (TextView) itemView.findViewById(R.id.textView_claims);
        }
    }
}
