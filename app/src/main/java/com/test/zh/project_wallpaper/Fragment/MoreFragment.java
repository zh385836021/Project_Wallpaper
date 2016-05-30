package com.test.zh.project_wallpaper.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.zh.project_wallpaper.R;

/**
 * 更多
 * Created by Zane on 2016/5/30.
 */
public class MoreFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_more,container,false);
    }
}
