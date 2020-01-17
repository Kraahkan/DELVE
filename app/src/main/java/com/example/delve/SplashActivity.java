package com.example.delve;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Handler;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TextView logoTextView = findViewById(R.id.logoTextView);
        YoYo.with(Techniques.FadeIn)
                .duration(2000)
                .playOn(logoTextView);

        styleStuff();

        final Intent i = new Intent(this, IntroActivity.class);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                startActivity(i);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();

            }
        }, 2900);


    }

    private void styleStuff() {
       // ActionBar actionBar = getSupportActionBar();
       // actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
      //  actionBar.hide();

        Window window = this.getWindow();

        // Don't need for this app

        if(Build.VERSION.SDK_INT >= 21.0){

            // clear FLAG_TRANSLUCENT_STATUS flag:
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            //this code will be executed on devices running on DONUT (NOT ICS) or later
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }
    }
}
