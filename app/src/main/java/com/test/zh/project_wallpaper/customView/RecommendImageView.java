package com.test.zh.project_wallpaper.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ImageView;

/**
 * Created by Zane on 2016/6/1.
 */
public class RecommendImageView extends ImageView {


    public RecommendImageView(Context context) {
        super(context);
    }

    public RecommendImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        DisplayMetrics metrics=getResources().getDisplayMetrics();
        int height=metrics.heightPixels;
        heightMeasureSpec=height/3;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }
}
