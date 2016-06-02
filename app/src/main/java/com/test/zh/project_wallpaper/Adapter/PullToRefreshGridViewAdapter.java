package com.test.zh.project_wallpaper.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.test.zh.project_wallpaper.Bean.RecommendBean;
import com.test.zh.project_wallpaper.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Zane on 2016/6/1.
 */
public class PullToRefreshGridViewAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<RecommendBean.DataBean.WallpaperListInfoBean> iv_List;
    private ImageLoader loader;
    private Context context;


    public PullToRefreshGridViewAdapter(Context context, ArrayList<RecommendBean.DataBean.WallpaperListInfoBean> iv_List, ImageLoader loader) {
        this.iv_List = iv_List;
        this.loader = loader;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return iv_List == null ? 0 : iv_List.size();
    }

    @Override
    public Object getItem(int position) {
        return iv_List.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_gridview, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        loader.displayImage(iv_List.get(position).getWallPaperMiddle(), holder.recommIv);

        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.recomm_iv)
        ImageView recommIv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
