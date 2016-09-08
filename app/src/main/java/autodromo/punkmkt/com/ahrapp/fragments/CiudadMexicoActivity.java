package autodromo.punkmkt.com.ahrapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import autodromo.punkmkt.com.ahrapp.BaseActivity;
import autodromo.punkmkt.com.ahrapp.CiudadMexicoDetalleActivity;
import autodromo.punkmkt.com.ahrapp.MyVolleySingleton;
import autodromo.punkmkt.com.ahrapp.R;

/**
 * Created by sebastianmendezgiron on 25/09/15.
 */
public class CiudadMexicoActivity extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.actividad_ciudad_mexico,container,false);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Tracker tracker = ((MyVolleySingleton) getActivity().getApplication()).getTracker(MyVolleySingleton.TrackerName.APP_TRACKER);
        tracker.setScreenName(getString(R.string.menu_cd_mexico));
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
        IniciarActivityHospedaje();
        IniciarActivityRestaurantes();
        IniciarActivityAdondeir();
    }

    public void IniciarActivityHospedaje(){

        ImageView mImageView = (ImageView) getActivity().findViewById(R.id.ir_hospedaje);
        ((BaseActivity) getActivity()).loadBitmap(R.drawable.hospedaje_select, mImageView,350,179);
        // add button listener
        mImageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent myIntent = new Intent(getActivity(), CiudadMexicoDetalleActivity.class);
                myIntent.putExtra("fragment","hospedaje");
                getActivity().startActivity(myIntent);
            }
        });
    }

    public void IniciarActivityRestaurantes(){
        ImageView mImageView = (ImageView) getActivity().findViewById(R.id.ir_restaurantes);
        ((BaseActivity) getActivity()).loadBitmap(R.drawable.restaurantes_select, mImageView,350,179);
        // add button listener
        mImageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent myIntent = new Intent(getActivity(), CiudadMexicoDetalleActivity.class);
                myIntent.putExtra("fragment","restaurantes");
                getActivity().startActivity(myIntent);
            }
        });
    }

    public void IniciarActivityAdondeir(){
        ImageView mImageView = (ImageView) getActivity().findViewById(R.id.ir_a_dondeir);
        ((BaseActivity) getActivity()).loadBitmap(R.drawable.donde_it_select, mImageView,350,179);
        // add button listener
        mImageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent myIntent = new Intent(getActivity(), CiudadMexicoDetalleActivity.class);
                myIntent.putExtra("fragment","adondeir");
                getActivity().startActivity(myIntent);
            }
        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                Intent intent = new Intent(getActivity(),BaseActivity.class);
                startActivity(intent);
                return true;
        }
        return false;
    }
}
