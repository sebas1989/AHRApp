
package autodromo.punkmkt.com.ahrapp;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.lang.ref.WeakReference;

import autodromo.punkmkt.com.ahrapp.utils.BitmapManager;
import autodromo.punkmkt.com.ahrapp.utils.RecyclingBitmapDrawable;
import autodromo.punkmkt.com.ahrapp.utils.TouchImageView;

/**
 * Created by sebastianmendezgiron on 04/10/15.
 */
public class ServicioDetalleActivity extends Activity {
    private TouchImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String descripcion = intent.getStringExtra("descripcion");
        String name = intent.getStringExtra("name");
        setContentView(R.layout.activity_servicio_detalle);
        Tracker tracker = ((MyVolleySingleton) getApplication()).getTracker(MyVolleySingleton.TrackerName.APP_TRACKER);
        tracker.setScreenName("SERVICIOS-ZONA"+name);
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
        imagen = (TouchImageView) findViewById(R.id.imagen_principal);
        ImageView imagen2 = (ImageView) findViewById(R.id.servicios_listado);


        switch (descripcion) {
            case "00":
                // imagen.setImageResource(R.drawable.area_verde_servicios);
                //imagen.setImageBitmap(BitmapManager.decodeSampledBitmapFromResource(getResources(), R.drawable.area_verde_servicios, width, height));
                loadBitmap(R.drawable.area_verde_servicios, imagen,750,683);
                break;
            case "11":
                //imagen.setImageResource(R.drawable.area_azul_servicios);
                //  imagen.setImageBitmap(BitmapManager.decodeSampledBitmapFromResource(getResources(), R.drawable.area_azul_servicios, width, height));
                loadBitmap(R.drawable.area_azul_servicios, imagen,750,683);
                break;
            case "22":
                // imagen.setImageResource(R.drawable.area_morada_servicios);
                //imagen.setImageBitmap(BitmapManager.decodeSampledBitmapFromResource(getResources(), R.drawable.area_azul_servicios, width, height));
                loadBitmap(R.drawable.area_morada_servicios, imagen,750,683);
                break;
            case "33":
                // imagen.setImageResource(R.drawable.area_amarilla_servicios);
                //imagen.setImageBitmap(BitmapManager.decodeSampledBitmapFromResource(getResources(), R.drawable.area_amarilla_servicios, width, height));
                loadBitmap(R.drawable.area_amarilla_servicios, imagen,750,683);
                break;
//            case "44":
//                // imagen.setImageResource(R.drawable.area_naranja_autodromo);
//                //imagen.setImageBitmap(BitmapManager.decodeSampledBitmapFromResource(getResources(), R.drawable.area_naranja_autodromo, width, height));
//                loadBitmap(R.drawable.area_naranja_autodromo, imagen);
//                break;
            case "55":
                // imagen.setImageResource(R.drawable.area_gris_servicios);
                // imagen.setImageBitmap(BitmapManager.decodeSampledBitmapFromResource(getResources(), R.drawable.area_gris_servicios, width, height));
                loadBitmap(R.drawable.area_gris_servicios, imagen,750,683);
                break;
            case "66":
                // imagen.setImageResource(R.drawable.area_cafe_servicios);
                // imagen.setImageBitmap(BitmapManager.decodeSampledBitmapFromResource(getResources(), R.drawable.area_cafe_servicios, width, height));
                loadBitmap(R.drawable.area_cafe_servicios, imagen,750,683);
                break;
            case "77":
                // imagen.setImageResource(R.drawable.area_cafe_servicios);
                // imagen.setImageBitmap(BitmapManager.decodeSampledBitmapFromResource(getResources(), R.drawable.area_cafe_servicios, width, height));
                loadBitmap(R.drawable.area_paddock_servicios, imagen,750,683);
                break;
            default:
                break;
        }

        loadBitmap2(R.drawable.servicios_listado, imagen2,750,900);
    }


    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            BaseActivity.mMemoryCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(String key) {
        return BaseActivity.mMemoryCache.get(key);
    }


    public void loadBitmap(int resId, ImageView imageView, int width, int height) {
        final String imageKey = String.valueOf(resId);

        final Bitmap bitmap = BaseActivity.mMemoryCache.get(imageKey);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            imageView.setImageResource(R.drawable.descarga);
            BitmapWorkerTask task = new BitmapWorkerTask(imageView, 100, 100);
            task.execute(resId);
        }


        new RecyclingBitmapDrawable(getResources(),bitmap);
    }
    public void loadBitmap2(int resId, ImageView imageView, int width, int height) {
        final String imageKey = String.valueOf(resId);

        final Bitmap bitmap = BaseActivity.mMemoryCache.get(imageKey);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            imageView.setImageResource(R.drawable.descarga);
            BitmapWorkerTask2 task = new BitmapWorkerTask2(imageView, 100, 100);
            task.execute(resId);
        }


        new RecyclingBitmapDrawable(getResources(),bitmap);
    }
    class BitmapWorkerTask extends AsyncTask<Integer, Void, Bitmap> {
        private final WeakReference<ImageView> imageViewReference;
        private int data = 0;
        private int width;
        private int height;

        public BitmapWorkerTask(ImageView imageView, int width, int height) {
            // Use a WeakReference to ensure the ImageView can be garbage collected
            imageViewReference = new WeakReference<ImageView>(imageView);
            this.width = width;
            this.height = height;
        }

        // Decode image in background.
        @Override
        protected Bitmap doInBackground(Integer... params) {
            final Bitmap bitmap = decodeSampledBitmapFromResource(getResources(), params[0],580,480);
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

    class BitmapWorkerTask2 extends AsyncTask<Integer, Void, Bitmap> {
        private final WeakReference<ImageView> imageViewReference;
        private int data = 0;
        private int width;
        private int height;

        public BitmapWorkerTask2(ImageView imageView, int width, int height) {
            // Use a WeakReference to ensure the ImageView can be garbage collected
            imageViewReference = new WeakReference<ImageView>(imageView);
            this.width = width;
            this.height = height;
        }

        // Decode image in background.
        @Override
        protected Bitmap doInBackground(Integer... params) {
            final Bitmap bitmap = decodeSampledBitmapFromResource(getResources(), params[0],335,486);
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



