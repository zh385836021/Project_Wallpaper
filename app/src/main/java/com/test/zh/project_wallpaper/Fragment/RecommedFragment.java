package com.test.zh.project_wallpaper.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.zh.project_wallpaper.R;

/**
 * 推荐
 * Created by Zane on 2016/5/30.
 */
public class RecommedFragment extends Fragment {
    private TextView tv_title;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_recommed,container,false);
        return view;
    }
}
