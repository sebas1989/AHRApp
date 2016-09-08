package autodromo.punkmkt.com.ahrapp.adapters;

/**
 * Created by sebastianmendezgiron on 25/09/15.
 */
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import autodromo.punkmkt.com.ahrapp.BaseActivity;
import autodromo.punkmkt.com.ahrapp.PilotosDetalleActivity;
import autodromo.punkmkt.com.ahrapp.R;
import autodromo.punkmkt.com.ahrapp.models.Piloto;
import autodromo.punkmkt.com.ahrapp.utils.BitmapManager;
import autodromo.punkmkt.com.ahrapp.utils.RecyclingBitmapDrawable;


import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by germanpunk on 20/09/15.
 */
public class PilotosAdapter extends RecyclerView.Adapter<PilotosAdapter.PilotoViewHolder> {
    private List<Piloto> items;
    public Context context;
    Resources r;
    public final static Integer[] imageResIds = new Integer[] {
            R.drawable.alexander_rossi_pilot, R.drawable.carlos_sainz_pilot, R.drawable.daniel_ricciardo_pilot,
            R.drawable.daniil_kvyat_pilot, R.drawable.felipe_massa_pilot, R.drawable.felipe_nassar_pilot,
            R.drawable.fernando_alonso_pilot, R.drawable.jenson_button_pilot, R.drawable.kimi_rnikkinen_pilot,
            R.drawable.lewis_hamilton_pilot, R.drawable.marcus_ericsson_pilot, R.drawable.max_verstappen_pilot,
            R.drawable.nico_hulkenberg_pilot, R.drawable.nico_rosberg_pilot, R.drawable.pastor_maldonado_pilot,
            R.drawable.romain_grosjean_pilot, R.drawable.merhi_pilot, R.drawable.sebastian_vettel_pilot,
            R.drawable.sergio_perez_pilot, R.drawable.valtteri_bottas_pilot,  R.drawable.will_stevens_pilot
            };

    public static class PilotoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView nombre;
        public ImageView imagen;
        public IMyViewHolderClicks mListener;
        public PilotoViewHolder(View v,IMyViewHolderClicks listener) {
            super(v);
            mListener = listener;
            imagen = (ImageView) v.findViewById(R.id.netork_imageView);
            nombre = (TextView) v.findViewById(R.id.name);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.onPotato(v,getLayoutPosition());
        }
        public static interface IMyViewHolderClicks {
            public void onPotato(View caller, int i);
            // public void onTomato(ImageView callerImage, int i);
        }
    }

    public PilotosAdapter(List<Piloto> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public PilotosAdapter.PilotoViewHolder onCreateViewHolder(final ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_piloto, viewGroup, false);

        PilotosAdapter.PilotoViewHolder vh = new PilotoViewHolder(v, new PilotosAdapter.PilotoViewHolder.IMyViewHolderClicks() {

            public void onPotato(View caller, int i) {
                Piloto piloto  = items.get(i);
                Intent Idetail = new Intent (viewGroup.getContext(), PilotosDetalleActivity.class);
                Idetail.putExtra("id", Integer.toString(piloto.getId()));
                Idetail.putExtra("nombre", piloto.getNombre());
                Idetail.putExtra("image", piloto.getFoto());
                Idetail.putExtra("equipo", piloto.getEquipo());
                Idetail.putExtra("numero", piloto.getNumero());
                Idetail.putExtra("nacionalidad", piloto.getNacionalidad());
                Idetail.putExtra("fecha_nacimiento", piloto.getFecha_nacimiento());
                Idetail.putExtra("campeonatos", piloto.getCampeonatos());
                Idetail.putExtra("grands_prix", piloto.getGrands_prix());
                Idetail.putExtra("podiums", piloto.getPodiums());
                viewGroup.getContext().startActivity(Idetail);
            };

        });
        return vh;
    }

    @Override
    public void onBindViewHolder(PilotoViewHolder viewHolder, int i) {

        loadBitmap(imageResIds[i], viewHolder.imagen);
        viewHolder.nombre.setText(items.get(i).getNombre());

        //viewHolder.imagen.setImageBitmap(items.get(i).getFotob());
    }

    public void loadBitmap(int resId, ImageView imageView) {
        if (cancelPotentialWork(resId, imageView)) {
            Bitmap bitmap;
             bitmap =  BitmapManager.decodeSampledBitmapFromResource(context.getResources(), R.drawable.descarga, 100, 100);
            final BitmapWorkerTask task = new BitmapWorkerTask(imageView);
            final AsyncDrawable asyncDrawable = new AsyncDrawable(context.getResources(),bitmap, task);
            imageView.setImageDrawable(asyncDrawable);
            task.execute(resId);
          //  new RecyclingBitmapDrawable(context.getResources(),bitmap);
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
            final Bitmap bitmap = decodeSampledBitmapFromResource(context.getResources(), params[0], 170, 160);
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
