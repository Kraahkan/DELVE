package DELVE;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class SoundControl {

    private static SoundPool fxSound;
    private static MediaPlayer ambientPlayer;

    /*private static int chest_open_FX;
    private static int crows_FX;
    private static int crows_2_FX;
    private static int door_FX;
    private static int door_2_FX;
    private static int drawer_FX;
    private static int footsteps_gravel_FX;
    private static int footsteps_mud_FX;
    private static int footsteps_snow_FX;
    private static int inventory_FX;
    private static int loot_FX;
    private static int mining_FX;
    private static int move_inside_FX;
    private static int torch_FX;
    private static int train_FX;
    private static int water_drop_FX;*/


    public SoundControl(Context context) {
        //SoundPool (int maxStreams, int streamType, int srcQuality)
        fxSound = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);

        /*chest_open_FX = fxSound.load(context, R.raw.chest, 1);
        crows_FX = fxSound.load(context, R.raw.crows, 1);
        crows_2_FX = fxSound.load(context, R.raw.crows2, 1);
        door_FX = fxSound.load(context, R.raw.door_open, 1);
        door_2_FX = fxSound.load(context, R.raw.door_open2, 1);
        drawer_FX = fxSound.load(context, R.raw.drawer, 1);
        footsteps_gravel_FX = fxSound.load(context, R.raw.footsteps_gravel, 1);
        footsteps_mud_FX = fxSound.load(context, R.raw.footsteps_mud, 1);
        footsteps_snow_FX = fxSound.load(context, R.raw.footsteps_snow_crunchy, 1);
        inventory_FX = fxSound.load(context, R.raw.inventory, 1);
        loot_FX = fxSound.load(context, R.raw.loot, 1);
        mining_FX = fxSound.load(context, R.raw.mining, 1);
        move_inside_FX = fxSound.load(context, R.raw.move_inside, 1);
        torch_FX = fxSound.load(context, R.raw.torch, 1);
        train_FX = fxSound.load(context, R.raw.train_close, 1);
        water_drop_FX = fxSound.load(context, R.raw.water_drop, 1);*/
    }

//---------------------------------------------------------------------------------------------------------------------------------

/**                                             NEEDED?                                             */
    /*
     * Creates soundID for fxPlay method.
     * resID is a resource ID.
     * For reference how to get resID: https://stackoverflow.com/questions/7182525/android-get-r-raw-from-variable
     * */
    /*
    public void createID(Context context, int resID, int priority) {
        fxSound.load(context, resID, priority);
    }
    */
//---------------------------------------------------------------------------------------------------------------------------------

    /**
     * Starts playing sfx.
     * soundID is an integer assigned to var after soundPool.load()
     * loop = 0 - noLoop || loop = -1 - loop forever
     */
    public void fxPlay(int soundID, float leftVolume, float rightVolume, int priority, int loop, float rate) {
        fxSound.play(soundID, leftVolume, rightVolume, priority, loop, rate);
    }

    /**
     * Stops playing sfx.
     */
    public void fxStop() {
        fxSound.release();
    }

    /**
     * Starts playing Ambient.
     * resID == resourceID.
     * For reference how to get resID: https://stackoverflow.com/questions/7182525/android-get-r-raw-from-variable
     * View may be needed in method signature.
     */
    public void ambientPlay(/*View v,*/ Context context, int resID) {
        if (ambientPlayer == null) {
            ambientPlayer = MediaPlayer.create(context, resID);
        }
        ambientPlayer.start();
    }

    /**
     * Pauses ambient.
     * View may be needed in method signature.
     */

    public void ambientPause(/*View v,*/) {
        if (ambientPlayer != null) {
            ambientPlayer.stop();
        }
    }

    /**
     * Stops ambient.
     * Releases MediaPlayr to save resources.
     */
    public void ambientStop() {
        if (ambientPlayer != null) {
            ambientPlayer.release();
            ambientPlayer = null;
        }
    }
}
