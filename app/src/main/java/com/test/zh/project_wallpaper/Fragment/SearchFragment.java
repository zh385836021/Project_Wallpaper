package com.test.zh.project_wallpaper.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.test.zh.project_wallpaper.Adapter.Search_AD_Adapter;
import com.test.zh.project_wallpaper.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 搜索
 * Created by Zane on 2016/5/30.
 */
public class SearchFragment extends Fragment {
    @Bind(R.id.btn_search)
    Button btnSearch;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    private Search_AD_Adapter ad_adapter;
    private ArrayList<ImageView> iv_List;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        initData();
//        initAdapter();
    }

//    private void initAdapter() {
//        ad_adapter = new Search_AD_Adapter(iv_List);
//
//    }
//
//    public void initData() {
//        for (int i = 0; i < 6; i++) {
//            ImageView iv = new ImageView(getActivity());
//            iv.setImageResource(R.drawable.ic_launcher);
//            iv.setScaleType(ImageView.ScaleType.FIT_XY);
//            iv_List.add(iv);
//        }
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
