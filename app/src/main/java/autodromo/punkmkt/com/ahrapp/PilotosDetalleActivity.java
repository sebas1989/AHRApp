package autodromo.punkmkt.com.ahrapp;

/**
 * Created by sebastianmendezgiron on 25/09/15.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import java.util.ArrayList;

import autodromo.punkmkt.com.ahrapp.adapters.GalleryAdapter;
import autodromo.punkmkt.com.ahrapp.models.GalleryItem;
import autodromo.punkmkt.com.ahrapp.models.Piloto;
import autodromo.punkmkt.com.ahrapp.models.Premio;
import autodromo.punkmkt.com.ahrapp.utils.MyVolleySingleton;


public class PilotosDetalleActivity extends Activity {
    private String AHZ_PILOTOS_JSON_API_URL = "http://104.236.3.158/api/pilotos/";
    static ArrayList<GalleryItem> galeria_fotos = new ArrayList<GalleryItem>();
    Piloto piloto;
    static ImageLoader imageLoader = MyVolleySingleton.getInstance().getImageLoader();
    private RecyclerView.Adapter adapter;
    static ImageView imagen;
    TextView descripcion_nombre_piloto;
    TextView descripcion_numero_piloto;
    TextView descripcion_equipo_piloto;
    TextView descripcion_nacionalidad_piloto;
    TextView descripcion_fecha_nacimiento_piloto;
    //TextView descripcion_lugar_nacimiento_piloto;
    TextView descripcion_campeonatos_piloto;
    TextView descripcion_grands_prix_piloto;
    TextView descripcion_podiums_piloto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilotos_detalle);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
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
        Premio premio = new Premio();
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

        /*Log.d("id",id);*/
        switch (Integer.parseInt(id)){
            case 0:
                imagen.setImageResource(R.drawable.alexander_rossi_car);
                break;
            case 1:
                imagen.setImageResource(R.drawable.carlos_sainz_car);
                break;
            case 2:
                imagen.setImageResource(R.drawable.daniel_ricciardo_car);
                break;
            case 3:
                imagen.setImageResource(R.drawable.daniil_kvyat_car);
                break;
            case 4:
                imagen.setImageResource(R.drawable.felipe_massa_car);
                break;
            case 5:
                imagen.setImageResource(R.drawable.felipe_nassar_car);
                break;
            case 6:
                imagen.setImageResource(R.drawable.fernando_alonso_car);
                break;
            case 7:
                imagen.setImageResource(R.drawable.jenson_button_car);
                break;
            case 8:
                imagen.setImageResource(R.drawable.kimi_rnikkinen_car);
                break;
            case 9:
                imagen.setImageResource(R.drawable.lewis_hamilton_car);
                break;
            case 10:
                imagen.setImageResource(R.drawable.marcus_ericsson_car);
                break;
            case 11:
                imagen.setImageResource(R.drawable.max_verstappen_car);
                break;
            case 12:
                imagen.setImageResource(R.drawable.nico_hulkenberg_car);
                break;
            case 13:
                imagen.setImageResource(R.drawable.rico_rosberg_car);
                break;
            case 14:
                imagen.setImageResource(R.drawable.pastor_maldonado_car);
                break;
            case 15:
                imagen.setImageResource(R.drawable.romain_grosjean_car);
                break;
            case 16:
                imagen.setImageResource(R.drawable.sebastian_vettel_car);
                break;
            case 17:
                imagen.setImageResource(R.drawable.sergio_perez_car);
                break;
            case 18:
                imagen.setImageResource(R.drawable.valtteri_bottas_car);
                break;
            case 19:
                imagen.setImageResource(R.drawable.will_stevens_car);
                break;
            default:
                break;
        }

        piloto = new Piloto(Integer.parseInt(id),nombre,image,numero,equipo,nacionalidad,fecha_nacimiento,campeonatos,grands_prix,podiums);
        createdetailpiloto(piloto);


    }

    public void createdetailpiloto(Piloto piloto){
        descripcion_nombre_piloto.setText(piloto.getNombre());
        descripcion_numero_piloto.setText(piloto.getNumero());
        descripcion_equipo_piloto.setText(piloto.getEquipo());
        descripcion_nacionalidad_piloto.setText(piloto.getNacionalidad());
        descripcion_fecha_nacimiento_piloto.setText(piloto.getFecha_nacimiento());
        //descripcion_lugar_nacimiento_piloto.setText(piloto.getLugar_nacimiento());
        descripcion_campeonatos_piloto.setText(piloto.getCampeonatos());
        descripcion_grands_prix_piloto.setText(piloto.getGrands_prix());
        descripcion_podiums_piloto.setText(piloto.getPodiums());
    }
    public static void updatePhoto(String url){
        //imagen.setImageUrl(url, imageLoader);

        Log.d("url",url);
        switch (url){
            //imagen.setImageDrawable(r.getDrawable(R.drawable.noticia_photo1));
            case "foto1":
                imagen.setImageResource(R.drawable.piloto_gallery);
                //imagen.setDefaultImageResId(R.drawable.piloto_gallery);
                //imagen.setImageUrl(galeria_fotos.get(0).getImage(), imageLoader);
                //imagen.setImageDrawable(ContextCompat.getDrawable(InitializeApplication.getAppContext(),R.drawable.piloto_gallery));
                break;
            case "foto2":
                Log.d("entre", "entre");
                imagen.setImageResource(R.drawable.noticia_photo1);
                //imagen.setDefaultImageResId(R.drawable.noticia_photo1);
                //imagen.setImageDrawable(ContextCompat.getDrawable(InitializeApplication.getAppContext(), R.drawable.noticia_photo1));
                break;
            default:
                break;
        }

    }
}