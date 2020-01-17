package com.example.delve;


import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;


import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import android.widget.Adapter;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.github.jinatonic.confetti.CommonConfetti;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.irozon.sneaker.Sneaker;
import com.shashank.sony.fancygifdialoglib.FancyGifDialog;
import com.shashank.sony.fancygifdialoglib.FancyGifDialogListener;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        ViewGroup mainViewGroup = findViewById(R.id.container);
      //  navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        navView.setVisibility(View.INVISIBLE);

       styleStuff();

        /* Sneaker.with(this) // Activity, Fragment or ViewGroup
                .setTitle("Success!!")
                .setMessage("This is the success message")
                .sneakSuccess();*/


        // Make success feel great
     //   FancyToast.makeText(this,"Hello World !",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
       // CommonConfetti.rainingConfetti((ViewGroup)mTextMessage.getParent(), new int[] { Color.BLUE })
      //  CommonConfetti.explosion(mainViewGroup,500,500,new int[] { Color.BLACK });

        if (1 == 0)
            new FancyGifDialog.Builder(this)
                    .setTitle("Granny eating chocolate dialog box")
                    .setMessage("This is a granny eating chocolate dialog box. This library is used to help you easily create fancy gify dialog.")
                    .setPositiveBtnText("Ok")
                    .setPositiveBtnBackground("#FF4081")
                    .setGifResource(R.drawable.gif1)   //Pass your Gif here
                    .isCancellable(true)
                    .OnPositiveClicked(new FancyGifDialogListener() {
                        @Override
                        public void OnClick() {
                            Toast.makeText(MainActivity.this,"Ok",Toast.LENGTH_SHORT).show();
                        }
                    })
                    .build();


        // https://github.com/yuyakaido/CardStackView
        // https://github.com/flschweiger/SwipeStack
        // https://github.com/UFreedom/FloatingView
        // https://github.com/XunMengWinter/CircularAnim
        // https://github.com/JoaquimLey/faboptions
        // https://github.com/codemybrainsout/ahoy-onboarding
        // https://github.com/skyfe79/AndroidGradientImageView
        // https://github.com/Kraahkan?after=Y3Vyc29yOnYyOpK5MjAxNi0xMC0xN1QxNjoxOToyNS0wNzowMM4EMmfr&tab=stars
        // https://github.com/LiveTyping/CannyViewAnimator
        // https://github.com/iammert/ScalingLayout
        // https://github.com/PHELAT/Fun
        // https://github.com/igalata/Bubble-Picker
        // https://github.com/jinatonic/confetti
        // https://github.com/aurelhubert/ahbottomnavigation
        // https://github.com/ncapdevi/FragNav
        //
        // https://github.com/saket/InboxRecyclerView


    }

    private void setupCards() {



     /*   CardStackView cardStackView = findViewById(R.id.card_stack_view);
        CardStackLayoutManager cardStackLayoutManager = new CardStackLayoutManager();
                cardStackView.setLayoutManager(); = CardStackLayoutManager()
        cardStackView.setAdapter(Adapter adapter); = CardStackAdapter();
        CardStack*/
    }

    private void styleStuff() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));

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
