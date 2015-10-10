package autodromo.punkmkt.com.ahrapp;

/**
 * Created by sebastianmendezgiron on 25/09/15.
 */
import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import autodromo.punkmkt.com.ahrapp.models.Piloto;

public class PilotosDetalleActivity extends Activity {
    Piloto piloto;
    ImageView imagen;
    TextView descripcion_nombre_piloto;
    TextView descripcion_numero_piloto;
    TextView descripcion_equipo_piloto;
    TextView descripcion_nacionalidad_piloto;
    TextView descripcion_fecha_nacimiento_piloto;
    TextView descripcion_campeonatos_piloto;
    TextView descripcion_grands_prix_piloto;
    TextView descripcion_podiums_piloto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilotos_detalle);
        imagen = (ImageView) findViewById(R.id.imagen_principal);
        descripcion_nombre_piloto = (TextView) findViewById(R.id.descripcion_nombre_piloto);
        descripcion_numero_piloto = (TextView) findViewById(R.id.descripcion_numero_piloto);
        descripcion_equipo_piloto = (TextView) findViewById(R.id.descripcion_equipo_piloto);
        descripcion_nacionalidad_piloto = (TextView) findViewById(R.id.descripcion_nacionalidad_piloto);
        descripcion_fecha_nacimiento_piloto = (TextView) findViewById(R.id.descripcion_fecha_nacimiento_piloto);
        descripcion_campeonatos_piloto = (TextView) findViewById(R.id.descripcion_campeonatos_piloto);
        descripcion_grands_prix_piloto = (TextView) findViewById(R.id.descripcion_grandsprix_piloto);
        descripcion_podiums_piloto = (TextView) findViewById(R.id.descripcion_podiums_piloto);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String nombre = intent.getStringExtra("nombre");
        String image = intent.getStringExtra("image");
        String numero = intent.getStringExtra("numero");
        String equipo = intent.getStringExtra("equipo");
        String nacionalidad = intent.getStringExtra("nacionalidad");
        String fecha_nacimiento = intent.getStringExtra("fecha_nacimiento");
        //String lugar_nacimiento = intent.getStringExtra("lugar_nacimiento");
        String campeonatos = intent.getStringExtra("campeonatos");
        String grands_prix = intent.getStringExtra("grands_prix");
        String podiums = intent.getStringExtra("podiums");

        final BitmapFactory.Options options = new BitmapFactory.Options();
        //options.inJustDecodeBounds=true;
        options.inSampleSize = 2;
        switch (Integer.parseInt(id)){
            case 0:
                imagen.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.alexander_rossi_car,options));
                break;
            case 1:
                imagen.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.carlos_sainz_car, options));
                break;
            case 2:
                imagen.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.daniel_ricciardo_car, options));
                break;
            case 3:
                imagen.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.daniil_kvyat_car, options));
                break;
            case 4:
                imagen.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.felipe_massa_car, options));
                break;
            case 5:
                imagen.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.felipe_nassar_car, options));
                break;
            case 6:
                imagen.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.fernando_alonso_car, options));
                break;
            case 7:
                imagen.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.jenson_button_car, options));
                break;
            case 8:
                imagen.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.kimi_rnikkinen_car, options));
                break;
            case 9:
                imagen.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.lewis_hamilton_car, options));
                break;
            case 10:
                imagen.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.marcus_ericsson_car, options));
                break;
            case 11:
                imagen.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.max_verstappen_car, options));
                break;
            case 12:
                imagen.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.nico_hulkenberg_car, options));
                break;
            case 13:
                imagen.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.rico_rosberg_car, options));
                break;
            case 14:
                imagen.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.pastor_maldonado_car, options));
                break;
            case 15:
                imagen.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.romain_grosjean_car, options));

                break;
            case 16:
                imagen.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.sebastian_vettel_car, options));
                break;
            case 17:
                imagen.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.sergio_perez_car, options));

                break;
            case 18:
                imagen.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.valtteri_bottas_car, options));
                break;
            case 19:
                imagen.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.will_stevens_car, options));
                break;
            default:
                break;
        }

        piloto = new Piloto(Integer.parseInt(id),nombre,numero,equipo,nacionalidad,fecha_nacimiento,campeonatos,grands_prix,podiums);
        createdetailpiloto(piloto);

    }

    public void createdetailpiloto(Piloto piloto){
        descripcion_nombre_piloto.setText(piloto.getNombre());
        descripcion_numero_piloto.setText(piloto.getNumero());
        descripcion_equipo_piloto.setText(piloto.getEquipo());
        descripcion_nacionalidad_piloto.setText(piloto.getNacionalidad());
        descripcion_fecha_nacimiento_piloto.setText(piloto.getFecha_nacimiento());
        descripcion_campeonatos_piloto.setText(piloto.getCampeonatos());
        descripcion_grands_prix_piloto.setText(piloto.getGrands_prix());
        descripcion_podiums_piloto.setText(piloto.getPodiums());
    }

}