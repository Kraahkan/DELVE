package dependency.greendao.test.tinder.directional;

import android.content.Context;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Debug;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SoundController {

    static MediaPlayer oldSound;
    Context activityContext;

    private MediaPlayer mediaPlayer;

    float volume = 0;
    float oldVolume = 0;

    private int MAX_VOLUME = 180;

    public void initialize(Context context) {

        activityContext = context;
        parseResources(context);

    }

    private void parseResources(Context context) { // need to rewrite this

        Resources resources = context.getResources();

        //resources.obtainTypedArray(R.raw);

        ArrayList<Integer> id = new ArrayList<Integer>();
        for (int i = 0; i <= 79; i++) {
        }

        id.add(context.getResources().getIdentifier("fx_chest", "raw", context.getPackageName())); // don't include file type
        Log.d("resources",id.get(0).toString());

    }

    public void playOneShot(String soundName, int volume) {

        // later:  int startTime, int duration,
        playSound(soundName);
    }

    public void playMusic(String musicName, float desiredVolume) { // only one music at a time
        crossFade(musicName, desiredVolume);
    }

    public void playAmbiance(String ambianceName) { // only one ambiance at a time
        crossFade(ambianceName, 1);
    }


    private void crossFade(String newSound, float desiredVolume) {
        fadeIn(newSound, desiredVolume);
        fadeOut();

    }

    private void fadeIn(String soundName, float desiredVolume) {

        Uri uri=Uri.parse("android.resource://"+activityContext.getPackageName()+"/raw/" + soundName);
        oldSound = MediaPlayer.create(activityContext, uri);
        // mp.start();

        final int FADE_DURATION = 3000; //The duration of the fade, ex. 30000 is a proper fade
        //The amount of time between volume changes. The smaller this is, the smoother the fade
        final int FADE_INTERVAL = 250;
        final float MAX_VOLUME = desiredVolume; //The volume will increase from 0 to 1
        int numberOfSteps = FADE_DURATION/FADE_INTERVAL; //Calculate the number of fade steps
        //Calculate by how much the volume changes each step
        final float deltaVolume = MAX_VOLUME / (float)numberOfSteps;

        //Create a new Timer and Timer task to run the fading outside the main UI thread
        final Timer timer = new Timer(true);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                fadeInStep(deltaVolume, oldSound); //Do a fade step
                //Cancel and Purge the Timer if the desired volume has been reached
                if(oldVolume>=1f){
                    timer.cancel();
                    timer.purge();
                }
            }
        };

        timer.schedule(timerTask,FADE_INTERVAL,FADE_INTERVAL);

        oldSound.setVolume(0,0);
        oldSound.start();

    }

    private void fadeInStep(float deltaVolume, MediaPlayer mp){
        mp.setVolume(volume, volume);
        volume += deltaVolume;

    }

    private void fadeOutStep(float deltaVolume){
        oldSound.setVolume(oldVolume, oldVolume);
        oldVolume -= deltaVolume;

    }

    private void fadeOut() {


       MediaPlayer mp = oldSound;
        // mp.start();

        final int FADE_DURATION = 3000; //The duration of the fade, ex. 30000 is a proper fade
        //The amount of time between volume changes. The smaller this is, the smoother the fade
        final int FADE_INTERVAL = 250;
        final float MAX_VOLUME = 1; //The volume will increase from 0 to 1
        int numberOfSteps = FADE_DURATION/FADE_INTERVAL; //Calculate the number of fade steps
        //Calculate by how much the volume changes each step
        final float deltaVolume = MAX_VOLUME / (float)numberOfSteps;

        //Create a new Timer and Timer task to run the fading outside the main UI thread
        final Timer timer = new Timer(true);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                fadeOutStep(deltaVolume); //Do a fade step
                //Cancel and Purge the Timer if the desired volume has been reached
                if(volume<=0f){
                    timer.cancel();
                    timer.purge();
                }
            }
        };

        timer.schedule(timerTask,FADE_INTERVAL,FADE_INTERVAL);

        mp.setVolume(0,0);
        mp.start();
    }
    private void playSound(String soundName) { // WORK ON THIS
        // Uri.
        //playr = MediaPlayer.create(activityContext,R.raw. + soundName);
        // MediaPlayer.cre

        try {

            Uri uri = Uri.parse("android.resource://" + activityContext.getPackageName() + "/raw/" + soundName);
            MediaPlayer mp = MediaPlayer.create(activityContext, uri);


            // final float logVolume = (float) (1 - (Math.log(MAX_VOLUME - volume) / Math.log(MAX_VOLUME)));
            //mp.setVolume(logVolume, logVolume);
            mp.start();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

}
 