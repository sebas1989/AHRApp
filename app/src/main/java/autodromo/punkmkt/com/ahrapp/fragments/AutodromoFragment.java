package autodromo.punkmkt.com.ahrapp.fragments;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import com.uber.sdk.android.rides.RideParameters;
import com.uber.sdk.android.rides.RideRequestActivity;
import com.uber.sdk.android.rides.RideRequestActivityBehavior;
import com.uber.sdk.android.core.UberSdk;
import com.uber.sdk.android.rides.RideRequestButton;
import com.uber.sdk.core.auth.Scope;
import com.uber.sdk.rides.client.SessionConfiguration;

import java.util.ArrayList;
import java.util.Arrays;

import autodromo.punkmkt.com.ahrapp.BaseActivity;
import autodromo.punkmkt.com.ahrapp.ComollegarActivity;
import autodromo.punkmkt.com.ahrapp.MyVolleySingleton;
import autodromo.punkmkt.com.ahrapp.R;
import autodromo.punkmkt.com.ahrapp.SettingsActivity;
import autodromo.punkmkt.com.ahrapp.UberActivity;


import com.uber.sdk.core.auth.AccessToken;
import com.uber.sdk.rides.client.ServerTokenSession;
import com.uber.sdk.rides.client.SessionConfiguration;
import com.uber.sdk.rides.client.error.ApiError;

/**
 * Created by sebastianmendezgiron on 30/09/15.
 */
public class AutodromoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_autodromo,container,false);
        return v;

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Tracker tracker = ((MyVolleySingleton) getActivity().getApplication()).getTracker(MyVolleySingleton.TrackerName.APP_TRACKER);
        tracker.setScreenName(getString(R.string.menu_autodromo));
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
        IniciarActivityEventos();
        IniciarUbicaTuZona();
        iniciarServicios();
        iniciarUber();
    }

    public void IniciarActivityEventos(){
        ImageView mImageView = (ImageView) getActivity().findViewById(R.id.como_llegar);
        ((BaseActivity) getActivity()).loadBitmap(R.drawable.pista_location, mImageView,350,179);
        // add button listener
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent myIntent = new Intent(getActivity(), ComollegarActivity.class);
                getActivity().startActivity(myIntent);
            }
        });
    }

    public void IniciarUbicaTuZona(){
        ImageView mImageView = (ImageView) getActivity().findViewById(R.id.ubica_tu_zona);
        ((BaseActivity) getActivity()).loadBitmap(R.drawable.pista_sit, mImageView,350,179);
        // add button listener
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Fragment fP = new ComoLlegarFragment();
                android.support.v4.app.FragmentTransaction ftP = getFragmentManager().beginTransaction();
                ftP.replace(R.id.frame, fP); // f1_container is your FrameLayout container
                ftP.setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ftP.addToBackStack(null);
                ftP.commit();
            }
        });
    }

    public void iniciarServicios(){

        ImageView mImageView = (ImageView) getActivity().findViewById(R.id.servicios);

        ((BaseActivity) getActivity()).loadBitmap(R.drawable.pista_servicios, mImageView,350,179);
        // add button listener
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Fragment fP = new ServiciosFragment();
                android.support.v4.app.FragmentTransaction ftP = getFragmentManager().beginTransaction();
                ftP.replace(R.id.frame, fP); // f1_container is your FrameLayout container
                ftP.setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ftP.addToBackStack(null);
                ftP.commit();
            }
        });
    }

    public void iniciarUber(){

        ImageView mImageView = (ImageView) getActivity().findViewById(R.id.uber);

        ((BaseActivity) getActivity()).loadBitmap(R.drawable.pista_sit, mImageView,350,179);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Fragment fP = new UberFragment();
                android.support.v4.app.FragmentTransaction ftP = getFragmentManager().beginTransaction();
                ftP.replace(R.id.frame, fP); // f1_container is your FrameLayout container
                ftP.setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ftP.addToBackStack(null);
                ftP.commit();
                /*Intent myIntent = new Intent(getActivity(), UberActivity.class);
                getActivity().startActivity(myIntent);*/
            }
        });
    }
}
