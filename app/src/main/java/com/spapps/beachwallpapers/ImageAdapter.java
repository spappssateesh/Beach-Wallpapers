package com.spapps.beachwallpapers;

import android.view.View;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

    private Context mContext;

    public Integer[] mThumbIds = {
            R.mipmap.beach_1,R.mipmap.beach_2,R.mipmap.beach_3,
            R.mipmap.beach_4,R.mipmap.beach_5,R.mipmap.beach_6,
            R.mipmap.beach_7,R.mipmap.beach_8,R.mipmap.beach_9,
            R.mipmap.beach_10,R.mipmap.beach_11,R.mipmap.beach_12,
            R.mipmap.beach_13,R.mipmap.beach_14,R.mipmap.beach_15,
            R.mipmap.beach_16,R.mipmap.beach_17,R.mipmap.beach_18,
            R.mipmap.beach_19,R.mipmap.beach_20,R.mipmap.beach_21,
            R.mipmap.beach_22,R.mipmap.beach_23,R.mipmap.beach_24,
            R.mipmap.beach_25,R.mipmap.beach_26,R.mipmap.beach_27,
            R.mipmap.beach_28,R.mipmap.beach_29,R.mipmap.beach_30,
            R.mipmap.beach_31,R.mipmap.beach_32,R.mipmap.beach_33,
            R.mipmap.beach_34,R.mipmap.beach_35,R.mipmap.beach_36
    };

    // Constructor
    public ImageAdapter(Context c){
        mContext = c;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mThumbIds[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(150, 150));
        //imageView.setPadding(8, 8, 8, 8);
        return imageView;
    }
}