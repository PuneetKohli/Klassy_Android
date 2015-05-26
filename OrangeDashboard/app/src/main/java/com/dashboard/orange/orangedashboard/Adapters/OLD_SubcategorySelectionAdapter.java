package com.dashboard.orange.orangedashboard.Adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;

import com.dashboard.orange.orangedashboard.ParseObjects.Subcategory;
import com.dashboard.orange.orangedashboard.R;

import java.util.ArrayList;

public class OLD_SubcategorySelectionAdapter extends ArrayAdapter
{

    private Context context;
    private boolean useList = true;
    private ArrayList<Subcategory> mItems;


    public OLD_SubcategorySelectionAdapter(Context context, ArrayList<Subcategory> items)
    {
        super(context, android.R.layout.simple_list_item_multiple_choice, items);
        mItems = items;
        this.context = context;
    }

    @Override
    public int getCount()
    {
        return mItems.size();
    }

    @Override
    public Subcategory getItem(int position)
    {
        return (Subcategory) mItems.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    /**
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder = null;
        Subcategory item = (Subcategory) getItem(position);

        // This block exists to inflate the settings list item conditionally based on whether
        // we want to support a grid or list view.
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
        {
            if (useList)
            {
                convertView = mInflater.inflate(R.layout.example_list_item, null);
            }
            else
            {
                //viewToUse = mInflater.inflate(R.layout.example_grid_item, null);
            }

            holder = new ViewHolder();
            holder.titleText = (CheckedTextView) convertView.findViewById(R.id.checkbox);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
            convertView.setTag(holder);

        }

        holder.titleText.setText(item.getTagName());

        //holder.titleText.(mCheckedChangeListener);

        return convertView;
    }

    /**
     * Holder for the list items.
     */
    private class ViewHolder
    {
        CheckedTextView titleText;
    }
}
