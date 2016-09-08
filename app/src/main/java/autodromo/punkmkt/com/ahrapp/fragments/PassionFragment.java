package autodromo.punkmkt.com.ahrapp.fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.support.v4.app.Fragment;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import autodromo.punkmkt.com.ahrapp.BaseActivity;
import autodromo.punkmkt.com.ahrapp.MyVolleySingleton;
import autodromo.punkmkt.com.ahrapp.R;
import autodromo.punkmkt.com.ahrapp.SinglePassionActivity;
import autodromo.punkmkt.com.ahrapp.adapters.CustomGrid;
import autodromo.punkmkt.com.ahrapp.adapters.ListAdapter;
import autodromo.punkmkt.com.ahrapp.utils.BitmapManager;
import autodromo.punkmkt.com.ahrapp.utils.RecyclingBitmapDrawable;

/**
 * Created by sebastianmendezgiron on 25/09/15.
 */
public class PassionFragment extends Fragment implements AdapterView.OnItemClickListener  {
    public final static Integer[] imageResIds = new Integer[] {
            R.drawable.hamilton, R.drawable.roseberg, R.drawable.kimi,
            R.drawable.vettel, R.drawable.bottas, R.drawable.massa,
            R.drawable.kvyat, R.drawable.ricciardo, R.drawable.checo,
            R.drawable.hukelberg, R.drawable.ericsson, R.drawable.nasr,
            R.drawable.sainz, R.drawable.verstappen, R.drawable.grosjean,
            R.drawable.pastor, R.drawable.alonso, R.drawable.button,
            R.drawable.merhi, R.drawable.stevens,  R.drawable.rossi,R.drawable.mexico
    };
    int[] imageId={
            R.drawable.hamilton_bg,
            R.drawable.roseberg_bg,
            R.drawable.kimi_bg,
            R.drawable.vettel_bg,
            R.drawable.bottas_bg,
            R.drawable.massa_bg,
            R.drawable.kvyat_bg,
            R.drawable.ricciardo_bg,
            R.drawable.checo_bg,
            R.drawable.hukelberg_bg,
            R.drawable.ericsson_bg,
            R.drawable.nasr_bg,
            R.drawable.sianz_bg,
            R.drawable.verstappen_bg,
            R.drawable.grosjean_bg,
            R.drawable.pastor_bg,
            R.drawable.alonso_bg,
            R.drawable.button_bg,
            R.drawable.merhi_bg,
            R.drawable.stevens_bg,
            R.drawable.rossi_bg,
            R.drawable.mexico_bg,

    };
    private ImageAdapter mAdapter;

    // Empty constructor as per Fragment docs
    public PassionFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Tracker tracker = ((MyVolleySingleton) getActivity().getApplication()).getTracker(MyVolleySingleton.TrackerName.APP_TRACKER);
        tracker.setScreenName(getString(R.string.menu_pasion));
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
        mAdapter = new ImageAdapter(getActivity().getApplicationContext());
        doFirstRun();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       // View v = inflater.inflate(R.layout.passion_activity,container,false);
        final View v = inflater.inflate(R.layout.passion_activity, container, false);
        final GridView mGridView = (GridView) v.findViewById(R.id.grid_ecards);
        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(this);
        return v;


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        Log.d("loddd",Integer.toString(position));
        //final Intent i = new Intent(getActivity(), ImageDetailActivity.class);
        //i.putExtra(ImageDetailActivity.EXTRA_IMAGE, position);
        //startActivity(i);

        Intent detailIntent = new Intent(getActivity(), SinglePassionActivity.class);
        detailIntent.putExtra("imageId", imageId[position]);
        //Log.d("imageId", Integer.toString(imageId[position]));
        //Toast.makeText(getActivity().getApxplicationContext(),imageId[position],Toast.LENGTH_SHORT).show();

        startActivity(detailIntent);
    }





    private void doFirstRun() {
        //int MODE_PRIVATE = 0;
        //SharedPreferences settings = getActivity().getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        //if (settings.getBoolean("isFirstRunPopupPasion", true)) {

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            LayoutInflater inflater = getActivity().getLayoutInflater();
            //builder.setTitle("Instrucciones");
            //builder.setMessage(marker.getSnippet());
            builder.setView(inflater.inflate(R.layout.instructions_comparte_pasion, null)).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                }
            });
            builder.create();

            builder.show();

            //SharedPreferences.Editor editor = settings.edit();
            //editor.putBoolean("isFirstRunPopupPasion", false);
            //editor.commit();
        //}
    }

    private class ImageAdapter extends BaseAdapter {
        private final Context mContext;

        public ImageAdapter(Context context) {
            super();
            mContext = context;
        }

        @Override
        public int getCount() {
            return imageResIds.length;
        }

        @Override
        public Object getItem(int position) {
            return imageResIds[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            ImageView imageView;
            if (convertView == null) { // if it's not recycled, initialize some attributes
                imageView = new ImageView(mContext);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setLayoutParams(new GridView.LayoutParams(
                        GridLayout.LayoutParams.WRAP_CONTENT,GridLayout.LayoutParams.WRAP_CONTENT));
            } else {
                imageView = (ImageView) convertView;
            }
            loadBitmap(imageResIds[position], imageView);
            return imageView;
        }
    }
    public void loadBitmap(int resId, ImageView imageView) {
        if (cancelPotentialWork(resId, imageView)) {
            Bitmap bitmap;
            bitmap =  BitmapManager.decodeSampledBitmapFromResource(getActivity().getApplicationContext().getResources(), R.drawable.descarga, 100, 100);
            final BitmapWorkerTask task = new BitmapWorkerTask(imageView);
            final AsyncDrawable asyncDrawable = new AsyncDrawable(getActivity().getApplicationContext().getResources(),bitmap, task);
            imageView.setImageDrawable(asyncDrawable);
            task.execute(resId);
            new RecyclingBitmapDrawable(getResources(),bitmap);
        }
    }
    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            BaseActivity.mMemoryCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(String key) {
        return BaseActivity.mMemoryCache.get(key);
    }
    static class AsyncDrawable extends BitmapDrawable {
        private final WeakReference<BitmapWorkerTask> bitmapWorkerTaskReference;

        public AsyncDrawable(Resources res, Bitmap bitmap,
                             BitmapWorkerTask bitmapWorkerTask) {
            super(res, bitmap);
            bitmapWorkerTaskReference =
                    new WeakReference<BitmapWorkerTask>(bitmapWorkerTask);
        }

        public BitmapWorkerTask getBitmapWorkerTask() {
            return bitmapWorkerTaskReference.get();
        }
    }

    public static boolean cancelPotentialWork(int data, ImageView imageView) {
        final BitmapWorkerTask bitmapWorkerTask = getBitmapWorkerTask(imageView);

        if (bitmapWorkerTask != null) {
            final int bitmapData = bitmapWorkerTask.data;
            if (bitmapData != data) {
                // Cancel previous task
                bitmapWorkerTask.cancel(true);
            } else {
                // The same work is already in progress
                return false;
            }
        }
        // No task associated with the ImageView, or an existing task was cancelled
        return true;
    }

    private static BitmapWorkerTask getBitmapWorkerTask(ImageView imageView) {
        if (imageView != null) {
            final Drawable drawable = imageView.getDrawable();
            if (drawable instanceof AsyncDrawable) {
                final AsyncDrawable asyncDrawable = (AsyncDrawable) drawable;
                return asyncDrawable.getBitmapWorkerTask();
            }
        }
        return null;
    }

    class BitmapWorkerTask extends AsyncTask<Integer, Void, Bitmap> {
        private final WeakReference<ImageView> imageViewReference;
        private int data = 0;

        public BitmapWorkerTask(ImageView imageView) {
            // Use a WeakReference to ensure the ImageView can be garbage collected
            imageViewReference = new WeakReference<ImageView>(imageView);
        }

        // Decode image in background.
        @Override
        protected Bitmap doInBackground(Integer... params) {
            final Bitmap bitmap = decodeSampledBitmapFromResource(getActivity().getApplicationContext().getResources(), params[0], 150, 100);
            addBitmapToMemoryCache(String.valueOf(params[0]), bitmap);
            return bitmap;
        }

        // Once complete, see if ImageView is still around and set bitmap.
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (isCancelled()) {
                bitmap = null;
            }

            if (imageViewReference != null && bitmap != null) {
                final ImageView imageView = imageViewReference.get();
                final BitmapWorkerTask bitmapWorkerTask =
                        getBitmapWorkerTask(imageView);
                if (this == bitmapWorkerTask && imageView != null) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        }
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }



}
