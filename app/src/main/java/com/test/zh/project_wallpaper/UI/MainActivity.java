package com.test.zh.project_wallpaper.UI;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.test.zh.project_wallpaper.Fragment.ClassifyFragment;
import com.test.zh.project_wallpaper.Fragment.MoreFragment;
import com.test.zh.project_wallpaper.Fragment.RecommedFragment;
import com.test.zh.project_wallpaper.Fragment.SearchFragment;
import com.test.zh.project_wallpaper.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    @Bind(R.id.rb_recommed)
    RadioButton rbRecommed;
    @Bind(R.id.rb_type)
    RadioButton rbType;
    @Bind(R.id.rb_search)
    RadioButton rbSearch;
    @Bind(R.id.rb_more)
    RadioButton rbMore;
    @Bind(R.id.radioGroup)
    RadioGroup radioGroup;
    @Bind(R.id.main_bottom_ll)
    LinearLayout mainBottomLl;


    private FragmentManager manager;

    private ArrayList<Fragment> list = new ArrayList<>();

    private RadioButton rbBtn;

    private long[] mHits = new long[2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        manager = getSupportFragmentManager();

        addFragment();
        initlistener();

        manager.beginTransaction().add(R.id.main_frame,list.get(0)).commit();
    }


    public void initlistener() {
        radioGroup.setOnCheckedChangeListener(this);

    }

    public void addFragment() {
        list.add(new RecommedFragment());
        list.add(new ClassifyFragment());
        list.add(new SearchFragment());
        list.add(new MoreFragment());
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        RadioButton rb = (RadioButton) group.findViewById(checkedId);
        int num = Integer.parseInt(rb.getTag().toString());
        if (!list.get(num).isAdded()) {
            manager.beginTransaction().add(R.id.main_frame, list.get(num)).commit();
        }
        for (int i = 0; i < list.size(); i++) {
            if (i==num) {
                manager.beginTransaction().show(list.get(i)).commit();
            }else{
                manager.beginTransaction().hide(list.get(i)).commit();
            }
        }
    }

    //TODO 双击退出程序
    @Override
    public void onBackPressed() {
        doubleClick();
    }

    private void doubleClick() {
        System.arraycopy(mHits, 1, mHits, 0, mHits.length-1);
        mHits[mHits.length-1] = SystemClock.uptimeMillis();
        if (SystemClock.uptimeMillis() - mHits[0] <= 500) {
            finish();
        } else {
            Toast.makeText(this, "再一次点击退出壁纸精选", Toast.LENGTH_SHORT).show();
        }
    }
}
