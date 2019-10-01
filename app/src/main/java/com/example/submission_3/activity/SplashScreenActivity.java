package com.example.submission_3.activity;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.submission_3.R;
import com.example.submission_3.activity.MainActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private int loading_time=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent home = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(home);
                finish();
            }
        },loading_time);
    }
}
