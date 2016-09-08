package autodromo.punkmkt.com.ahrapp.adapters;

/**
 * Created by sebastianmendezgiron on 29/09/15.
 */
/**
 * Created by germanpunk on 02/10/15.
 */
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import com.parse.ParseException;
import com.parse.ParsePush;
import com.parse.SaveCallback;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.lang.ref.WeakReference;

import autodromo.punkmkt.com.ahrapp.BaseActivity;
import autodromo.punkmkt.com.ahrapp.ConfiguracionActivity;
import autodromo.punkmkt.com.ahrapp.R;
import autodromo.punkmkt.com.ahrapp.databases.NotificationModel;
import autodromo.punkmkt.com.ahrapp.utils.BitmapManager;
import autodromo.punkmkt.com.ahrapp.utils.RecyclingBitmapDrawable;

/**
 * Created by sebastianmendezgiron on 29/09/15.
 */

public class ConfigurationPageAdapter extends PagerAdapter {
    Activity activity;
    Resources r;
    ImageView img2;
    ImageView img3;
    ImageView imageView;
    ImageView img4;
    ImageView img5;
    public ConfigurationPageAdapter(Resources r, Activity activity){
        this.activity = activity;
        this.r = r;
    }
    public int getCount() {
        return 5;
    }

    public Object instantiateItem(View collection, int position) {
        LayoutInflater inflater = (LayoutInflater) collection.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        int resId = 0;
        switch (position) {
            case 0:
                resId = R.layout.activity_tuto_one;
                break;
            case 1:
                resId = R.layout.activity_tuto_two;
                break;
            case 2:
                resId = R.layout.activity_tuto_three;
                break;
            case 3:
                resId = R.layout.activar_ubicacion;
                break;
            case 4:
                resId = R.layout.activar_notificaciones;
                break;
        }

        View view = inflater.inflate(resId, null);

        ((ViewPager) collection).addView(view);
        int width = 100;
        int height = 250;
        switch (position) {
            case 0:
                imageView = (ImageView) view.findViewById(R.id.tuto1);
               // imageView.setImageBitmap(BitmapManager.decodeSampledBitmapFromResource(r, R.drawable.home_tutorial_1, width, height));
                loadBitmap(R.drawable.home_tutorial_1, imageView);
                break;
            case 1:
                img2= (ImageView) view.findViewById(R.id.tuto2);
                //img2.setImageBitmap(BitmapManager.decodeSampledBitmapFromResource(r, R.drawable.home_tutorial_2, width, height));
                loadBitmap(R.drawable.home_tutorial_2, img2);
                break;
            case 2:
                img3= (ImageView) view.findViewById(R.id.tuto3);
                //img3.setImageBitmap(BitmapManager.decodeSampledBitmapFromResource(r, R.drawable.home_tutorial_3, width, height));
                loadBitmap(R.drawable.home_tutorial_3, img3);
                break;
            case 3:
                img4= (ImageView) view.findViewById(R.id.ubicacion);
                loadBitmap(R.drawable.bg_configuration1, img4);
                SwitchCompat mSwitchGPS = (SwitchCompat) view.findViewById(R.id.turnGPSOn);
                mSwitchGPS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            ConfiguracionActivity.onCheckedChanged(activity, isChecked);
                        }
                    }
                });

                break;
            case 4:
                img5= (ImageView) view.findViewById(R.id.notificacion);
                loadBitmap(R.drawable.configuracion_2, img5);
                SwitchCompat mSwitchGPSDefault = (SwitchCompat) view.findViewById(R.id.turnNotification);
                mSwitchGPSDefault.setChecked(true);
                final String nombre_notificacion = "notificaciones";
                try {
                    final NotificationModel notificacion = new Select().from(NotificationModel.class).where("name = " + "'" + nombre_notificacion + "'").querySingle();
                    int active = notificacion.getActive();
                    if (active == 1) {
                        ParsePush.unsubscribeInBackground(nombre_notificacion, new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {

                                    notificacion.setActive(0);
                                    notificacion.update();
                                } else {

                                }
                            }
                        });

                    }
                    else {
                        //update
                        ParsePush.subscribeInBackground(nombre_notificacion, new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    notificacion.setActive(1);
                                    notificacion.update();
                                } else {
                                }
                            }
                        });
                    }

                }
                catch (Throwable e) {

                    ParsePush.subscribeInBackground(nombre_notificacion, new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                NotificationModel noti = new NotificationModel();
                                noti.setName(nombre_notificacion);
                                noti.setActive(1);
                                noti.save();
                            } else {
                            }
                        }
                    });
                }
                break;
        }

        view.setTag("myview" + position);

        return view;
    }

    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView((View) arg2);

    }

    @Override
    public void finishUpdate(View arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == ((View) arg1);

    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public Parcelable saveState() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void startUpdate(View arg0) {
        // TODO Auto-generated method stub

    }


    public void loadBitmap(int resId, ImageView imageView) {
        if (cancelPotentialWork(resId, imageView)) {
            Bitmap bitmap;
            bitmap =  BitmapManager.decodeSampledBitmapFromResource(activity.getApplicationContext().getResources(), R.drawable.descarga, 100, 100);
            final BitmapWorkerTask task = new BitmapWorkerTask(imageView);
            final AsyncDrawable asyncDrawable = new AsyncDrawable(activity.getApplicationContext().getResources(),bitmap, task);
            imageView.setImageDrawable(asyncDrawable);
            task.execute(resId);
            new RecyclingBitmapDrawable(activity.getApplicationContext().getResources(),bitmap);
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
            final Bitmap bitmap = decodeSampledBitmapFromResource(activity.getApplicationContext().getResources(), params[0], 300, 400);
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