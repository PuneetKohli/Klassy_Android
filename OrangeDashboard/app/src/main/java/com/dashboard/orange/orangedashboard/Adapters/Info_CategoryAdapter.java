package com.dashboard.orange.orangedashboard.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dashboard.orange.orangedashboard.ParseObjects.Category;
import com.dashboard.orange.orangedashboard.R;

import java.util.ArrayList;

public class Info_CategoryAdapter extends BaseAdapter
{
    private Context mContext;
    private ArrayList<Category> mItems;

    public Info_CategoryAdapter(Context context, ArrayList<Category> items)
    {
        mContext = context;
        mItems = items;
    }

    @Override
    public int getCount()
    {
        return mItems.size();
    }

    @Override
    public Object getItem(int position)
    {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder viewHolder;

        if (convertView == null)
        {
            // inflate the GridView item layout
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.category_grid_item, parent, false);

            // initialize the view holder
            viewHolder = new ViewHolder();
            viewHolder.ivIcon = (ImageView) convertView.findViewById(R.id.ivIcon);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            convertView.setTag(viewHolder);
        }
        else
        {
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // update the item view
        Category item = mItems.get(position);
        if(item.getImageLink().contains("access"))
        {
            viewHolder.ivIcon.setImageResource(R.drawable.category_accessories);
        }
        else if (item.getImageLink().contains("beauty"))
        {
            viewHolder.ivIcon.setImageResource(R.drawable.category_beautyncosmetics);
        }
        else if (item.getImageLink().contains("foot"))
        {
            viewHolder.ivIcon.setImageResource(R.drawable.category_footwear);
        }
        else if (item.getImageLink().contains("cloth"))
        {
            viewHolder.ivIcon.setImageResource(R.drawable.category_clothing);
        }
        else if (item.getImageLink().contains("home"))
        {
            viewHolder.ivIcon.setImageResource(R.drawable.category_homendecor);
        }
        else if (item.getImageLink().contains("sport"))
        {
            viewHolder.ivIcon.setImageResource(R.drawable.category_sportsnfitness);
        }


        viewHolder.tvTitle.setText(item.getCategoryName());

        return convertView;
    }

    /**
     * The view holder design pattern prevents using findViewById()
     * repeatedly in the getView() method of the adapter.
     *
     */
    private static class ViewHolder
    {
        ImageView ivIcon;
        TextView tvTitle;
    }
}