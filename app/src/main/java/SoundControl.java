import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import dependency.greendao.test.tinder.directional.R;

public class SoundControl {

    private static SoundPool soundPool;

    private static int chest_open_Sound;
    private static int crows_Sound;
    private static int crows_2_Sound;
    private static int door_Sound;
    private static int door_2_Sound;
    private static int drawer_Sound;
    private static int footsteps_gravel_Sound;
    private static int footsteps_mud_Sound;
    private static int footsteps_snow_Sound;
    private static int inventory_Sound;
    private static int loot_Sound;
    private static int mining_Sound;
    private static int move_inside_Sound;
    private static int torch_Sound;
    private static int train_Sound;
    private static int water_drop_Sound;


    public SoundControl(Context context) {
        //SoundPool (int maxStreams, int streamType, int srcQuality)
        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);

        chest_open_Sound = soundPool.load(context, R.raw.chest, 1);
        crows_Sound = soundPool.load(context, R.raw.crows, 1);
        crows_2_Sound = soundPool.load(context, R.raw.crows2, 1);
        door_Sound = soundPool.load(context, R.raw.door_open, 1);
        door_2_Sound = soundPool.load(context, R.raw.door_open2, 1);
        drawer_Sound = soundPool.load(context, R.raw.drawer, 1);
        footsteps_gravel_Sound = soundPool.load(context, R.raw.footsteps_gravel, 1);
        footsteps_mud_Sound = soundPool.load(context, R.raw.footsteps_mud, 1);
        footsteps_snow_Sound = soundPool.load(context, R.raw.footsteps_snow_crunchy, 1);
        inventory_Sound = soundPool.load(context, R.raw.inventory, 1);
        loot_Sound = soundPool.load(context, R.raw.loot, 1);
        mining_Sound = soundPool.load(context, R.raw.mining, 1);
        move_inside_Sound = soundPool.load(context, R.raw.move_inside, 1);
        torch_Sound = soundPool.load(context, R.raw.torch, 1);
        train_Sound = soundPool.load(context, R.raw.train_close, 1);
        water_drop_Sound = soundPool.load(context, R.raw.water_drop, 1);
    }

    public void playSFX (int soundID, float leftVolume, float rightVolume, int priority, int loop, float rate) {
        soundPool.play(soundID, leftVolume, rightVolume, priority, loop, rate);
    }

}
