package com.jpushkarskaya.tasker;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.vstechlab.easyfonts.EasyFonts;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DISPLAY_LENGTH = 6000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        TextView tvSplash = (TextView) findViewById(R.id.tvSplash);
        TextView tvCreator = (TextView) findViewById(R.id.tvCreator);

        try {
            tvSplash.setTypeface(EasyFonts.captureIt(this));
            tvCreator.setTypeface(EasyFonts.greenAvocado(this));
        } catch (NullPointerException ex){
            // ruh roh. no cool font for you
        }

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainDisplayActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

    }
}
