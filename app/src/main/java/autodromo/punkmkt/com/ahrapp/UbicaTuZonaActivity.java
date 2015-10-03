package autodromo.punkmkt.com.ahrapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

/**
 * Created by sebastianmendezgiron on 01/10/15.
 */
public class UbicaTuZonaActivity extends FragmentActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.como_llegar_activity);

        ImageView imagen = (ImageView) findViewById(R.id.mapaGeneral);
        imagen.setImageResource(R.drawable.mapa_general);
    }
}

