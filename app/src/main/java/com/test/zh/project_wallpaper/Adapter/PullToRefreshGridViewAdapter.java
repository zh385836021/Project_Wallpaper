package com.test.zh.project_wallpaper.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_gridview, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final ProgressBar bar = holder.bar;

        loader.displayImage(iv_List.get(position).getWallPaperSource(), holder.recommIv, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                 bar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                Log.i("prfga","onLoadingComplete");

                bar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

            }
        });

        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.recomm_iv)
        ImageView recommIv;
        @Bind(R.id.progressBar)
        ProgressBar bar;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
