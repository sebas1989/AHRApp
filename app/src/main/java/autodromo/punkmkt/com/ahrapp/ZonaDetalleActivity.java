package autodromo.punkmkt.com.ahrapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import autodromo.punkmkt.com.ahrapp.models.Zona;

/**
 * Created by sebastianmendezgiron on 04/10/15.
 */
public class ZonaDetalleActivity extends Activity {
    Zona zona;
    static ImageView imagen;
    TextView nombre;
    TextView descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zona_detalle);

        imagen = (ImageView) findViewById(R.id.imagen_principal);
        nombre = (TextView) findViewById(R.id.nombreZona);
        descripcion = (TextView) findViewById(R.id.descripcionZona);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String nombre = intent.getStringExtra("nombre_completo");
        String descripcion = intent.getStringExtra("descripcion");
        String mapa = intent.getStringExtra("mapa");

        switch (Integer.parseInt(id)){
            case 0:
                imagen.setImageResource(R.drawable.area_verde_autodromo);
                break;
            case 1:
                imagen.setImageResource(R.drawable.area_azul_autodromo);
                break;
            case 2:
                imagen.setImageResource(R.drawable.area_morada_autodromo);
                break;
            case 3:
                imagen.setImageResource(R.drawable.area_amarilla_autodromo);
                break;
            case 4:
                imagen.setImageResource(R.drawable.area_naranja_autodromo);
                break;
            case 5:
                imagen.setImageResource(R.drawable.area_gris_autodromo);
                break;
            case 6:
                imagen.setImageResource(R.drawable.area_cafe_autodromo);
                break;
            default:
                break;
        }

        zona = new Zona(Integer.parseInt(id),"",nombre,descripcion,mapa);
        createDetailZona(zona);
    }

    public void createDetailZona(Zona zona){
        nombre.setText(zona.getNameCompleto());
        descripcion.setText(zona.getDescripcion());
    }
}
