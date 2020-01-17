package com.example.delve;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.ContextCompat;

import com.codemybrainsout.onboarder.AhoyOnboarderActivity;
import com.codemybrainsout.onboarder.AhoyOnboarderCard;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AhoyOnboarderActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AhoyOnboarderCard card1 = new AhoyOnboarderCard("Welcome to the best place on earth", "Here is some text", R.drawable.curved_shape);
        AhoyOnboarderCard card2 = new AhoyOnboarderCard("Glad you could make it", "Look, some more text", R.drawable.curved_shape);
        AhoyOnboarderCard card3 = new AhoyOnboarderCard("By the way, you suck", "Ya dirty dog", R.drawable.curved_shape);

        AhoyOnboarderCard[] introCards = {card1, card2, card3};

        List<AhoyOnboarderCard> pages = new ArrayList<>();

        for (AhoyOnboarderCard card : introCards) {
            card.setBackgroundColor(R.color.colorAccent);
            card.setTitleColor(R.color.white);
            card.setDescriptionColor(R.color.black);
            card.setTitleTextSize(dpToPixels(16, this));
            card.setDescriptionTextSize(dpToPixels(10, this));
            card.setIconLayoutParams(128, 128, 64, 64, 64, 64);

            pages.add(card);
        }


        setOnboardPages(pages);

        List<Integer> colorList = new ArrayList<>();
        colorList.add(R.color.colorPrimary);
        colorList.add(R.color.colorPrimary);
        colorList.add(R.color.colorPrimary);
        setColorBackground(colorList);

        setFinishButtonDrawableStyle(ContextCompat.getDrawable(this, R.drawable.rounded_button));

        YoYo.with(Techniques.FadeIn)
                .duration(1400)
                .playOn(findViewById(R.id.vp_pager));


        //or

      //  setColorBackground(R.color.colorPrimary);

      //  Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
      //  setFont(face);


    }

    @Override
    public void onFinishButtonPressed() {
        Intent myIntent = new Intent(this, TestActivity.class);
        myIntent.putExtra("key", 2); //Optional parameters
        this.startActivity(myIntent);

        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();


    }
}
