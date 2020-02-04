package dependency.greendao.test.tinder.directional;

import android.content.Context;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Debug;
import android.util.Log;

import java.util.ArrayList;

public class SoundController {

    String oldSound = "";
    Context activityContext;

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

   private void playOneShot(String soundName, int startTime, int duration) {

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

    }

    private void fadeOut(String soundName) { // fade out

    }

    private void playSound(String soundName) { // WORK ON THIS
       // Uri.
        //playr = MediaPlayer.create(activityContext,R.raw. + soundName);
       // MediaPlayer.cre
    }

}
