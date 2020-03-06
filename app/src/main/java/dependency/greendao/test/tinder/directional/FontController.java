package dependency.greendao.test.tinder.directional;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

public class FontController {

    static Typeface typeface;
    //Context activityContext;

    public static void changeFontTo(Activity activity, TextView textView, String path) {
        typeface = Typeface.createFromAsset(activity.getAssets(), path); //you can use your activity name instead of getActivity().
        textView.setTypeface(typeface);
    }
}
