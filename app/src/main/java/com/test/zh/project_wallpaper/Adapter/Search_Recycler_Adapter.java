package com.test.zh.project_wallpaper.Adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.test.zh.project_wallpaper.Bean.SearchHotBean;
import com.test.zh.project_wallpaper.Bean.SearchListBean;
import com.test.zh.project_wallpaper.Bean.SearchMoreBean;
import com.test.zh.project_wallpaper.R;
import com.test.zh.project_wallpaper.customView.MySearchAdView;

import java.util.ArrayList;

/**
 * Created by Zane on 2016/6/2.
 */
public class Search_Recycler_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<SearchHotBean.DataBean> list;
    private ArrayList<SearchListBean.DataBean> beanList;
    private ArrayList<SearchMoreBean.DataBean.TopicBean> moreList;
    private ImageLoader loader;
    private Context context;
    private LayoutInflater inflater;
    private static final String SEARCH_RECYCLER_ADAPTER = "Search_Recycler_Adapter";
    private static final int SEARCH_HOT=0;
    private static final int SEARCH_MORE=1;
    private static final int SEARCH_LIST=2;

    public Search_Recycler_Adapter(Context context, ImageLoader loader) {
        this.context = context;
        this.loader = loader;
        inflater = LayoutInflater.from(context);
    }
    public void updateHot (ArrayList<SearchHotBean.DataBean> hotList) {

        this.list = hotList;
    }

    public void updateList(ArrayList<SearchListBean.DataBean> beanList) {
        this.beanList = beanList;
    }

    public void updateMore(ArrayList<SearchMoreBean.DataBean.TopicBean> moreList) {
        this.moreList = moreList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case SEARCH_HOT:
                holder = new Search_Hot(inflater.inflate(R.layout.item_search_hot, parent, false));
                break;
            case SEARCH_MORE:
                holder = new Search_AD(LayoutInflater.from(context).inflate(R.layout.item_search_ad, parent, false));
                break;
            case SEARCH_LIST:
                holder = new Search_Text(inflater.inflate(R.layout.item_search_text, parent, false));
                break;

        }
        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == SEARCH_HOT) {
            return SEARCH_HOT;
        } else if (position == SEARCH_MORE) {
            return SEARCH_MORE;
        }
        return SEARCH_LIST;


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case SEARCH_HOT:
                Search_Hot search_hot = (Search_Hot) holder;

                if (list != null) {
                    Log.i(SEARCH_RECYCLER_ADAPTER, "displayImage:" + list.get(position).getImgs().size());
                    loader.displayImage(list.get(0).getImgs().get(0), search_hot.ivSearchMachine);
                    loader.displayImage(list.get(1).getImgs().get(0), search_hot.ivSearchCartoon);
                    loader.displayImage(list.get(2).getImgs().get(0), search_hot.ivSearchScenery);
                    loader.displayImage(list.get(3).getImgs().get(0), search_hot.ivSearchAnimal);

                    //Log.i(SEARCH_RECYCLER_ADAPTER,"tvSearchMachine"+list.get(0).getKeyword());
                    search_hot.tvSearchMachine.setText(list.get(0).getKeyword());
                    search_hot.tvSearchCartoon.setText(list.get(1).getKeyword());
                    search_hot.tvSearchScenery.setText(list.get(2).getKeyword());
                    search_hot.tvSearchAnimal.setText(list.get(3).getKeyword());
                }

                break;

            case SEARCH_MORE:
                Search_AD search_ad= (Search_AD) holder;
                if (moreList != null) {

                    search_ad.adView.upDataViewPager(moreList,loader);
                }
                break;

            case SEARCH_LIST:
                Search_Text search_text = (Search_Text) holder;
                if (beanList != null) {

                    search_text.search_title_tv.setText(beanList.get(position-2).getKeyword());

                    loader.displayImage(beanList.get(position-2).getImgs().get(0),search_text.Search_Text_iv1);
                    loader.displayImage(beanList.get(position-2).getImgs().get(1),search_text.Search_Text_iv2);
                    loader.displayImage(beanList.get(position-2).getImgs().get(2),search_text.Search_Text_iv3);
                }
                break;
        }

    }


    @Override
    public int getItemCount() {
        //Log.i(SEARCH_RECYCLER_ADAPTER, "list===" + list + "---------" + "list.size()==" + list.size());
         //return 6;
        int count = beanList == null? 0: beanList.size()+2;
        return count;
    }

    //TODO  热门搜索
    class Search_Hot extends RecyclerView.ViewHolder {
        private TextView tvSearchMachine, tvSearchCartoon;
        private TextView tvSearchAnimal, tvSearchScenery;
        private ImageView ivSearchMachine, ivSearchCartoon;
        private ImageView ivSearchAnimal, ivSearchScenery;

        public Search_Hot(View itemView) {
            super(itemView);
            tvSearchMachine = (TextView) itemView.findViewById(R.id.tv_search_machine);
            tvSearchCartoon = (TextView) itemView.findViewById(R.id.tv_search_cartoon);
            tvSearchScenery = (TextView) itemView.findViewById(R.id.tv_search_scenery);
            tvSearchAnimal = (TextView) itemView.findViewById(R.id.tv_search_animal);

            ivSearchMachine = (ImageView) itemView.findViewById(R.id.iv_search_machine);
            ivSearchCartoon = (ImageView) itemView.findViewById(R.id.iv_search_cartoon);
            ivSearchScenery = (ImageView) itemView.findViewById(R.id.iv_search_scenery);
            ivSearchAnimal = (ImageView) itemView.findViewById(R.id.iv_search_animal);
        }
    }

    //TODO  广告
    class Search_AD extends RecyclerView.ViewHolder {
        MySearchAdView adView;
        public Search_AD(View itemView) {
            super(itemView);
            adView = (MySearchAdView) itemView.findViewById(R.id.my_search_ad);
        }
    }

    //TODO 类型
    class Search_Text extends RecyclerView.ViewHolder {
        private ImageView Search_Text_iv1, Search_Text_iv2, Search_Text_iv3;
        private TextView search_title_tv;

        public Search_Text(View itemView) {
            super(itemView);
            Search_Text_iv1 = (ImageView) itemView.findViewById(R.id.search_text_iv1);
            Search_Text_iv2 = (ImageView) itemView.findViewById(R.id.search_text_iv2);
            Search_Text_iv3 = (ImageView) itemView.findViewById(R.id.search_text_iv3);
            search_title_tv= (TextView) itemView.findViewById(R.id.search_title_tv);

        }
    }

}
