package autodromo.punkmkt.com.ahrapp.fragments;

import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import autodromo.punkmkt.com.ahrapp.ComollegarActivity;
import autodromo.punkmkt.com.ahrapp.R;
import autodromo.punkmkt.com.ahrapp.ServiciosActivity;

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

        IniciarActivityEventos();
        IniciarUbicaTuZona();
        iniciarServicios();
    }


    public void IniciarActivityEventos(){
        Button button = (Button) getActivity().findViewById(R.id.como_llegar);
        // add button listener
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent myIntent = new Intent(getActivity(), ComollegarActivity.class);
                getActivity().startActivity(myIntent);
            }
        });
    }

    public void IniciarUbicaTuZona(){
        Button button = (Button) getActivity().findViewById(R.id.ubica_tu_zona);
        // add button listener
        button.setOnClickListener(new View.OnClickListener() {
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
        Button button = (Button) getActivity().findViewById(R.id.servicios);
        // add button listener
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent myIntent = new Intent(getActivity(), ServiciosActivity.class);
                getActivity().startActivity(myIntent);
            }
        });
    }
}
