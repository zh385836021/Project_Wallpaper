package com.test.zh.project_wallpaper.Fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.test.zh.project_wallpaper.Adapter.PullToRefreshGridViewAdapter;
import com.test.zh.project_wallpaper.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RecommendItemFragment extends Fragment {
    private PullToRefreshGridView  gridView;
    private Handler handler=new Handler();
    private PullToRefreshGridViewAdapter adapter;
    private SimpleDateFormat sdf;
    private  Date date;
    private String times;


    public static RecommendItemFragment newInstance(String url) {
        RecommendItemFragment fragment = new RecommendItemFragment();
        Bundle args = new Bundle();
        args.putString("URL", url);
        fragment.setArguments(args);
        return fragment;
    }
        //TODO 设置风格
    public void initGridViewStyle() {
        long mill=System.currentTimeMillis();
        sdf=new SimpleDateFormat("yyyy-MM-dd");
        date=new Date(mill);
        times=sdf.format(date);

        ILoadingLayout il = gridView.getLoadingLayoutProxy();
        il.setLastUpdatedLabel("上次加载时间"+times);
        il.setLoadingDrawable(getResources().getDrawable(R.drawable.umeng_socialize_wxcircle,null));
        il.setPullLabel("下拉刷新");
        il.setRefreshingLabel("努力刷新数据中...");
        il.setReleaseLabel("释放后刷新数据");

    }

    //TODO grideView 下拉刷新事件
    public void initListener() {
        gridView.setMode(PullToRefreshBase.Mode.BOTH);
        gridView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                        gridView.onRefreshComplete();
                    }
                },20000);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                        gridView.onRefreshComplete();
                    }
                },20000);
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.recommend_gridview,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListener();
        initGridViewStyle();

    }
}
