package com.test.zh.project_wallpaper.customView;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.test.zh.project_wallpaper.Adapter.Search_AD_Adapter;
import com.test.zh.project_wallpaper.Bean.SearchMoreBean;
import com.test.zh.project_wallpaper.R;

import java.util.ArrayList;

/**
 * Created by Zane on 2016/6/3.
 */
public class MySearchAdView extends LinearLayout {
    private Context context;
    private ViewPager viewPager;
    private TextView tv;
    private RadioGroup rg;
    private Handler handler = new Handler();
    private Runnable r;
    private boolean flag;
    private ArrayList<SearchMoreBean.DataBean.TopicBean> more_List = new ArrayList<>();

    //TODO 用于控制ViewPager中显示的数据源
    private ArrayList<View> pagerList = new ArrayList<>();
    private Search_AD_Adapter ad_adapter;


    public MySearchAdView(Context context) {
        super(context);
        init(context);
    }

    public MySearchAdView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    //TODO 初始化布局和方法
    private void init(Context context) {
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.my_search_ad, this, true);

        initView(view);
        setAdapter();
        initListener();
        startRunnable();

    }


    //TODO 初始化控件
    private void initView(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.search_ad_viewPger);
        tv = (TextView) view.findViewById(R.id.tv_search_more);
        rg = (RadioGroup) view.findViewById(R.id.rg_search_dot);
    }

    //TODO 设置设配器
    private void setAdapter() {
        ad_adapter = new Search_AD_Adapter(context);
        viewPager.setAdapter(ad_adapter);


    }

    //TODO 初始化并添加RadioButton
    private void initRadioBtn() {
        for (int i = 0; i < more_List.size() / 2; i++) {
            RadioButton rb = new RadioButton(context);
            rb.setId(i);
            //设置radioButton的被选择的效果
            rb.setButtonDrawable(R.drawable.selec_search_ad);
            //把radioButton添加到RadioGroup
            rg.addView(rb);
        }
        rg.check(0);
    }


    public void initListener() {
        //TODO 图片圆点的切换事件
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rbs = (RadioButton) group.findViewById(checkedId);
                int num = rbs.getId();
                viewPager.setCurrentItem(num % 5);


            }
        });

        //TODO 页面左右滑动监听事件
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                rg.check(position % 5);

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        flag = true;
                        handler.removeCallbacks(r);

                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        if (flag) {
                            flag = false;
                            handler.postDelayed(r, 2000);
                        }
                        break;
                }

            }
        });

    }

    //TODO 开启线程执行自动切换
    public void startRunnable() {
        r = new Runnable() {
            @Override
            public void run() {
                int position = viewPager.getCurrentItem() + 1;
                if (pagerList != null && position == pagerList.size()) {
                    position = 0;
                }
                viewPager.setCurrentItem(position);
                handler.postDelayed(this, 2000);

            }
        };
        handler.postDelayed(r, 2000);

    }

    //TODO 更新ViewPager
    public void upDataViewPager(ArrayList<SearchMoreBean.DataBean.TopicBean> more_List, ImageLoader loader) {

        this.more_List = more_List;
            initRadioBtn();

        for (int i = 0; i < more_List.size() / 2; i++) {
            View view = LayoutInflater.from(context).inflate(R.layout.search_viewpager_item, null);
            ImageView search_iv_pager1 = (ImageView) view.findViewById(R.id.search_iv_pager1);
            ImageView search_iv_pager2 = (ImageView) view.findViewById(R.id.search_iv_pager2);

            //TODO 存储iv上要显示的图片的网址
            search_iv_pager1.setTag(more_List.get(i*2).getFocus_picture_path());
            search_iv_pager2.setTag(more_List.get(i*2+1).getFocus_picture_path());
            pagerList.add(view);
        }
        ad_adapter.setPageList(pagerList,loader);
        ad_adapter.notifyDataSetChanged();


    }


}
