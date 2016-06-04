package com.test.zh.project_wallpaper.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.test.zh.project_wallpaper.Adapter.ClassifyAdapter;
import com.test.zh.project_wallpaper.BaseApplication.MyApplication;
import com.test.zh.project_wallpaper.Bean.ClassifyBean;
import com.test.zh.project_wallpaper.Constant.IBind;
import com.test.zh.project_wallpaper.R;
import com.test.zh.project_wallpaper.request.WallPaperRequest;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 分类
 * Created by Zane on 2016/5/30.
 */
public class ClassifyFragment extends Fragment {
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.listView)
    ListView listView;

    private ImageLoader Loader;
    private ClassifyAdapter adapter;
    private RequestQueue queue;
    private ArrayList<ClassifyBean.DataBean> list;
    private static final String TAG="ClassifyFragment";

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_classify, container, false);
        ButterKnife.bind(this, view);
        title.setText("分类");
        queue = Volley.newRequestQueue(getActivity());
        Loader = ((MyApplication) getActivity().getApplication()).getLoader();
        initData();
        initAdapter();
        return view;
    }



    //TODO Volley下载数据
    public void initData() {
        list=new ArrayList<>();
        WallPaperRequest<ClassifyBean> request = new WallPaperRequest<>(ClassifyBean.class, IBind.CLASSIFY_MAIN, new Response.Listener<ClassifyBean>() {
            @Override
            public void onResponse(ClassifyBean response) {
                List<ClassifyBean.DataBean> dataList=response.getData();
                Log.i(TAG,"Response: "+response.getData());
                list.addAll(dataList);
                Log.i(TAG,"list=="+list);
                initAdapter();
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG,"onErrorResponse: "+error.getMessage());
                Toast.makeText(getActivity(), "网络错误,请重试!", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }

    private void initAdapter() {
        adapter=new ClassifyAdapter(getActivity(),list,Loader);
        listView.setOnScrollListener(new PauseOnScrollListener(Loader, false, false));
        listView.setDividerHeight(0);
        listView.setAdapter(adapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
