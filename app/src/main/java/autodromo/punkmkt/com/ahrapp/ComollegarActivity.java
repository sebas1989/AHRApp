package autodromo.punkmkt.com.ahrapp;

/**
 * Created by sebastianmendezgiron on 30/09/15.
 */
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
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

public class ComollegarActivity extends FragmentActivity implements GoogleMap.OnMarkerClickListener{
    Marker marker;
    ActionBar actionBar;
    ArrayList<Coordenada> recolecciones;
    ArrayList<Coordenada> taxis;
    ArrayList<Coordenada> estaciones_metro;
    static final LatLng AUTODROMO_HNOZ_RODRIGUEZ = new LatLng(19.404514,-99.0892086);
    GoogleMap googleMap;
    TextView lugartitulo, lugardesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Tracker tracker = ((MyVolleySingleton) getApplication()).getTracker(MyVolleySingleton.TrackerName.APP_TRACKER);
        tracker.setScreenName(getString(R.string.como_llegar_mapa));
        tracker.send(new HitBuilders.ScreenViewBuilder().build());

        setContentView(R.layout.activity_comollegar);

        //check if Google play services is available
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

        // Showing status
        if(status!= ConnectionResult.SUCCESS){
            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();
        }/*
        else{
            // Getting reference to the SupportMapFragment
            SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

            googleMap = fm.getMap();
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            LatLng centerLatLng = AUTODROMO_HNOZ_RODRIGUEZ;
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(centerLatLng, 10);
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
        }*/

    }

    public void RemoveMarkers(){
        googleMap.clear();
    }

    public void MostrarMarcadorAhd(View v) {
        RemoveMarkers();
        MostrarMarcadorAHZ();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(AUTODROMO_HNOZ_RODRIGUEZ, 5);
        googleMap.moveCamera(cameraUpdate);
    }
    public void MostrarMarcadoresRecoleccion(View v){
        RemoveMarkers();
        MostrarMarcadoresRecoleccionWitoutView();
    }
    public void MostrarMarcadoresRecoleccionWitoutView(){

        Button ticket = (Button) findViewById(R.id.recoleccion);
        ticket.setBackground(getResources().getDrawable(R.drawable.ticket_icon_hover_96));
        Button metro = (Button) findViewById(R.id.taxis);
        metro.setBackground(getResources().getDrawable(R.drawable.train_icon));
        Button metrobus = (Button) findViewById(R.id.metro);
        metrobus.setBackground(getResources().getDrawable(R.drawable.taxi_icon));
        recolecciones = new ArrayList<Coordenada>();
        recolecciones.add(new Coordenada(19.525688, -99.226925, "MUNDO E", "Horario de salida: 7:00 hrs\n Horario de regreso: Salida cada media hora a partir de las 13:00 hasta las 16:00 hrs.\n Domingo: Horario de regreso: Salida cada media hora a partir de las 15:30 hasta las 19:00 hrs\n *Se deja de vender 2 días antes del evento. No hay entrega de Will Call.\n Punto de partida: Blvd. Manuel Ávila Camacho No 1007. Salida por Camino Antiguo a Santa Mónica s/n Col. Bellavista, frente a Gayoso."));
        recolecciones.add(new Coordenada(19.363066, -99.273070, "SANTA FE", "Horario de salida: 7:00 hrs \n Horario de regreso: Salida cada media hora a partir de las 13:00 hasta las 16:00 hrs.\n Domingo: Horario de regreso: Salida cada media hora a partir de las 15:30 hasta las 19:00 hrs\n *Se deja de vender 2 días antes del evento. No hay entrega de Will Call.\n  Punto de partida: Av. Vasco de Quiroga 3800 salida en la caseta de acceso al estacionamiento del Palacio de Hierro."));
        recolecciones.add(new Coordenada(19.301994, -99.123284, "GALERÍAS COAPA", "Horario de salida: 7:00 hrs \n Horario de regreso: Salida cada media hora a partir de las 13:00 hasta las 16:00 hrs.\n Domingo: Horario de regreso: Salida cada media hora a partir de las 15:30 hasta las 19:00 hrs\n *Se deja de vender 2 días antes del evento. No hay entrega de Will Call.\n Punto de partida: Calz. Del Hueso 519 Col. Residencial Acoxpa, salida sobre Calz De Los Tenorios, delegación Coyoacán."));
        recolecciones.add(new Coordenada(19.490944, -99.133967, "PLAZA LINDAVISTA", "Horario de salida: 7:00 hrs\n Horario de regreso: Salida cada media hora a partir de las 13:00 hasta las 16:00 hrs.\n Domingo: Horario de regreso: Salida cada media hora a partir de las 15:30 hasta las 19:00 hrs\n *Se deja de vender 2 días antes del evento. No hay entrega de Will Call.\n Punto de partida: Instituto Politécnico Nacional y eje 5 Montevideo, Col. Lindavista salida sobre Av. Politécnico."));
        recolecciones.add(new Coordenada(19.304524, -99.189463, "PERISUR", "Horario de salida: 7:00 hrs\n Horario de regreso: Salida cada media hora a partir de las 13:00 hasta las 16:00 hrs.\n Domingo: Horario de regreso: Salida cada media hora a partir de las 15:30 hasta las 19:00 hrs\n *Se deja de vender 2 días antes del evento. No hay entrega de Will Call.\n Punto de partida: Periférico sur 4660, Col. Ampliación Pedregal. Salida Sobre calle Zacatepetl a un costado del centro comercial."));
        recolecciones.add(new Coordenada(19.438452, -99.222613, "HIPÓDROMO", "Horario de salida: 7:00 hrs\n Horario de regreso: Salida cada media hora a partir de las 13:00 hasta las 16:00 hrs.\n Domingo: Horario de regreso: Salida cada media hora a partir de las 15:30 hasta las 19:00 hrs\n *Se deja de vender 2 días antes del evento. No hay entrega de Will Call.\n Punto de partida: Hipódromo de las América Puerta 1, Av. Industria Militar s/n. Col. Lomas de Sotelo. Salida en la Glorieta del Caballito."));
        recolecciones.add(new Coordenada(19.402968, -99.242663, "BOSQUE DE LAS LOMAS", "Horario de salida: 7:00 hrs\n Horario de regreso: Salida cada media hora a partir de las 13:00 hasta las 16:00 hrs.\n Domingo: Horario de regreso: Salida cada media hora a partir de las 15:30 hasta las 19:00 hrs\n *Se deja de vender 2 días antes del evento. No hay entrega de Will Call.\n Punto de partida: Bosque de Durazno 39 col. Bosque de las Lomas, salida en Bosques de Ciruelos frente al valet parking."));
        MostrarMarcadorAHZ();

        for(Coordenada recoleccion : recolecciones ){
            MarkerOptions options = new MarkerOptions();
            options.title(recoleccion.getTitulo());
            options.snippet(recoleccion.getDescripcion());
            LatLng point = new LatLng(recoleccion.getLatitud(), recoleccion.getLongitud());
            options.position(point);
            options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
            googleMap.addMarker(options);
            googleMap.setOnMarkerClickListener(this);
        }

    }

    public void MostrarMarcadoresEstacionesMetro(View v){
        Button metro = (Button) findViewById(R.id.taxis);
        metro.setBackground(getResources().getDrawable(R.drawable.train_icon_hover_96));
        Button ticket = (Button) findViewById(R.id.recoleccion);
        ticket.setBackground(getResources().getDrawable(R.drawable.ticket_bus));
        Button metrobus = (Button) findViewById(R.id.metro);
        metrobus.setBackground(getResources().getDrawable(R.drawable.taxi_icon));

        RemoveMarkers();
        estaciones_metro = new ArrayList<Coordenada>();
        estaciones_metro.add(new Coordenada(19.407126, -99.082267, "METRO PUEBLA", ""));
        estaciones_metro.add(new Coordenada(19.408185, -99.091366, "METRO CIUDAD DEPORTIVA", ""));
        estaciones_metro.add(new Coordenada(19.408610, -99.103175, "METRO VELÓDROMO", ""));
        MostrarMarcadorAHZ();
        for(Coordenada estacion : estaciones_metro ){
            MarkerOptions options = new MarkerOptions();
            options.title(estacion.getTitulo());
            LatLng point = new LatLng(estacion.getLatitud(), estacion.getLongitud());
            options.position(point);
            options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
            googleMap.addMarker(options);
        }
    }


    public void MostrarMarcadoresTaxis(View v){

        Button metrobus = (Button) findViewById(R.id.metro);
        metrobus.setBackground(getResources().getDrawable(R.drawable.taxi_icon_hover_96));
        Button metro = (Button) findViewById(R.id.taxis);
        metro.setBackground(getResources().getDrawable(R.drawable.train_icon));
        Button ticket = (Button) findViewById(R.id.recoleccion);
        ticket.setBackground(getResources().getDrawable(R.drawable.ticket_bus));

        RemoveMarkers();
        taxis = new ArrayList<Coordenada>();
        taxis.add(new Coordenada(19.393807, -99.090382, "UPIICSA", ""));
        taxis.add(new Coordenada(19.396575, -99.096079, "IZTACALCO", ""));
        MostrarMarcadorAHZ();

        for(Coordenada taxi : taxis ) {
            MarkerOptions options = new MarkerOptions();

            options.title(taxi.getTitulo());
            LatLng point = new LatLng(taxi.getLatitud(), taxi.getLongitud());
            options.position(point);
            options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
            googleMap.addMarker(options);

            googleMap.setOnMarkerClickListener(this);
        }
    }

    public void MostrarMarcadorAHZ(){
        Marker AHZ = googleMap.addMarker(new MarkerOptions().position(AUTODROMO_HNOZ_RODRIGUEZ)
                .title("Autodromo Hermanos Rodríguez"));
        AHZ.showInfoWindow();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        if (marker.getSnippet() != null){
            AlertDialog.Builder builder = new AlertDialog.Builder(ComollegarActivity.this);
            LayoutInflater inflater = (ComollegarActivity.this).getLayoutInflater();
            builder.setTitle(marker.getTitle());
            builder.setMessage(marker.getSnippet());
            builder.setView(inflater.inflate(R.layout.map_alert, null)).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                }
            });
            builder.create();
            builder.show();
            return true;
        }else {
            return false;
        }
    }

}
