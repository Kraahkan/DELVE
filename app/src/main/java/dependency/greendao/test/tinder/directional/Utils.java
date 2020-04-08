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

            int totalCount=0, j, first;
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            JSONArray array = new JSONArray(loadJSONFromAsset(context, "instance.json"));

            ArrayList<Instance> instanceListInner= new ArrayList<Instance>();
            ArrayList<ArrayList<Instance>> instanceListOuter = new ArrayList<ArrayList<Instance>>();

            Instance instance = gson.fromJson(array.getString(totalCount), Instance.class);
            instanceListInner.add(instance);

            String instID = instance.getInstanceID();
            first = Integer.parseInt(instID.substring(0,1));

            totalCount++;
            j = first;

            for (int i = 0; totalCount < array.length(); i++) {

                while(j==first) {

                    instance = gson.fromJson(array.getString(totalCount), Instance.class);
                    totalCount++;
                    instID = instance.getInstanceID();
                    first = Integer.parseInt(instID.substring(0,1));

                        if (first>j || totalCount==array.length()){
                            j++;
                            instanceListInner.add(instance);
                            instanceListOuter.add(instanceListInner);
                            instanceListInner= new ArrayList<Instance>();

                        }
                    instanceListInner.add(instance);
                    }
            }


            /*
            String ID;
             ID = instanceListOuter.get(0).get(0).getInstanceID();
            Log.d("utils", "first "+ID);
             ID = instanceListOuter.get(0).get(1).getInstanceID();
            Log.d("utils", "second "+ID);
             ID = instanceListOuter.get(0).get(2).getInstanceID();
            Log.d("utils", "third "+ID);
             ID = instanceListOuter.get(1).get(0).getInstanceID();
            Log.d("utils", "fourth "+ID);
             ID = instanceListOuter.get(1).get(1).getInstanceID();
            Log.d("utils", "fifth "+ID);
             ID = instanceListOuter.get(1).get(2).getInstanceID();
            Log.d("utils", "sixth "+ID);
             ID = instanceListOuter.get(1).get(3).getInstanceID();
            Log.d("utils", "seventh "+ID);
             ID = instanceListOuter.get(2).get(0).getInstanceID();
            Log.d("utils", "eigth "+ID);
             ID = instanceListOuter.get(2).get(1).getInstanceID();
            Log.d("utils", "ninth "+ID);
*/
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
