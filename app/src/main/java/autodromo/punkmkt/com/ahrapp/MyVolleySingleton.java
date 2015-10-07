package autodromo.punkmkt.com.ahrapp;

/**
 * Created by sebastianmendezgiron on 14/09/15.
 */
/**
 * Created by germanpunk on 28/07/15.
 */

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * copied from the official documentation
 */

import android.app.Application;
import android.content.Intent;
import android.text.TextUtils;

import autodromo.punkmkt.com.ahrapp.models.CustomUser;
import autodromo.punkmkt.com.ahrapp.R;


public class MyVolleySingleton extends Application {

    public static final String TAG = MyVolleySingleton.class.getSimpleName();

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private static MyVolleySingleton mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        FlowManager.init(MyVolleySingleton.this);
        Parse.initialize(this,
                getResources().getString(R.string.parse_application_id),
                getResources().getString(R.string.parse_client_key));
        ParseObject.registerSubclass(CustomUser.class);
        ParseInstallation.getCurrentInstallation().saveInBackground();

        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);

        /*if (isFirstRun) {

            Intent myIntent = new Intent(getApplicationContext(), ConfiguracionActivity.class);
            myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplicationContext().startActivity(myIntent);
        }*/


        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).commit();
    }

    public static synchronized MyVolleySingleton getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(MyVolleySingleton.this);
        }

        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new MyLruBitmapCache());
        }
        return this.mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}