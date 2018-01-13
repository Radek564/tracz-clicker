package com.palusiniak.traczclicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by John on 2017-12-23.
 */

public class CustomListAdapter3 extends BaseAdapter {
    private ArrayList<DecorationsItem> listData;
    private LayoutInflater layoutInflater;

    public CustomListAdapter3(Context aContext, ArrayList<DecorationsItem> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public boolean isEnabled(int position) {
        if (ShopDecorations.shopDecBought == 1) {
            if (position == 0)
                return false;
        }
        if (ShopDecorations.shopDec2Bought == 1) {
            if (position == 1)
                return false;
        }
        if (ShopDecorations.shopDec3Bought == 1) {
            if (position == 2)
                return false;
        }
        if (ShopDecorations.shopDec4Bought == 1) {
            if (position == 3)
                return false;
        }
        if (ShopDecorations.shopDec5Bought == 1) {
            if (position == 4)
                return false;
        }
        return true;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_row_layout, null);
            holder = new ViewHolder();
            holder.nameView = (TextView) convertView.findViewById(R.id.name);
            holder.descriptionView = (TextView) convertView.findViewById(R.id.description);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.nameView.setText(listData.get(position).getName());
        holder.descriptionView.setText(listData.get(position).getDescription(position));
        return convertView;
    }

    static class ViewHolder {
        TextView nameView;
        TextView descriptionView;
    }
}