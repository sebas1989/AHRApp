package autodromo.punkmkt.com.ahrapp.fragments;

import android.app.ActionBar;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import autodromo.punkmkt.com.ahrapp.R;
import autodromo.punkmkt.com.ahrapp.adapters.PilotosAdapter;
import autodromo.punkmkt.com.ahrapp.adapters.ZonasAdapter;
import autodromo.punkmkt.com.ahrapp.models.Piloto;
import autodromo.punkmkt.com.ahrapp.models.Zona;
import autodromo.punkmkt.com.ahrapp.utils.TouchImageView;

/**
 * Created by sebastianmendezgiron on 01/10/15.
 */
public class ComoLlegarFragment extends Fragment{

    private RecyclerView.Adapter adapter;
    private TouchImageView imagen;
    private TextView scrollPositionTextView;
    private TextView zoomedRectTextView;
    private TextView currentZoomTextView;
    private DecimalFormat df;

    private ArrayList<Zona> zonas = new ArrayList<Zona>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.como_llegar_activity,container,false);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        imagen = (TouchImageView) getActivity().findViewById(R.id.mapaGeneral);
        imagen.setImageResource(R.drawable.mapa_general);

        imagen.setOnTouchImageViewListener(new TouchImageView.OnTouchImageViewListener(){
            @Override
            public void onMove() {
            PointF point = imagen.getScrollPosition();
            RectF rect = imagen.getZoomedRect();
            float currentZoom = imagen.getCurrentZoom();
            boolean isZoomed = imagen.isZoomed();
            }
        });

        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        Zona z0 = new Zona(0, "VERDE",  "Zona Verde", "Acceso por P4, P5, P6 sobre Viaducto Río de la Piedad esquina con Circuito Interior Av. Río Churubusco. El metro más cercano es Cd. Deportiva (línea 9 del metro).", "area_verde_autodromo.jpg");
        Zona z1 = new Zona(1, "AZUL",   "Zona Azul",    "El ingreso es por P8, P9 Y P12 entre Viaducto Río de la Piedad  y Eje 4 Oriente. El metro más cercano es Puebla (línea 9 del metro).", "area_azul_autodromo.jpg");
        Zona z2 = new Zona(2, "MORADA", "Zona Morada",  "El ingreso es por P13 ubicada sobre Eje 4 Oriente, esquina con Eje 3 Sur Añil. La estación de metrobús más cercana a este acceso es UPIICSA.","area_morada_autodromo.jpg");
        Zona z3 = new Zona(3, "AMARILLA","Zona amarilla",   "El ingreso es por P14 ubicada sobre Eje 3 Sur Añil, esquina con Eje 4 Oriente. La estación de metrobús más cercana a este acceso es UPIICSA.","area_amarilla_autodromo");
        Zona z4 = new Zona(4, "NARANJA",    "Zona naranja","El ingreso es por P16 ubicada sobre Eje 3 Sur Añil, esquina con Resina.  La estación de metrobús más cercana a este acceso es Iztacalco.","area_naranja_autodromo.jpg");
        Zona z5 = new Zona(5, "GRIS","Zona Gris","El ingreso es por P1 del Palacio de los Deportes  sobre Circuito Interior Río Churubusco esquina con Eje 3 Sur Añil. El metro más cercano es Velódromo (línea 9 del metro).","area_gris_autodromo.jpg");
        Zona z6 = new Zona(6, "CAFÉ","Zona Café","Acceso por P1 del Palacio de los Deportes ubicada sobre Circuito Interior Río Churubusco, esquina con Eje 3 Sur Añil. El metro más cercano es Velódromo (línea 9 del metro).","area_cafe_autodromo.jpg");

        zonas.add(z0);
        zonas.add(z1);
        zonas.add(z2);
        zonas.add(z3);
        zonas.add(z4);
        zonas.add(z5);
        zonas.add(z6);
        adapter = new ZonasAdapter(zonas);
        //Por si quieren configurar algom como Grill solo cambian la linea de arriba por esta:
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }




}
