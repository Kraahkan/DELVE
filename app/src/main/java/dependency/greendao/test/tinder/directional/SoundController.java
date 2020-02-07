package dependency.greendao.test.tinder.directional;

import android.content.Context;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Debug;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SoundController {

    String oldSound = "";
    Context activityContext;

    private MediaPlayer mediaPlayer;

    float volume = 0;

    private final static int MAX_VOLUME = 180;

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

   public void playMusic(String musicName) { // only one music at a time
       crossFade(musicName);
   }

    public void playAmbiance(String ambianceName) { // only one ambiance at a time
        crossFade(ambianceName);
    }


   private void crossFade(String newSound) {
        fadeIn(newSound);
        fadeOut(oldSound);

        oldSound = newSound;
   }

    private void fadeIn(String soundName) {

        Uri uri=Uri.parse("android.resource://"+activityContext.getPackageName()+"/raw/" + soundName);
        final MediaPlayer mp = MediaPlayer.create(activityContext, uri);
       // mp.start();

        final int FADE_DURATION = 3000; //The duration of the fade, ex. 30000 is a proper fade
        //The amount of time between volume changes. The smaller this is, the smoother the fade
        final int FADE_INTERVAL = 250;
        final int MAX_VOLUME = 1; //The volume will increase from 0 to 1
        int numberOfSteps = FADE_DURATION/FADE_INTERVAL; //Calculate the number of fade steps
        //Calculate by how much the volume changes each step
        final float deltaVolume = MAX_VOLUME / (float)numberOfSteps;

        //Create a new Timer and Timer task to run the fading outside the main UI thread
        final Timer timer = new Timer(true);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                fadeInStep(deltaVolume, mp); //Do a fade step
                //Cancel and Purge the Timer if the desired volume has been reached
                if(volume>=1f){
                    timer.cancel();
                    timer.purge();
                }
            }
        };

        timer.schedule(timerTask,FADE_INTERVAL,FADE_INTERVAL);

        mp.setVolume(0,0);
        mp.start();
    }

    private void fadeInStep(float deltaVolume, MediaPlayer mp){
        mp.setVolume(volume, volume);
        volume += deltaVolume;

    }

    private void fadeOut(String soundName) { // fade out

    }

    private void playSound(String soundName) { // WORK ON THIS
       // Uri.
        //playr = MediaPlayer.create(activityContext,R.raw. + soundName);
       // MediaPlayer.cre

        Uri uri=Uri.parse("android.resource://"+activityContext.getPackageName()+"/raw/" + soundName);
        MediaPlayer mp = MediaPlayer.create(activityContext, uri);


        // final float logVolume = (float) (1 - (Math.log(MAX_VOLUME - volume) / Math.log(MAX_VOLUME)));
        //mp.setVolume(logVolume, logVolume);
        mp.start();

    }

}
