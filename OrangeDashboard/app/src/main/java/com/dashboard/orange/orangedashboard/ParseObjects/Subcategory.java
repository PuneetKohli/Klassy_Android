package com.dashboard.orange.orangedashboard.ParseObjects;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by Puneet on 13-04-2015.
 */
@ParseClassName("Tags")
public class Subcategory extends ParseObject
{

    private boolean selected;
    public Subcategory()
    {}

    public String getTagName()
    {
        return getString("tag_description");
    }

    public Category getTagCategory()
    {
        return (Category) getParseObject("tag_category");
    }

    public String getTagImageFilePath()
    {
        return getString("tag_image");
    }

    public static ParseQuery<Subcategory> getQuery()
    {
        return ParseQuery.getQuery(Subcategory.class);
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
