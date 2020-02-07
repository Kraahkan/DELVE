package DELVE;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipeDirectionalView;
import com.mindorks.placeholderview.listeners.ItemRemovedListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TinderCard.Callback {

    private SwipeDirectionalView mSwipeView;
    private Context mContext;
    private int mAnimationDuration = 300;
    private boolean isToUndo = false;
    public ProgressBar progBar;
    public TextView storyText;

    int vitalityCount=0;

    GraphicsControl graphicsControl = new GraphicsControl();
    private FrameLayout frameLayout;
    private AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        frameLayout = findViewById(R.id.parentFrameLayout);
        animationDrawable = (AnimationDrawable) frameLayout.getBackground();
        graphicsControl.startAnimation(animationDrawable);


        progBar = findViewById(R.id.progressBar);
        mSwipeView = findViewById(R.id.swipeView);
        mContext = getApplicationContext();
        storyText = findViewById(R.id.storyTextView);
        
        int bottomMargin = Utils.dpToPx(160);
        Point windowSize = Utils.getDisplaySize(getWindowManager());
        mSwipeView.getBuilder()
                // displays stack of cards
                .setDisplayViewCount(1)
                .setIsUndoEnabled(true)
                .setSwipeVerticalThreshold(Utils.dpToPx(50))
                .setSwipeHorizontalThreshold(Utils.dpToPx(50))
                .setHeightSwipeDistFactor(10)
                .setWidthSwipeDistFactor(5)
                .setSwipeDecor(new SwipeDecor()
                        .setViewWidth(windowSize.x-110)
                        .setViewHeight(windowSize.y-250 - bottomMargin)
                        .setViewGravity(Gravity.TOP)
                        .setPaddingTop(20)
                        .setSwipeMaxChangeAngle(10)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.tinder_swipe_in_msg_view)
                        .setSwipeOutMsgLayoutId(R.layout.tinder_swipe_out_msg_view)
                        .setSwipeRotationAngle(7)
                        .setSwipeAnimTime(mAnimationDuration));


        final Point cardViewHolderSize = new Point(windowSize.x, windowSize.y - bottomMargin);


        ArrayList<ArrayList<Instance>> instanceArrayListOuter = Utils.loadInstance(mContext);


        final Instance instance = instanceArrayListOuter.get(0).get(0);
        mSwipeView.addView(new TinderCard(mContext, instance, cardViewHolderSize, this));
        storyText.setText(instance.getStoryText());
        final Instance instance1 = instanceArrayListOuter.get(0).get(1);

        mSwipeView.addView(new TinderCard(mContext, instance1, cardViewHolderSize, this));
        final Instance instance2 = instanceArrayListOuter.get(0).get(2);
        mSwipeView.addView(new TinderCard(mContext, instance2, cardViewHolderSize, this));


        findViewById(R.id.rejectBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(false);
                progBar.setProgress(progBar.getProgress()+10);
            }
        });

        findViewById(R.id.acceptBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mSwipeView.doSwipe(true);


            }
        });




        mSwipeView.addItemRemoveListener(new ItemRemovedListener() {
            @Override
            public void onItemRemoved(int count) {
                vitalityCount++;
                storyText.setText(instance1.getStoryText());
                if(vitalityCount%3==0){
                    progBar.setProgress(progBar.getProgress()+5);
                }
                Instance instance = new Instance();
                mSwipeView.addView(new TinderCard(mContext, instance, cardViewHolderSize, MainActivity.this));

            }
        });


    }

    private void makeSnow() {
        Particles.emitParticles((ViewGroup)findViewById(R.id.parentFrameLayout).getParent());
    }

    @Override
    public void onSwipeUp() {
        isToUndo = true;
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            graphicsControl.hideSystemUI(frameLayout);
        }
    }

    // Shows the system bars by removing all the flags
    // except for the ones that make the content appear under the system bars.
    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }
}
