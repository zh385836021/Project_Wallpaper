package com.test.zh.project_wallpaper.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.test.zh.project_wallpaper.Adapter.Search_Recycler_Adapter;
import com.test.zh.project_wallpaper.BaseApplication.MyApplication;
import com.test.zh.project_wallpaper.Bean.RecommendBean;
import com.test.zh.project_wallpaper.Bean.SearchHotBean;
import com.test.zh.project_wallpaper.Constant.IBind;
import com.test.zh.project_wallpaper.R;
import com.test.zh.project_wallpaper.request.WallPaperRequest;

import java.util.ArrayList;
import java.util.List;

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

    private Search_Recycler_Adapter search_Adapter;
    private ArrayList<SearchHotBean.DataBean>iv_List;
    private RequestQueue queue;
    private ImageLoader loader;
    private static final String TAG="SearchFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loader=((MyApplication)getActivity().getApplication()).getLoader();
        queue= Volley.newRequestQueue(getActivity());
        initData();
        //initRecyclerAdapter();
    }

    public void initData() {
        iv_List=new ArrayList<>();
        WallPaperRequest<SearchHotBean> request=new WallPaperRequest<>(SearchHotBean.class, IBind.SEARCH_HOT, new Response.Listener<SearchHotBean>() {
            @Override
            public void onResponse(SearchHotBean response) {
                List<SearchHotBean.DataBean> mList=response.getData();
                Log.i(TAG,"SearchHotBean mList=="+mList);

                if (mList!=null) {
                    iv_List.addAll(mList);
                    initRecyclerAdapter();
                    search_Adapter.notifyDataSetChanged();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG,"response:"+error.getMessage());
                Toast.makeText(getActivity(), "网络错误,请重试！", Toast.LENGTH_SHORT).show();
            }
        });
        //TODO  把请求添加到队列中
        queue.add(request);

    }
    public void initRecyclerAdapter() {
        Log.i(TAG,"initRecyclerAdapter iv_List=="+iv_List);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        search_Adapter=new Search_Recycler_Adapter(getActivity(),iv_List,loader);
        recyclerView.setAdapter(search_Adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
