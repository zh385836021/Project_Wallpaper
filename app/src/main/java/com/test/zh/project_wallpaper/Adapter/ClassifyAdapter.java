package com.test.zh.project_wallpaper.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.test.zh.project_wallpaper.Bean.ClassifyBean;
import com.test.zh.project_wallpaper.Constant.IBind;
import com.test.zh.project_wallpaper.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Zane on 2016/5/31.
 */
public class ClassifyAdapter extends BaseAdapter {
    private ArrayList<ClassifyBean.DataBean> list;
    private Context context;
    private LayoutInflater inflater;
    private ImageLoader loader;

    public ClassifyAdapter(Context context, ArrayList<ClassifyBean.DataBean> list, ImageLoader loader) {
        this.context = context;
        this.loader = loader;
        this.list = list;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_classify_listview, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.itemTitle.setText(list.get(position).getPicCategoryName());
        holder.tvContent.setText(list.get(position).getDescWords());

        loader.displayImage(list.get(position).getCategoryPic(), holder.itemIv);
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.item_iv)
        ImageView itemIv;
        @Bind(R.id.item_title)
        TextView itemTitle;
        @Bind(R.id.tv_content)
        TextView tvContent;
        @Bind(R.id.iv_arrow)
        ImageView ivArrow;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
