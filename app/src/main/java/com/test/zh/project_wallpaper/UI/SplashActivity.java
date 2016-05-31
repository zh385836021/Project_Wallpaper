package com.test.zh.project_wallpaper.UI;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.test.zh.project_wallpaper.R;

public class SplashActivity extends AppCompatActivity {
    private Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        lanuchMainActivity();
    }

    public  void lanuchMainActivity() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
            }
        },2000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
