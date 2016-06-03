package com.test.zh.project_wallpaper.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.test.zh.project_wallpaper.Bean.SearchHotBean;
import com.test.zh.project_wallpaper.R;

import java.util.ArrayList;

/**
 * Created by Zane on 2016/6/2.
 */
public class Search_Recycler_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<SearchHotBean.DataBean> list;
    private ImageLoader loader;
    private Context context;
    private LayoutInflater inflater;
    private static final String SEARCH_RECYCLER_ADAPTER="Search_Recycler_Adapter";

    public Search_Recycler_Adapter(Context context, ArrayList<SearchHotBean.DataBean> list, ImageLoader loader) {
        this.context = context;
        this.list = list;
        this.loader = loader;
        inflater = LayoutInflater.from(context);
        //initData();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }



    }





    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case 0:
                holder = new Search_Hot(inflater.inflate(R.layout.item_search_hot, parent, false));
                break;
//            case 1:
//                holder = new Search_AD(LayoutInflater.from(context).inflate(R.layout.item_search_ad, parent, false));
//                break;
//            case 1:
//                holder = new Search_Text(inflater.inflate(R.layout.item_search_text, parent, false));
//                break;

        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case 0:
                Search_Hot search_hot = (Search_Hot) holder;

                Log.i(SEARCH_RECYCLER_ADAPTER,"tvSearchMachine"+list.get(0).getKeyword());
                search_hot.tvSearchMachine.setText(list.get(0).getKeyword());
                search_hot.tvSearchCartoon.setText(list.get(1).getKeyword());
                search_hot.tvSearchScenery.setText(list.get(2).getKeyword());
                search_hot.tvSearchAnimal.setText(list.get(3).getKeyword());


                //Log.i(SEARCH_RECYCLER_ADAPTER,"displayImage:"+list.get(position).getImgs().get(1));
                loader.displayImage(list.get(position).getImgs().get(0), search_hot.ivSearchMachine);
                loader.displayImage(list.get(position).getImgs().get(1), search_hot.ivSearchCartoon);
                loader.displayImage(list.get(position).getImgs().get(2), search_hot.ivSearchScenery);
                loader.displayImage(list.get(position).getImgs().get(3), search_hot.ivSearchAnimal);
                break;

//            case 1:
//                Search_Text search_text= (Search_Text) holder;
//                loader.displayImage(list.get(position).getImgs().get(0), search_text.Search_Text_iv1);
//                loader.displayImage(list.get(position).getImgs().get(1), search_text.Search_Text_iv2);
//                loader.displayImage(list.get(position).getImgs().get(2), search_text.Search_Text_iv3);
//                break;
        }

    }


    @Override
    public int getItemCount() {
        Log.i(SEARCH_RECYCLER_ADAPTER,"getItemCount==="+list+"---------"+"list.size()=="+list.size());
        return list==null?0:list.size();
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
//    class Search_AD extends RecyclerView.ViewHolder {
//        private ViewPager viewPager;
//        private ImageView dot_1,dot_2;
//        private ImageView dot_3,dot_4;
//        private ImageView dot_5;
//        private TextView tvMore;
//
//        public Search_AD(View itemView) {
//            super(itemView);
//            viewPager= (ViewPager) itemView.findViewById(R.id.search_ad_viewPger);
//            dot_1= (ImageView) itemView.findViewById(R.id.search_dot_1);
//            dot_2= (ImageView) itemView.findViewById(R.id.search_dot_2);
//            dot_3= (ImageView) itemView.findViewById(R.id.search_dot_3);
//            dot_4= (ImageView) itemView.findViewById(R.id.search_dot_4);
//            dot_5= (ImageView) itemView.findViewById(R.id.search_dot_5);
//            tvMore= (TextView) itemView.findViewById(R.id.tv_search_more);
//
//            ad_adapter = new Search_AD_Adapter(iv_List);
//            viewPager.setAdapter(ad_adapter);
//        }
//    }
//
    //TODO  文字
    class Search_Text extends RecyclerView.ViewHolder {
        private ImageView Search_Text_iv1, Search_Text_iv2, Search_Text_iv3;

        public Search_Text(View itemView) {
            super(itemView);
            Search_Text_iv1 = (ImageView) itemView.findViewById(R.id.search_text_iv1);
            Search_Text_iv2 = (ImageView) itemView.findViewById(R.id.search_text_iv2);
            Search_Text_iv3 = (ImageView) itemView.findViewById(R.id.search_text_iv3);

        }
    }

}
