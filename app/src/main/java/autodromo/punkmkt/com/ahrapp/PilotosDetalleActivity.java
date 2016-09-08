package autodromo.punkmkt.com.ahrapp;

/**
 * Created by sebastianmendezgiron on 25/09/15.
 */
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import autodromo.punkmkt.com.ahrapp.models.Piloto;
import autodromo.punkmkt.com.ahrapp.utils.RecyclingBitmapDrawable;

public class PilotosDetalleActivity extends Activity {
    Piloto piloto;
    ImageView imagen;
    TextView descripcion_nombre_piloto;
    TextView descripcion_numero_piloto;
    TextView descripcion_equipo_piloto;
    TextView descripcion_nacionalidad_piloto;
    TextView descripcion_fecha_nacimiento_piloto;
    TextView descripcion_campeonatos_piloto;
    TextView descripcion_grands_prix_piloto;
    TextView descripcion_podiums_piloto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilotos_detalle);
        //imagen = (ImageView) findViewById(R.id.imagen_principal);

        ImageView mImageView = (ImageView) findViewById(R.id.imagen_principal);


        descripcion_nombre_piloto = (TextView) findViewById(R.id.descripcion_nombre_piloto);
        descripcion_numero_piloto = (TextView) findViewById(R.id.descripcion_numero_piloto);
        descripcion_equipo_piloto = (TextView) findViewById(R.id.descripcion_equipo_piloto);
        descripcion_nacionalidad_piloto = (TextView) findViewById(R.id.descripcion_nacionalidad_piloto);
        descripcion_fecha_nacimiento_piloto = (TextView) findViewById(R.id.descripcion_fecha_nacimiento_piloto);
        descripcion_campeonatos_piloto = (TextView) findViewById(R.id.descripcion_campeonatos_piloto);
        descripcion_grands_prix_piloto = (TextView) findViewById(R.id.descripcion_grandsprix_piloto);
        descripcion_podiums_piloto = (TextView) findViewById(R.id.descripcion_podiums_piloto);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String nombre = intent.getStringExtra("nombre");
        //String image = intent.getStringExtra("image");
        String numero = intent.getStringExtra("numero");
        String equipo = intent.getStringExtra("equipo");
        String nacionalidad = intent.getStringExtra("nacionalidad");
        String fecha_nacimiento = intent.getStringExtra("fecha_nacimiento");
        String campeonatos = intent.getStringExtra("campeonatos");
        String grands_prix = intent.getStringExtra("grands_prix");
        String podiums = intent.getStringExtra("podiums");

        final BitmapFactory.Options options = new BitmapFactory.Options();
        //options.inJustDecodeBounds=true;
        options.inSampleSize = 2;
        switch (Integer.parseInt(id)){
            case 0:
                //imagen.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.alexander_rossi_car,options));
                loadBitmap(R.drawable.alexander_rossi_car, mImageView);
                break;
            case 1:
               // imagen.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.carlos_sainz_car, options));
                loadBitmap(R.drawable.carlos_sainz_car, mImageView);
                break;
            case 2:
                //imagen.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.daniel_ricciardo_car, options));
                loadBitmap(R.drawable.daniel_ricciardo_car, mImageView);
                break;
            case 3:
                //imagen.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.daniil_kvyat_car, options));
                loadBitmap(R.drawable.daniil_kvyat_car, mImageView);
                break;
            case 4:
                //imagen.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.felipe_massa_car, options));
                loadBitmap(R.drawable.felipe_massa_car, mImageView);
                break;
            case 5:
                // imagen.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.felipe_nassar_car, options));
                loadBitmap(R.drawable.felipe_nassar_car, mImageView);
                break;
            case 6:
                //imagen.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.fernando_alonso_car, options));
                loadBitmap(R.drawable.fernando_alonso_car, mImageView);
                break;
            case 7:
                // imagen.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.jenson_button_car, options));
                loadBitmap(R.drawable.jenson_button_car, mImageView);
                break;
            case 8:
                // imagen.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.kimi_rnikkinen_car, options));
                loadBitmap(R.drawable.kimi_rnikkinen_car, mImageView);
                break;
            case 9:
                // imagen.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.lewis_hamilton_car, options));
                loadBitmap(R.drawable.lewis_hamilton_car, mImageView);
                break;
            case 10:
                //imagen.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.marcus_ericsson_car, options));
                loadBitmap(R.drawable.marcus_ericsson_car, mImageView);
                break;
            case 11:
                // imagen.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.max_verstappen_car, options));
                loadBitmap(R.drawable.max_verstappen_car, mImageView);
                break;
            case 12:
                // imagen.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.nico_hulkenberg_car, options));
                loadBitmap(R.drawable.nico_hulkenberg_car, mImageView);
                break;
            case 13:
                //imagen.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.rico_rosberg_car, options));
                loadBitmap(R.drawable.rico_rosberg_car, mImageView);
                break;
            case 14:
                //imagen.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.pastor_maldonado_car, options));
                loadBitmap(R.drawable.pastor_maldonado_car, mImageView);
                break;
            case 15:
                //imagen.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.romain_grosjean_car, options));
                loadBitmap(R.drawable.romain_grosjean_car, mImageView);
                break;
            case 16:
                //imagen.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.merhi_car, options));
                loadBitmap(R.drawable.merhi_car, mImageView);
                break;
            case 17:
                // imagen.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.sebastian_vettel_car, options));
                loadBitmap(R.drawable.sebastian_vettel_car, mImageView);
                break;
            case 18:
                //  imagen.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.sergio_perez_car, options));
                loadBitmap(R.drawable.sergio_perez_car, mImageView);

                break;
            case 19:
                //imagen.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.valtteri_bottas_car, options));
                loadBitmap(R.drawable.valtteri_bottas_car, mImageView);
                break;
            case 20:
                //imagen.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.will_stevens_car, options));
                loadBitmap(R.drawable.will_stevens_car, mImageView);
                break;
            default:
                break;

        }

        piloto = new Piloto(Integer.parseInt(id),nombre,numero,equipo,nacionalidad,fecha_nacimiento,campeonatos,grands_prix,podiums);
        createdetailpiloto(piloto);

    }

    public void createdetailpiloto(Piloto piloto){
        descripcion_nombre_piloto.setText(piloto.getNombre());
        descripcion_numero_piloto.setText(piloto.getNumero());
        descripcion_equipo_piloto.setText(piloto.getEquipo());
        descripcion_nacionalidad_piloto.setText(piloto.getNacionalidad());
        descripcion_fecha_nacimiento_piloto.setText(piloto.getFecha_nacimiento());
        descripcion_campeonatos_piloto.setText(piloto.getCampeonatos());
        descripcion_grands_prix_piloto.setText(piloto.getGrands_prix());
        descripcion_podiums_piloto.setText(piloto.getPodiums());
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
            final Bitmap bitmap = decodeSampledBitmapFromResource(getResources(), params[0], 300, 200);
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