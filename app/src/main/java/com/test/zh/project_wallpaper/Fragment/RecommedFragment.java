package com.test.zh.project_wallpaper.Fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.test.zh.project_wallpaper.Adapter.RecommendAdapter;
import com.test.zh.project_wallpaper.BaseApplication.MyApplication;
import com.test.zh.project_wallpaper.Constant.IBind;
import com.test.zh.project_wallpaper.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 推荐
 * Created by Zane on 2016/5/30.
 */
public class RecommedFragment extends Fragment {
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.include_ll)
    RelativeLayout includeLl;
    @Bind(R.id.main_tab)
    TabLayout mainTab;
    @Bind(R.id.viewPager)
    ViewPager viewPager;



    private ArrayList<Fragment> frag_List= new ArrayList<>();
    private String[] titles = {"最新", "热门", "随机"};
    private RecommendAdapter adapter;
    private ImageLoader Loader;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommed, container, false);
        ButterKnife.bind(this, view);
        Loader = ((MyApplication) getActivity().getApplication()).getLoader();
        initTab();
        initImage();
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
        adapter = new RecommendAdapter(frag_List,titles,getFragmentManager());
        viewPager.setAdapter(adapter);
    }

    //TODO 对TabLayout设置标题
    public void initTab() {
        for (int i = 0; i < titles.length; i++) {
            TabLayout.Tab tab = mainTab.newTab().setText(titles[i]).setTag(i);
            mainTab.addTab(tab);
        }
    }

    //TODO 添加图片地址
    public void initImage() {
        frag_List.add(RecommendItemFragment.newInstance(IBind.RECOMMEND_NEW));
        frag_List.add(RecommendItemFragment.newInstance(IBind.RECOMMEND_RANDOM));
        frag_List.add(RecommendItemFragment.newInstance(IBind.CLASSIFY_DESIGN_HOT));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
