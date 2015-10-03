package autodromo.punkmkt.com.ahrapp.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import autodromo.punkmkt.com.ahrapp.CiudadMexicoDetalleActivity;
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

        IniciarActivityHospedaje();
        IniciarActivityRestaurantes();
        IniciarActivityAdondeir();
        IniciarActivityEventos();
    }

    public void IniciarActivityHospedaje(){

        Button button = (Button) getActivity().findViewById(R.id.ir_hospedaje);

        // add button listener
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent myIntent = new Intent(getActivity(), CiudadMexicoDetalleActivity.class);
                myIntent.putExtra("fragment","hospedaje");
                getActivity().startActivity(myIntent);
            }
        });
    }

    public void IniciarActivityRestaurantes(){

        Button button = (Button) getActivity().findViewById(R.id.ir_restaurantes);

        // add button listener
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent myIntent = new Intent(getActivity(), CiudadMexicoDetalleActivity.class);
                myIntent.putExtra("fragment","restaurantes");
                getActivity().startActivity(myIntent);
            }
        });
    }

    public void IniciarActivityAdondeir(){

        Button button = (Button) getActivity().findViewById(R.id.ir_a_dondeir);

        // add button listener
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent myIntent = new Intent(getActivity(), CiudadMexicoDetalleActivity.class);
                myIntent.putExtra("fragment","adondeir");
                getActivity().startActivity(myIntent);
            }
        });
    }
    public void IniciarActivityEventos(){

        Button button = (Button) getActivity().findViewById(R.id.ir_eventos);

        // add button listener
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent myIntent = new Intent(getActivity(), CiudadMexicoDetalleActivity.class);
                myIntent.putExtra("fragment","eventos");
                getActivity().startActivity(myIntent);
            }
        });
    }

}
