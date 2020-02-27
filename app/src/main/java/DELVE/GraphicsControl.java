package DELVE;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

public class GraphicsControl {
    public void hideSystemUI(View view) {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        view.setSystemUiVisibility(
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

    public void startAnimation(AnimationDrawable animationDrawable) {
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(3000);
        animationDrawable.start();
    }

    public void startAnimation(AnimationDrawable animationDrawable, int index) {
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(3000);
        animationDrawable.selectDrawable(index);
        animationDrawable.start();
    }

    public int getFrame(AnimationDrawable animationDrawable) {
        animationDrawable.stop();

    // The variable that will guard the frame number
        int frameNumber = 0;

    // Get the frame of the animation
        Drawable currentFrame, checkFrame;
        currentFrame = animationDrawable.getCurrent();

    // Checks the position of the frame
        for (int i = 0; i < animationDrawable.getNumberOfFrames(); i++) {
            checkFrame = animationDrawable.getFrame(i);
            if (checkFrame == currentFrame) {
                frameNumber = i;
                break;
            }
        }
        return frameNumber;
    }
}
