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
            int totalCount=0;
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            JSONArray array = new JSONArray(loadJSONFromAsset(context, "instance.json"));
            ArrayList<Instance> instanceListInner= new ArrayList<Instance>();
            ArrayList<ArrayList<Instance>> instanceListOuter = new ArrayList<ArrayList<Instance>>();

            Log.d("utils", "totalCount "+totalCount+"  array.length() "+ array.length());

            Instance instance = gson.fromJson(array.getString(totalCount), Instance.class);
            String instID = instance.getInstanceID();

            first = Integer.parseInt(instID.substring(0,1));
            second = Integer.parseInt(instID.substring(2,3));

            for (int i = 0; totalCount < array.length(); i++) {

                int j = 1;

                //Log.d("utils", "first "+first+" secound "+second);

                while(j==first) {
                    //make sure j resets after each loop.

                    instanceListInner.add(instance);
                    instance = gson.fromJson(array.getString(totalCount), Instance.class);

                    instID = instance.getInstanceID();
                    first = Integer.parseInt(instID.substring(0,1));
                    second = Integer.parseInt(instID.substring(2,3));
                    Log.d("utils", "first "+first+"        secound "+second);
                    totalCount++;
                    }
                j++;
                totalCount++;
                instanceListInner.add(instance);

                instanceListOuter.add(instanceListInner);

            }

            String ID = instanceListOuter.get(0).get(0).getInstanceID();
            Log.d("utils", "first "+ID);
             ID = instanceListOuter.get(0).get(1).getInstanceID();
            Log.d("utils", "second "+ID);
             ID = instanceListOuter.get(1).get(0).getInstanceID();
            Log.d("utils", "third "+ID);
             ID = instanceListOuter.get(1).get(1).getInstanceID();
            Log.d("utils", "fourth "+ID);

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
