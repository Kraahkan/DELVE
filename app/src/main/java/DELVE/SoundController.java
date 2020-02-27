package DELVE;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import java.util.ArrayList;

public class SoundController {

    public void initialize(Context context) {

        parseResources(context);

    }

    private void parseResources(Context context) {

        Resources resources = context.getResources();

        //resources.obtainTypedArray(R.raw);

        ArrayList<Integer> id = new ArrayList<Integer>();
        for (int i = 0; i <= 79; i++) {

        }

        id.add(context.getResources().getIdentifier("fx_chest", "raw", context.getPackageName())); // don't include file type
        Log.d("resources", id.get(0).toString());


    }

    private void fadeIn() {

    }

    private void fadeOut() {

    }

    public void crossFade() {
        fadeIn();
        fadeOut();
    }
}
