package com.test.zh.project_wallpaper.Adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Zane on 2016/6/2.
 */
public class Search_AD_Adapter extends PagerAdapter {
    private ArrayList<ImageView>ad_List;

    public Search_AD_Adapter(ArrayList<ImageView>ad_List) {
        this.ad_List=ad_List;
    }

    @Override
    public int getCount() {
        return ad_List==null?0:ad_List.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(ad_List.get(position));
        return ad_List.get(position);
    }
}
