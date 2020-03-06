package dependency.greendao.test.tinder.directional;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.widget.Button;
import android.widget.TextView;

public class FontController {


    public static void changeFontTo(Activity activity, TextView textView, String path) {
        Typeface tf = Typeface.createFromAsset(activity.getAssets(), path); //you can use your activity name instead of getActivity().
        textView.setTypeface(tf);
    }
}
