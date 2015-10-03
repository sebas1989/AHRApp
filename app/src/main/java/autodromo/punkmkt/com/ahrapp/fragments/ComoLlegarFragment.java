package autodromo.punkmkt.com.ahrapp.fragments;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

import autodromo.punkmkt.com.ahrapp.R;
import autodromo.punkmkt.com.ahrapp.utils.TouchImageView;

/**
 * Created by sebastianmendezgiron on 01/10/15.
 */
public class ComoLlegarFragment extends Fragment{


    private TouchImageView imagen;
    private TextView scrollPositionTextView;
    private TextView zoomedRectTextView;
    private TextView currentZoomTextView;
    private DecimalFormat df;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.como_llegar_activity,container,false);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        imagen = (TouchImageView) getActivity().findViewById(R.id.mapaGeneral);
        //Bitmap sourceImg = decodeSampledBitmapFromResource(getResources(), R.drawable.mapa_general, 100, 100);
        //imagen.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.mapa_general, 500, 200));
        imagen.setImageResource(R.drawable.mapa_general);
        try {
            imagen.setOnTouchImageViewListener(new TouchImageView.OnTouchImageViewListener(){
                @Override
                public void onMove() {
                    PointF point = imagen.getScrollPosition();
                    RectF rect = imagen.getZoomedRect();
                    float currentZoom = imagen.getCurrentZoom();
                    boolean isZoomed = imagen.isZoomed();
                }
            });
        }catch (Exception e){

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
