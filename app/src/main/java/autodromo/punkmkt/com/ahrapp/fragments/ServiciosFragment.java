package autodromo.punkmkt.com.ahrapp.fragments;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.lang.ref.WeakReference;
import java.text.DecimalFormat;
import java.util.ArrayList;

import autodromo.punkmkt.com.ahrapp.BaseActivity;
import autodromo.punkmkt.com.ahrapp.MyVolleySingleton;
import autodromo.punkmkt.com.ahrapp.R;
import autodromo.punkmkt.com.ahrapp.adapters.ServiciosAdapter;
import autodromo.punkmkt.com.ahrapp.models.Zona;
import autodromo.punkmkt.com.ahrapp.utils.BitmapManager;
import autodromo.punkmkt.com.ahrapp.utils.RecyclingBitmapDrawable;
import autodromo.punkmkt.com.ahrapp.utils.TouchImageView;

/**
 * Created by sebastianmendezgiron on 14/10/15.
 */

/**
 * Created by sebastianmendezgiron on 01/10/15.
 */
public class ServiciosFragment extends Fragment {

    private RecyclerView.Adapter adapter;
    private TouchImageView imagen;

    private ArrayList<Zona> zonas = new ArrayList<Zona>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.como_llegar_activity,container,false);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Tracker tracker = ((MyVolleySingleton) getActivity().getApplication()).getTracker(MyVolleySingleton.TrackerName.APP_TRACKER);
        tracker.setScreenName(getString(R.string.servicios));
        tracker.send(new HitBuilders.ScreenViewBuilder().build());

        imagen = (TouchImageView) getActivity().findViewById(R.id.mapaGeneral);

        loadBitmap(R.drawable.mapa_general_servicios, imagen);

        imagen.setOnTouchImageViewListener(new TouchImageView.OnTouchImageViewListener() {
            @Override
            public void onMove() {
                PointF point = imagen.getScrollPosition();
                RectF rect = imagen.getZoomedRect();
                float currentZoom = imagen.getCurrentZoom();
                boolean isZoomed = imagen.isZoomed();
            }
        });

        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        Zona z0 = new Zona(0, "VERDE",  "Zona Verde", "00", null);
        Zona z1 = new Zona(1, "AZUL",   "Zona Azul", "11", null);
        Zona z2 = new Zona(2, "MORADA", "Zona Morada", "22", null);
        Zona z3 = new Zona(3, "AMARILLA","Zona amarilla", "33", null);
        //Zona z4 = new Zona(4, "NARANJA", "Zona naranja", "44", null);
        Zona z5 = new Zona(4, "GRIS","Zona Gris", "55", null);
        Zona z6 = new Zona(5, "CAFÉ","Zona Café", "66", null);
        Zona z7 = new Zona(6, "PADDOCK CLUB","PADDOCK CLUB", "77", null);

        zonas.add(z0);
        zonas.add(z1);
        zonas.add(z2);
        zonas.add(z3);
        //zonas.add(z4);
        zonas.add(z5);
        zonas.add(z6);
        zonas.add(z7);
        adapter = new ServiciosAdapter(zonas);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            BaseActivity.mMemoryCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(String key) {
        return BaseActivity.mMemoryCache.get(key);
    }


    public void loadBitmap(int resId, ImageView imageView) {
        final String imageKey = String.valueOf(resId);

        final Bitmap bitmap = BaseActivity.mMemoryCache.get(imageKey);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            //imageView.setImageResource(R.drawable.loading);
            BitmapWorkerTask task = new BitmapWorkerTask(imageView);
            task.execute(resId);
        }

        new RecyclingBitmapDrawable(getResources(),bitmap);
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
            final Bitmap bitmap = decodeSampledBitmapFromResource(getResources(), params[0], 500, 400);
            addBitmapToMemoryCache(String.valueOf(params[0]), bitmap);
            return bitmap;
        }

        // Once complete, see if ImageView is still around and set bitmap.
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (imageViewReference != null && bitmap != null) {
                final ImageView imageView = imageViewReference.get();
                if (imageView != null) {
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

    public static boolean cancelPotentialWork(int data, ImageView imageView) {
        final BitmapWorkerTask bitmapWorkerTask = getBitmapWorkerTask(imageView);

        if (bitmapWorkerTask != null) {
            final int bitmapData = bitmapWorkerTask.data;
            // If bitmapData is not yet set or it differs from the new data
            if (bitmapData == 0 || bitmapData != data) {
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
}
