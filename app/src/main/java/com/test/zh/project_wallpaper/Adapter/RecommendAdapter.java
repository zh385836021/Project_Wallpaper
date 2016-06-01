package com.test.zh.project_wallpaper.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Zane on 2016/5/30.
 */
public class RecommendAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> frg_List;
    private String[] titles;

    public RecommendAdapter(ArrayList<Fragment> frg_List,String[] titles,FragmentManager fm) {
        super(fm);
        this.frg_List=frg_List;
        this.titles=titles;
    }


    @Override
    public Fragment getItem(int position) {
        return frg_List.get(position);
    }

    @Override
    public int getCount() {
        return frg_List==null?0:frg_List.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
