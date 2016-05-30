package com.test.zh.project_wallpaper;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.test.zh.project_wallpaper.Fragment.ClassifyFragment;
import com.test.zh.project_wallpaper.Fragment.MoreFragment;
import com.test.zh.project_wallpaper.Fragment.RecommedFragment;
import com.test.zh.project_wallpaper.Fragment.SearchFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener,TabLayout.OnTabSelectedListener {

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.main_tab)
    TabLayout mainTab;
    @Bind(R.id.main_frame)
    FrameLayout mainFrame;
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

    private ArrayList<Fragment> list=new ArrayList<>();
    private ArrayList<String> tab_List;
    private String[] titles={"最新","热门","随机"};
    private FragmentManager manager;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        manager=getSupportFragmentManager();
        title.setText("壁纸精选");
        addFragment();
        initlistener();
        initFragment();


    }
    public void initlistener() {
        radioGroup.setOnCheckedChangeListener(this);
        mainTab.setOnTabSelectedListener(this);

    }
    
    public  void initFragment() {
        tab_List=new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            TabLayout.Tab tab = mainTab.newTab().setText(titles[i]).setTag(i);
            mainTab.addTab(tab);
        }
       // manager.beginTransaction().add(R.id.main_frame,list.get(0)).commit();
    }

    public  void addFragment() {
        list.add(new RecommedFragment());
        list.add(new ClassifyFragment());
        list.add(new SearchFragment());
        list.add(new MoreFragment());
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        RadioButton rb= (RadioButton) group.findViewById(checkedId);
        int num= Integer.parseInt(rb.getTag().toString());
        manager.beginTransaction().replace(R.id.main_frame, list.get(num)).commit();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int position = Integer.parseInt(tab.getTag().toString());
        Fragment fg=list.get(position);
        if (!fg.isAdded()) {
            manager.beginTransaction().add(R.id.main_frame, fg).commit();
        } else {
            manager.beginTransaction().show(fg).commit();
        }
        manager.beginTransaction().hide(fg).commit();
        fragment=fg;
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    //TODO 双击退出程序

    @Override
    public void onBackPressed() {

    }
}
