package dependency.greendao.test.tinder.directional;

import android.content.Context;

public class GameController {


    public static void playMusic(Context context, String musicName, float volume) {

        SoundController soundController = new SoundController();
        soundController.initialize(context);
        // soundController.playOneShot("fx_door_open",0 );
        //soundController.playOneShot("mus_river",0 );
        soundController.playMusic(musicName, volume);
    }
}
