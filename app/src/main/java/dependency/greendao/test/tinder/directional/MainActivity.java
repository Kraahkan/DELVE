package dependency.greendao.test.tinder.directional;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipeDirectionalView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TinderCard.Callback {

    private SwipeDirectionalView mSwipeView;
    private Context mContext;
    private int mAnimationDuration = 300;
    private boolean isToUndo = false;
    public ProgressBar progBar;
    public TextView storyText;
    String currentID;
    Instance instance;
    ArrayList<ArrayList<Instance>> instanceArrayList;
    ArrayList<String> inverntoryArray;
    int vitailityCount, start;
    SharedPreferences.Editor editor;

    Context context;

    GraphicsControl graphicsControl = new GraphicsControl();

    @Override
    public void onBackPressed() {
        moveTaskToBack(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        Intent intent = getIntent();
        start = intent.getIntExtra("key", 0);


        progBar = findViewById(R.id.progressBar);
        mSwipeView = findViewById(R.id.swipeView);
        mContext = getApplicationContext();
        storyText = findViewById(R.id.storyTextView);
        instanceArrayList = Utils.loadInstance(mContext);
        FrameLayout parentLayout = findViewById(R.id.parentLayout);

        SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);


        if (start==1) {


            currentID = sharedPref.getString("ID", "2.2");
            int prog =  sharedPref.getInt("Prg", 10);
            int first = Integer.parseInt(currentID.substring(0, 1));
            int second = Integer.parseInt(currentID.substring(2, 3));
            instance = instanceArrayList.get(first - 1).get(second - 1);
            setCardView(instance);
            progBar.setProgress(prog);

        }
        else
        {
            instance = instanceArrayList.get(3).get(0);
            setCardView(instance);
            progBar.setProgress(10);
        }


        playAnimation(parentLayout);



    }

    private void setOnTouch(View view) {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP){
                    GameController.playSoundEffect(context,"woosh");
                    // Do what you want
                    return true;
                }
                return false;
            }
        });

    }

    public void playAnimation(FrameLayout layout) {

        AnimationDrawable animationDrawable = (AnimationDrawable) layout.getBackground();


        graphicsControl.hideSystemUI(getWindow().getDecorView());
        graphicsControl.startAnimation(animationDrawable, 2);
    }

    public void setCardView(Instance instance){



        if(vitailityCount%3 ==1 ){
            progBar.setProgress(progBar.getProgress() + 10);
        }


        int bottomMargin = Utils.dpToPx(160);
        Point windowSize = Utils.getDisplaySize(getWindowManager());

        //card builder and animation.
        mSwipeView.getBuilder()
                // displays stack of cards
                .setDisplayViewCount(1)
                .setIsUndoEnabled(true)
                .setSwipeVerticalThreshold(Utils.dpToPx(50))
                .setSwipeHorizontalThreshold(Utils.dpToPx(50))
                .setHeightSwipeDistFactor(10)
                .setWidthSwipeDistFactor(5)
                .setSwipeDecor(new SwipeDecor()
                        .setViewWidth(windowSize.x - 110)
                        .setViewHeight(windowSize.y - 250 - bottomMargin)
                        .setViewGravity(Gravity.TOP)
                        .setPaddingTop(20)
                        .setSwipeMaxChangeAngle(10)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.tinder_swipe_in_msg_view)
                        .setSwipeOutMsgLayoutId(R.layout.tinder_swipe_out_msg_view)
                        .setSwipeRotationAngle(7)
                        .setSwipeAnimTime(mAnimationDuration));

        final Point cardViewHolderSize = new Point(windowSize.x, windowSize.y - bottomMargin);


        currentID = instance.getInstanceID();
        mSwipeView.addView(new TinderCard(mContext, instance, cardViewHolderSize, this));


        storyText.setText(instance.getStoryText());

        if (!TextUtils.isEmpty(instance.getMusic()))
            GameController.playMusic(this,instance.getMusic(), .3f);

        if (!TextUtils.isEmpty(instance.getSoundEffect()))
            GameController.playSoundEffect(this,instance.getSoundEffect());


        if(!TextUtils.isEmpty(instance.getAmbiance()))
           GameController.playSoundEffect(this,instance.getAmbiance());

        //     if (instance.getMusic() != null)
        //     GameController.playSounds(this,instance.getMusic(),1);


        findViewById(R.id.exploreBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(false);
                progBar.setProgress(progBar.getProgress() + 10);

            }
        });

        findViewById(R.id.inspectBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(true);


            }
        });

    }


    @Override
    public void onSwipeDown() {
        //Toast.makeText(this, "bottom", Toast.LENGTH_SHORT).show();
        Log.d("GRAPE", "main");
        setCardView(instance);
    }
    @Override
    public void onSwipeUp() {
        //Toast.makeText(this, "up", Toast.LENGTH_SHORT).show();
        setCardView(instance);

    }
    @Override
    public void onSwipeLeft() {

        // HEY FUTURE CARLIN, PLZ FIX TRUNCATIONS THANK YOU LOVE U BYE

        SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        String instID = instance.getNegativeID();
        int first = Integer.parseInt(instID.substring(0,1));
        int second = Integer.parseInt(instID.substring(2,3));
        Log.d("GRAPE", "main left" + first+second);

        instance = instanceArrayList.get(first-1).get(second-1);
        setCardView(instance);

        editor.putString("ID", instID);
        editor.putInt("Prg", progBar.getProgress());
        editor.commit();

        vitailityCount++;
        int progress = Integer.parseInt(instance.getChapter());

        if(progress!=0)
        {
            progBar.setProgress(progBar.getProgress() + progress);

            if(progress <0){
                Toast.makeText(this, "You've lost " +progress+" vitality", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "You've gained " +progress+" vitality", Toast.LENGTH_SHORT).show();

            }
        }

    }
    @Override
    public void onPicClick()
    {

    }
    @Override
    public void onSwipeRight() {

        SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        String instID = instance.getPositiveID();
        addInventory(instance);
        hasKey(instance);
        int first = Integer.parseInt(instID.substring(0,1));
        int second = Integer.parseInt(instID.substring(2,3));
        Log.d("GRAPE", "main right" + first+second);

        instance = instanceArrayList.get(first-1).get(second-1);
        setCardView(instance);
        editor.putInt("Prg", progBar.getProgress());
        editor.putString("ID", instID);
        editor.commit();
        vitailityCount++;
        int progress = Integer.parseInt(instance.getChapter());

        if(progress!=0)
        {
            progBar.setProgress(progBar.getProgress() + progress);

            if(progress <0){
                Toast.makeText(this, "You've lost " +progress+" vitality", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "You've eaten and gained " +progress+" vitality", Toast.LENGTH_SHORT).show();

            }
        }
    }
    public void addInventory(Instance instance){
        try {
            if (instance.getItem() != "null") ;
            {
                inverntoryArray.add(instance.getItem());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean hasKey(Instance instance) {
        try{
        String item = instance.getItemKey();
        for (int i = 0; i < inverntoryArray.size(); i++) {
            if (item == inverntoryArray.get(i)) {
                inverntoryArray.set(i, inverntoryArray.get(inverntoryArray.size()));
                inverntoryArray.set(inverntoryArray.size(), "null");
                return true;
            }
        }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public void inspect()
    {
        Toast.makeText(this, "inspect", Toast.LENGTH_SHORT).show();
    }
    public void explore()
    {
        Toast.makeText(this, "explore", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }
    //hides system tray and makes application full screen.
    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
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
