package com.test.zh.project_wallpaper.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.test.zh.project_wallpaper.R;

import java.util.ArrayList;

/**
 * Created by Zane on 2016/6/2.
 */
public class Search_AD_Adapter extends PagerAdapter {
    private Context context;
    private ArrayList<View> ad_List;
    private LayoutInflater inflater;
    private ImageLoader loader;


    public Search_AD_Adapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return ad_List==null?0:ad_List.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view  = ad_List.get(position%ad_List.size());

        Log.i("","===== instantiateItem!  "+"  "+ad_List.size()+" "+ position%ad_List.size());
        container.addView(view);

        ImageView iv1 = (ImageView) view.findViewById(R.id.search_iv_pager1);
        ImageView iv2 = (ImageView) view.findViewById(R.id.search_iv_pager1);

        loader.displayImage(iv1.getTag().toString(),iv1);
        loader.displayImage(iv2.getTag().toString(),iv2);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(ad_List.get(position%ad_List.size()));
    }

    public void setPageList(ArrayList<View> ad_List, ImageLoader loader) {
        this.ad_List=ad_List;
        this.loader=loader;
    }
}
