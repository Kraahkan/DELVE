package dependency.greendao.test.tinder.directional;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by janisharali on 21/08/16.
 */
public class Utils {

    private static final String TAG = "Utils";

    public static ArrayList<ArrayList<Instance>> loadInstance(Context context) {
        try {
            int first;
            int second;
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            JSONArray array = new JSONArray(loadJSONFromAsset(context, "instance.json"));
            Log.d("utils", "builder");
            ArrayList<Instance> instanceListInner= new ArrayList<Instance>();

            ArrayList<ArrayList<Instance>> instanceListOuter = new ArrayList<ArrayList<Instance>>();


            for (int i = 0; i < 2; i++) {

                Instance instance = gson.fromJson(array.getString(i), Instance.class);

                String ID = instance.getInstanceID();
                first = Integer.parseInt(ID.substring(0,1));
                second = Integer.parseInt(ID.substring(2,3));

                instanceListInner.add(instance);

                Log.d("utils", "instance " +instanceListInner.get(i).getStoryText());



                Log.d("utils", "first "+first+" secound "+second);


            }
            instanceListOuter.add(instanceListInner);
            Log.d("utils", "return");
            return instanceListOuter;
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("utils", "return null");
            return null;
        }
    }

    private static String loadJSONFromAsset(Context context, String jsonFileName) {
        String json = null;
        InputStream is = null;
        try {
            AssetManager manager = context.getAssets();
            Log.d(TAG, "path " + jsonFileName);
            is = manager.open(jsonFileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static Point getDisplaySize(WindowManager windowManager) {
        try {
            if (Build.VERSION.SDK_INT > 16) {
                Display display = windowManager.getDefaultDisplay();
                DisplayMetrics displayMetrics = new DisplayMetrics();
                display.getMetrics(displayMetrics);
                return new Point(displayMetrics.widthPixels, displayMetrics.heightPixels);
            } else {
                return new Point(0, 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Point(0, 0);
        }
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}
