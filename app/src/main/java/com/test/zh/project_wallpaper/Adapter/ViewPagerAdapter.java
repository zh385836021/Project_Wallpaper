package com.test.zh.project_wallpaper.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Zane on 2016/5/30.
 */
public class ViewPagerAdapter extends PagerAdapter {
    private ArrayList<ImageView> iv_List;
    private String[] titles;

    public ViewPagerAdapter(ArrayList<ImageView> iv_List,String[] titles) {
        this.iv_List = iv_List;
        this.titles=titles;
    }

    @Override
    public int getCount() {
        return iv_List==null?0:iv_List.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //container.addView(iv_List.get(position).getView());
        container.addView(iv_List.get(position));
        return iv_List.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView((View) object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
