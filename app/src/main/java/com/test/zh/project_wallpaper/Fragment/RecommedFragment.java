package com.test.zh.project_wallpaper.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test.zh.project_wallpaper.Adapter.ViewPagerAdapter;
import com.test.zh.project_wallpaper.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 推荐
 * Created by Zane on 2016/5/30.
 */
public class RecommedFragment extends Fragment implements TabLayout.OnTabSelectedListener {
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.include_ll)
    RelativeLayout includeLl;
    @Bind(R.id.main_tab)
    TabLayout mainTab;
    @Bind(R.id.viewPager)
    ViewPager viewPager;



    private ArrayList<ImageView> tab_List;
    private String[] titles = {"最新", "热门", "随机"};
    private ViewPagerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommed, container, false);
        ButterKnife.bind(this, view);
        initTab();
        initAdapter();
        return view;
    }

    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainTab.setupWithViewPager(viewPager);
        title.setText("壁纸精选");
    }

    //TODO 设置适配器
    private void initAdapter() {
        tab_List = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ImageView iv=new ImageView(getActivity());
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            iv.setImageResource(R.drawable.ic_launcher);
            tab_List.add(iv);
        }
        adapter = new ViewPagerAdapter(tab_List,titles);
        viewPager.setAdapter(adapter);
    }

    public void initTab() {
        for (int i = 0; i < titles.length; i++) {
            TabLayout.Tab tab = mainTab.newTab().setText(titles[i]).setTag(i);
            mainTab.addTab(tab);
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
