package com.example.project_1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ViewAdapter extends BaseAdapter {
    private final Context ctx;
    private final int[] list;

    public ViewAdapter(Context ctx, int[] list){
        this.ctx = ctx;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public Object getItem(int position) {
        return list[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView iv = new ImageView(ctx);
        iv.setImageResource(list[position]);
        iv.setLayoutParams(new ViewGroup.LayoutParams(300,300));
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iv.setPadding(5, 5, 5, 5);
        return iv;
    }
}
