package autodromo.punkmkt.com.ahrapp;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.lang.ref.WeakReference;

import autodromo.punkmkt.com.ahrapp.models.Zona;
import autodromo.punkmkt.com.ahrapp.utils.BitmapManager;
import autodromo.punkmkt.com.ahrapp.utils.RecyclingBitmapDrawable;
import autodromo.punkmkt.com.ahrapp.utils.TouchImageView;

/**
 * Created by sebastianmendezgiron on 04/10/15.
 */
public class ZonaDetalleActivity extends Activity {
    Zona zona;
    private TouchImageView imagen;
    TextView nombre;
    TextView descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String nombre_ = intent.getStringExtra("nombre_completo");
        String descripcion_ = intent.getStringExtra("descripcion");
        String mapa = intent.getStringExtra("mapa");

        Tracker tracker = ((MyVolleySingleton) getApplication()).getTracker(MyVolleySingleton.TrackerName.APP_TRACKER);
        tracker.setScreenName("ZONA-"+nombre_);
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
        setContentView(R.layout.activity_zona_detalle);

        imagen = (TouchImageView) findViewById(R.id.imagen_principal);
        nombre = (TextView) findViewById(R.id.nombreZona);
        descripcion = (TextView) findViewById(R.id.descripcionZona);


        int width = 400;
        int height = 500;

        switch (Integer.parseInt(id)){
            case 0:
                //imagen.setImageResource(R.drawable.area_verde_autodromo);
                //imagen.setImageBitmap(BitmapManager.decodeSampledBitmapFromResource(getResources(), R.drawable.area_verde_autodromo, width, height));

                loadBitmap(R.drawable.area_verde_autodromo, imagen);
                break;
            case 1:
                //imagen.setImageResource(R.drawable.area_azul_autodromo);
                //imagen.setImageBitmap(BitmapManager.decodeSampledBitmapFromResource(getResources(), R.drawable.area_azul_autodromo, width, height));
                loadBitmap(R.drawable.area_azul_autodromo, imagen);
                break;
            case 2:
                //imagen.setImageResource(R.drawable.area_morada_autodromo);
                //imagen.setImageBitmap(BitmapManager.decodeSampledBitmapFromResource(getResources(), R.drawable.area_morada_autodromo, width, height));
                loadBitmap(R.drawable.area_morada_autodromo, imagen);
                break;
            case 3:
                //imagen.setImageResource(R.drawable.area_amarilla_autodromo);
                //imagen.setImageBitmap(BitmapManager.decodeSampledBitmapFromResource(getResources(), R.drawable.area_amarilla_autodromo, width, height));
                loadBitmap(R.drawable.area_amarilla_autodromo, imagen);
                break;
//            case 4:
//                //imagen.setImageResource(R.drawable.area_naranja_autodromo);
//                //imagen.setImageBitmap(BitmapManager.decodeSampledBitmapFromResource(getResources(), R.drawable.area_naranja_autodromo, width, height));
//                loadBitmap(R.drawable.area_naranja_autodromo, imagen);
//                break;
            case 4:
                //imagen.setImageResource(R.drawable.area_gris_autodromo);
               // imagen.setImageBitmap(BitmapManager.decodeSampledBitmapFromResource(getResources(), R.drawable.area_gris_autodromo, width, height));
                loadBitmap(R.drawable.area_gris_autodromo, imagen);
                break;
            case 5:
                //imagen.setImageResource(R.drawable.area_cafe_autodromo);
                //imagen.setImageBitmap(BitmapManager.decodeSampledBitmapFromResource(getResources(), R.drawable.area_cafe_autodromo, width, height));
                loadBitmap(R.drawable.area_cafe_autodromo, imagen);
                break;
            case 6:
                //imagen.setImageResource(R.drawable.area_cafe_autodromo);
                //imagen.setImageBitmap(BitmapManager.decodeSampledBitmapFromResource(getResources(), R.drawable.area_cafe_autodromo, width, height));
                loadBitmap(R.drawable.area_paddock_autodromo, imagen);
                break;
            default:
                break;
        }

        zona = new Zona(Integer.parseInt(id),"",nombre_,descripcion_,mapa);
        createDetailZona(zona);
    }

    public void createDetailZona(Zona zona){
        nombre.setText(zona.getNameCompleto());
        descripcion.setText(zona.getDescripcion());
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
            final Bitmap bitmap = decodeSampledBitmapFromResource(getResources(), params[0], 350, 320);
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
