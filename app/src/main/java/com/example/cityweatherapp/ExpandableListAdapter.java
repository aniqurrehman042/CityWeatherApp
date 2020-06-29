package com.example.cityweatherapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    @Override
    public int getGroupCount() {
        return 7;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, final boolean isExpanded, View convertView, ViewGroup parent) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_this_week_header, parent, false);
                if (isExpanded)
                    ((ImageView) view.findViewById(R.id.iv_arrow)).setImageResource(R.drawable.ic_arrow_up_black);
                else
                    ((ImageView) view.findViewById(R.id.iv_arrow)).setImageResource(R.drawable.ic_down_arrow);

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_this_week_child, parent, false);
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
