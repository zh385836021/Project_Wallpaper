package com.test.zh.project_wallpaper.Fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.test.zh.project_wallpaper.Adapter.PullToRefreshGridViewAdapter;
import com.test.zh.project_wallpaper.BaseApplication.MyApplication;
import com.test.zh.project_wallpaper.Bean.RecommendBean;
import com.test.zh.project_wallpaper.R;
import com.test.zh.project_wallpaper.request.WallPaperRequest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecommendItemFragment extends Fragment {
    @Bind(R.id.pullto_gridview)
    PullToRefreshGridView pulltoGridview;
    private  int currentPage=1;//当前页数
    private Handler handler = new Handler();
    private ArrayList<RecommendBean.DataBean.WallpaperListInfoBean> data_List;
    private PullToRefreshGridViewAdapter adapter;
    private String url;
    private String totalCount;
    private RequestQueue queue;
    private ImageLoader loader;


    //TODO 设置单例
    public static RecommendItemFragment newInstance(String url) {
        RecommendItemFragment fragment = new RecommendItemFragment();
        Bundle args = new Bundle();
        args.putString("URL", url);
        fragment.setArguments(args);
        return fragment;
    }

    //TODO 设置PullRefreshGridView风格
    public void initGridViewStyle() {
        long mill = System.currentTimeMillis();
        SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(mill);
       String times = sdf.format(date);

        ILoadingLayout il = pulltoGridview.getLoadingLayoutProxy();
        il.setLastUpdatedLabel("上次加载时间" + times);
        il.setLoadingDrawable(getResources().getDrawable(R.drawable.pic_loading));
        il.setPullLabel("下拉刷新");
        il.setRefreshingLabel("努力刷新数据中...");
        il.setReleaseLabel("释放后刷新数据");

    }
    public String getPageSize(int Page) {

        return "http://bz.budejie.com/?typeid=2&ver=3.4.3&no_cry=1&client=android&c=wallPaper&a=wallPaperNew&size=60&bigid=0&index="+Page;
    }

    //TODO grideView 下拉刷新事件
    public void initListener() {
//               loader.setDefaultLoadingListener(new ImageLoadingListener() {
//            @Override
//            public void onLoadingStarted(String imageUri, View view) {
//                AnimationUtils.loadAnimation(getActivity(),R.anim.anim_loading);
//            }
//
//            @Override
//            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
//
//            }
//
//            @Override
//            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
//
//            }
//
//            @Override
//            public void onLoadingCancelled(String imageUri, View view) {
//
//            }
//        });

        pulltoGridview.setMode(PullToRefreshBase.Mode.BOTH);
        pulltoGridview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        currentPage--;
                        if (currentPage == 0) {
                            Toast.makeText(getActivity(), "已经是第一页了", Toast.LENGTH_SHORT).show();
                            pulltoGridview.onRefreshComplete();
                            return;
                        } else {
                            String url=getPageSize(currentPage);
                            initNetDatas(url);
//                            adapter.notifyDataSetChanged();
                            pulltoGridview.onRefreshComplete();
                            Log.i("RecommendItemFragment","onPullDownToRefresh currentPageSize="+currentPage);
                        }
                    }
                }, 2000);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int countSize=Integer.parseInt(totalCount);
                        currentPage++;
                        if (currentPage >= countSize/60) {
                            Toast.makeText(getActivity(), "已经是第最后一页了", Toast.LENGTH_SHORT).show();
                            pulltoGridview.onRefreshComplete();
                        } else {
                            currentPage++;
                            String url=getPageSize(currentPage);
                            initNetDatas(url);
                           // adapter.notifyDataSetChanged();
                            pulltoGridview.onRefreshComplete();
                        }


                        Log.i("RecommendItemFragment","onPullUpToRefresh currentPageSize="+currentPage);
                    }
                }, 2000);
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.recommend_gridview, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        loader=((MyApplication)getActivity().getApplication()).getLoader();
        queue= Volley.newRequestQueue(getActivity());
         url=getArguments().getString("URL");

        initNetDatas(url);
        initListener();
        initGridViewStyle();

    }

    //TODO 请求网络数据
    public void initNetDatas(String urls) {
        data_List=new ArrayList<>();
        WallPaperRequest<RecommendBean> request=new WallPaperRequest<>(RecommendBean.class, urls, new Response.Listener<RecommendBean>() {
            @Override
            public void onResponse(RecommendBean response) {
                List<RecommendBean.DataBean.WallpaperListInfoBean> mList=response.getData().getWallpaperListInfo();
                totalCount=response.getData().getTotalCount();
                Log.i("RecommendItemFragment","mList=="+mList);

                if (mList!=null) {
                    data_List.addAll(mList);
                    setAdapter();
                    adapter.notifyDataSetChanged();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("RecommendItemFragment","response:"+error.getMessage());
                Toast.makeText(getActivity(), "网络错误,请重试！", Toast.LENGTH_SHORT).show();
            }
        });
        //TODO  把请求添加到队列中
        queue.add(request);

    }

    //TODO 设置适配器
    private void setAdapter() {
        adapter=new PullToRefreshGridViewAdapter(getActivity(),data_List,loader);
        pulltoGridview.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
