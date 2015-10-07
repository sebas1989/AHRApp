package autodromo.punkmkt.com.ahrapp;

/**
 * Created by sebastianmendezgiron on 30/09/15.
 */
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapActivity extends FragmentActivity {
    GoogleMap googleMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Getting reference to the SupportMapFragment
        SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        // Getting GoogleMap object from the fragmentpremios
        googleMap = fm.getMap();
        Intent intent = getIntent();
        try {
            String latitud = intent.getStringExtra("latitud_mapa");
            String longitud = intent.getStringExtra("longitud_mapa");
            String titulo = intent.getStringExtra("titulo");
            String ubicacion = intent.getStringExtra("ubicacion");
            String textoCompleto = titulo + ": \n" + ubicacion;
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

            LatLng centerLatLng = new LatLng(Float.parseFloat(latitud),Float.parseFloat(longitud));
            CameraUpdate cameraUpdate  = CameraUpdateFactory.newLatLngZoom(centerLatLng, 10);
            googleMap.moveCamera(cameraUpdate);
            googleMap.setMyLocationEnabled(true);
            googleMap.addMarker(new MarkerOptions().position(centerLatLng).title(textoCompleto)).showInfoWindow();

        } catch (Throwable e) {
            e.printStackTrace();
            Log.e("Debug", "Iniciar throwable");
        }
    }

}
