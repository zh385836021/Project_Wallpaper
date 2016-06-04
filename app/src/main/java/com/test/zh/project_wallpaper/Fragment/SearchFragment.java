package com.test.zh.project_wallpaper.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.PaintDrawable;
import android.graphics.drawable.PictureDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.test.zh.project_wallpaper.Adapter.Search_Recycler_Adapter;
import com.test.zh.project_wallpaper.BaseApplication.MyApplication;
import com.test.zh.project_wallpaper.Bean.SearchHotBean;
import com.test.zh.project_wallpaper.Bean.SearchListBean;
import com.test.zh.project_wallpaper.Bean.SearchMoreBean;
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
public class SearchFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.btn_search)
    Button btnSearch;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    private Search_Recycler_Adapter search_Adapter;
    private RequestQueue queue;
    private ImageLoader loader;
    private static final String TAG = "SearchFragment";
    private TextView search_cancle;
    private EditText search_Et;
    private ImageView search_iv;
    private PopupWindow popupWindow;
    private ArrayList<SearchListBean.DataBean> beanList=new ArrayList<>();
    private ArrayList<SearchHotBean.DataBean> hotList=new ArrayList<>();
    private ArrayList<SearchMoreBean.DataBean.TopicBean> moreList=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public void initListenter() {
        btnSearch.setOnClickListener(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loader = ((MyApplication) getActivity().getApplication()).getLoader();
        queue = Volley.newRequestQueue(getActivity());
        initData();
        initRecyclerAdapter();
        initListenter();

    }

    //TODO 使用Volley请求 文字、娱乐等 网络数据
    private void getListData() {
        WallPaperRequest<SearchListBean> request = new WallPaperRequest<>(
                SearchListBean.class, IBind.SEARCH_LIST, new Response.Listener<SearchListBean>() {
            @Override
            public void onResponse(SearchListBean response) {
                if (response != null && response.getData() != null) {
                    Log.i("!!!!!!!!","beanList  response:"+beanList+"-----------"+response.getData());
                    beanList.addAll(response.getData());
                    search_Adapter.updateList(beanList);
                    search_Adapter.notifyDataSetChanged();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        queue.add(request);
    }

    //TODO 使用Volley请求 查看更多 网络数据
    private void getMoreData() {
        WallPaperRequest<SearchMoreBean> request = new WallPaperRequest<>(
                SearchMoreBean.class, IBind.SEARCH_MORE, new Response.Listener<SearchMoreBean>() {
            @Override
            public void onResponse(SearchMoreBean response) {
                if (response != null && response.getData().getTopic() != null) {

                    moreList.addAll(response.getData().getTopic());
                    search_Adapter.updateMore(moreList);
                    search_Adapter.notifyDataSetChanged();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        queue.add(request);
    }

    //TODO 使用Volley请求 热门搜索 网络数据
    private void getHotData() {
        WallPaperRequest<SearchHotBean> request = new WallPaperRequest<>(
                SearchHotBean.class, IBind.SEARCH_HOT, new Response.Listener<SearchHotBean>() {
            @Override
            public void onResponse(SearchHotBean response) {
                if (response != null && response.getData() != null) {

                    hotList.addAll(response.getData());
                    search_Adapter.updateHot(hotList);
                    search_Adapter.notifyDataSetChanged();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        queue.add(request);
    }


    public void initData() {
        getListData();
        getHotData();
        getMoreData();
    }


//    //TODO 加载适配器
    public void initRecyclerAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        search_Adapter = new Search_Recycler_Adapter(getActivity(), loader);
        recyclerView.setAdapter(search_Adapter);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void initDialog() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.search_my_dialog, null);
        popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT,false);
        //设置可以获取焦点，否则弹出菜单中的EditText是无法获取输入的
        popupWindow.setFocusable(true);
        //这句是为了防止弹出菜单获取焦点之后，点击activity的其他组件没有响应
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //防止虚拟软键盘被弹出菜单遮住
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        //在底部显示
        popupWindow.showAsDropDown(view, Gravity.BOTTOM, 0, 0);


        search_Et = (EditText) view.findViewById(R.id.search_edit);
        search_iv = (ImageView) view.findViewById(R.id.search_ed_iv);
        search_cancle = (TextView) view.findViewById(R.id.search_cancle);

        search_Et.addTextChangedListener(textWacher);
        search_cancle.setOnClickListener(this);
        search_iv.setOnClickListener(this);

        popupWindow.showAsDropDown(view, 0, 0);
    }

    //TODO 文本监听事件
    public TextWatcher textWacher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //TODO 判断改变文本长度是否显示取消图标
            if (!TextUtils.isEmpty(s)) {
                search_iv.setVisibility(View.VISIBLE);
            } else {
                search_iv.setVisibility(View.GONE);
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_search:
                initDialog();
                break;
            case R.id.search_cancle:
                popupWindow.dismiss();
                break;
            case R.id.search_ed_iv:
                search_Et.setText(" ");
                break;
        }
    }
}
