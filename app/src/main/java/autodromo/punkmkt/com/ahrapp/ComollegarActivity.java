package autodromo.punkmkt.com.ahrapp;

/**
 * Created by sebastianmendezgiron on 30/09/15.
 */
import android.app.Dialog;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


import java.util.ArrayList;

import autodromo.punkmkt.com.ahrapp.models.Coordenada;

public class ComollegarActivity extends FragmentActivity {

    ArrayList<Coordenada> recolecciones;
    ArrayList<Coordenada> taxis;
    ArrayList<Coordenada> estaciones_metro;
    static final LatLng AUTODROMO_HNOZ_RODRIGUEZ = new LatLng(19.404514,-99.0892086);
    GoogleMap googleMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comollegar);

        //check if Google play services is available
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

        // Showing status
        if(status!= ConnectionResult.SUCCESS){
            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();
        }
        else{
            // Getting reference to the SupportMapFragment
            SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

            googleMap = fm.getMap();
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            LatLng centerLatLng = AUTODROMO_HNOZ_RODRIGUEZ;
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(centerLatLng, 14);
            googleMap.moveCamera(cameraUpdate);
            // Enabling MyLocation Layer of Google Map
            googleMap.setMyLocationEnabled(true);

            //add overlay
            if (googleMap != null) {
                Marker AHZ = googleMap.addMarker(new MarkerOptions().position(AUTODROMO_HNOZ_RODRIGUEZ)
                        .title("Autodromo Hermanos Rodriguez"));
                AHZ.showInfoWindow();


            }

            MostrarMarcadoresRecoleccionWitoutView();
        }
    }

    public void RemoveMarkers(){
        googleMap.clear();
    }

    public void MostrarMarcadorAhd(View v) {
        Log.i("Marcador","MostrarAhd");
        RemoveMarkers();
        MostrarMarcadorAHZ();

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(AUTODROMO_HNOZ_RODRIGUEZ, 14);
        googleMap.moveCamera(cameraUpdate);

    }
    public void MostrarMarcadoresRecoleccion(View v){
        RemoveMarkers();
        MostrarMarcadoresRecoleccionWitoutView();

    }
    public void MostrarMarcadoresRecoleccionWitoutView(){
        recolecciones = new ArrayList<Coordenada>();
        recolecciones.add(new Coordenada(19.407169, -99.081305, "Recoleccion1"));
        recolecciones.add(new Coordenada(19.410875, -99.095178, "Recoleccion2"));
        MostrarMarcadorAHZ();
        for(Coordenada recoleccion : recolecciones ){
            MarkerOptions options = new MarkerOptions();
            options.title(recoleccion.getTitulo());
            LatLng point = new LatLng(recoleccion.getLatitud(), recoleccion.getLongitud());
            options.position(point);
            options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
            googleMap.addMarker(options);
            //googleMap.addMarker(new MarkerOptions().position(new LatLng(estacion.getLatitud(), estacion.getLongitud())).title(estacion.getTitulo()));
        }

    }
    public void MostrarMarcadoresTaxis(View v){
        RemoveMarkers();
        taxis = new ArrayList<Coordenada>();
        taxis.add(new Coordenada(19.407169, -99.081305, "Taxi1"));
        taxis.add(new Coordenada(19.410875, -99.095178, "Taxi2"));
        MostrarMarcadorAHZ();
        for(Coordenada taxi : taxis ){
            MarkerOptions options = new MarkerOptions();
            options.title(taxi.getTitulo());
            LatLng point = new LatLng(taxi.getLatitud(), taxi.getLongitud());
            options.position(point);
            options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
            googleMap.addMarker(options);
            //googleMap.addMarker(new MarkerOptions().position(new LatLng(estacion.getLatitud(), estacion.getLongitud())).title(estacion.getTitulo()));

        }
    }

    public void MostrarMarcadoresEstacionesMetro(View v){
        RemoveMarkers();
        estaciones_metro = new ArrayList<Coordenada>();
        estaciones_metro.add(new Coordenada(19.407169, -99.081305, "Estacion1"));
        estaciones_metro.add(new Coordenada(19.410875, -99.095178,"Estacion2"));
        MostrarMarcadorAHZ();
        for(Coordenada estacion : estaciones_metro ){
            MarkerOptions options = new MarkerOptions();
            options.title(estacion.getTitulo());
            LatLng point = new LatLng(estacion.getLatitud(), estacion.getLongitud());
            options.position(point);
            options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
            googleMap.addMarker(options);
            //googleMap.addMarker(new MarkerOptions().position(new LatLng(estacion.getLatitud(), estacion.getLongitud())).title(estacion.getTitulo()));

        }
    }
    public void MostrarMarcadorAHZ(){
        Marker AHZ = googleMap.addMarker(new MarkerOptions().position(AUTODROMO_HNOZ_RODRIGUEZ)
                .title("Autodromo Hermanos Rodriguez"));
        AHZ.showInfoWindow();
    }

}
