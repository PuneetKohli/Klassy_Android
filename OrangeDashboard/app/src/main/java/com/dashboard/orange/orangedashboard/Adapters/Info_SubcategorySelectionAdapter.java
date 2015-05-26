package com.dashboard.orange.orangedashboard.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.dashboard.orange.orangedashboard.ParseObjects.Subcategory;
import com.dashboard.orange.orangedashboard.R;

import java.util.ArrayList;
import java.util.Locale;

public class Info_SubcategorySelectionAdapter extends BaseAdapter
{

    Context mContext;
    LayoutInflater inflater;
    private ArrayList<Subcategory> mainDataList = null;
    private ArrayList<Subcategory> arraylist;

    public Info_SubcategorySelectionAdapter(Context context, ArrayList<Subcategory> mainDataList)
    {

        mContext = context;
        this.mainDataList = mainDataList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<>();
        this.arraylist.addAll(mainDataList);
    }

    @Override
    public int getCount()
    {
        return mainDataList.size();
    }

    @Override
    public Subcategory getItem(int position)
    {
        return mainDataList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent)
    {
        final ViewHolder holder;
        if (view == null)
        {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.info_subcategory_list_row_item, null);

            holder.name = (TextView) view.findViewById(R.id.contactname);

            holder.check = (CheckBox) view.findViewById(R.id.contactcheck);


            view.setTag(holder);
            view.setTag(R.id.contactname, holder.name);
            view.setTag(R.id.contactcheck, holder.check);

            holder.check
                    .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
                    {

                        @Override
                        public void onCheckedChanged(CompoundButton vw,
                                                     boolean isChecked)
                        {

                            int getPosition = (Integer) vw.getTag();
                            mainDataList.get(getPosition).setSelected(
                                    vw.isChecked());

                        }
                    });

        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }

        holder.check.setTag(position);

        holder.name.setText(mainDataList.get(position).getTagName());
        holder.check.setChecked(mainDataList.get(position).isSelected());

        return view;
    }

    public void filter(String charText)
    {
        charText = charText.toLowerCase(Locale.getDefault());
        mainDataList.clear();
        if (charText.length() == 0)
        {
            mainDataList.addAll(arraylist);
        }
        else
        {
            for (Subcategory wp : arraylist)
            {
                if (wp.getTagName().toLowerCase(Locale.getDefault())
                        .contains(charText))
                {
                    mainDataList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

    static class ViewHolder
    {
        protected CheckBox check;
        protected TextView name;
    }

}