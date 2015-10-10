package autodromo.punkmkt.com.ahrapp.fragments;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import android.support.v4.app.Fragment;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;

import autodromo.punkmkt.com.ahrapp.R;
import autodromo.punkmkt.com.ahrapp.SinglePassionActivity;
import autodromo.punkmkt.com.ahrapp.adapters.CustomGrid;

/**
 * Created by sebastianmendezgiron on 25/09/15.
 */
public class PassionFragment extends Fragment {

    int[] imageId={
            R.drawable.alonso,
            R.drawable.bottas,
            R.drawable.button,
            R.drawable.checo,
            R.drawable.ericcson,
            R.drawable.grosjean,
            R.drawable.hamilton,
            R.drawable.hulkenberg,
            R.drawable.kimi,
            R.drawable.kvyat,
            R.drawable.massa,
            R.drawable.mexico_1,
            R.drawable.mexico_2,
            R.drawable.nasr,
            R.drawable.pastor,
            R.drawable.ricciardo,
            R.drawable.rosberg,
            R.drawable.rossi,
            R.drawable.sainz,
            R.drawable.stevens,
            R.drawable.verstappen,
            R.drawable.vettel,
    };

    ArrayList <Bitmap> bitmaps = new ArrayList<Bitmap>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.passion_activity,container,false);
        return v;

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;

        bitmaps.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.alonso, options));
        bitmaps.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.bottas, options));
        bitmaps.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.button, options));
        bitmaps.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.checo, options));
        bitmaps.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.ericcson, options));
        bitmaps.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.grosjean, options));
        bitmaps.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.hamilton, options));
        bitmaps.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.hulkenberg, options));
        bitmaps.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.kimi, options));
        bitmaps.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.kvyat, options));
        bitmaps.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.massa, options));
        bitmaps.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.mexico_1, options));
        bitmaps.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.mexico_2, options));
        bitmaps.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.nasr, options));
        bitmaps.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.pastor, options));
        bitmaps.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.ricciardo, options));
        bitmaps.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.rosberg, options));
        bitmaps.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.rossi, options));
        bitmaps.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.sainz, options));
        bitmaps.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.stevens, options));
        bitmaps.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.verstappen, options));
        bitmaps.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.vettel, options));


        GridView grid = (GridView) getActivity().findViewById(R.id.grid_ecards);
        CustomGrid adapter = new CustomGrid(getActivity(), bitmaps);
            grid.setAdapter(adapter);
        grid.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {

                Intent detailIntent = new Intent(getActivity(), SinglePassionActivity.class);
                detailIntent.putExtra("imageId", imageId[position]);
                detailIntent.putExtra("position", position);
                startActivity(detailIntent);

            }

        });

    }




}
