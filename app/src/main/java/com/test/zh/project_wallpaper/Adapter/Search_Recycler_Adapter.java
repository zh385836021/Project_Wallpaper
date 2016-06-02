package com.test.zh.project_wallpaper.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.zh.project_wallpaper.R;

import java.util.ArrayList;

/**
 * Created by Zane on 2016/6/2.
 */
public class Search_Recycler_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<String>list;
    private Context context;

    public Search_Recycler_Adapter(Context context,ArrayList<String>list) {
        this.context=context;
        this.list=list;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else if (position==1) {
            return 1;
        } else  {
            return  2;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder=null;
        switch (viewType) {
            case 0:
                holder=new Search_Hot(LayoutInflater.from(context).inflate(R.layout.item_search_hot,parent,false));
                break;
            case 1:
                holder=new Search_AD(LayoutInflater.from(context).inflate(R.layout.item_search_ad,parent,false));
                break;
            case 2:
                holder=new Search_Text(LayoutInflater.from(context).inflate(R.layout.item_search_text,parent,false));
                break;

        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    //TODO  热门搜索
    class Search_Hot extends RecyclerView.ViewHolder {

        public Search_Hot(View itemView) {
            super(itemView);
        }
    }

    //TODO  广告
    class Search_AD extends RecyclerView.ViewHolder {

        public Search_AD(View itemView) {
            super(itemView);
        }
    }

    //TODO  文字
    class Search_Text extends RecyclerView.ViewHolder {

        public Search_Text(View itemView) {
            super(itemView);
        }
    }

}
