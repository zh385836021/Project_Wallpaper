package com.test.zh.project_wallpaper.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.zh.project_wallpaper.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 推荐
 * Created by Zane on 2016/5/30.
 */
public class RecommedFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommed, container, false);
        return view;
    }

}
