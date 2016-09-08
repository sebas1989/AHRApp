package autodromo.punkmkt.com.ahrapp;

import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import autodromo.punkmkt.com.ahrapp.utils.AuthRequest;
import autodromo.punkmkt.com.ahrapp.utils.NetworkUtils;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sebastianmendezgiron on 21/09/15.
 */
public class SingleNewDetailActivity extends AppCompatActivity {



    public static final String ALBUM_PATH = "/Download/AHR_Noticias/";
    private String AHR_FILTER_SINGLE_NEW = "http://104.236.3.158/api/noticias/";
    ImageLoader imageLoader = MyVolleySingleton.getInstance().getImageLoader();
    NetworkImageView mNetworkImageView;
    private TextView mTitulo, mSubtitulo, mDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noticia_individual);

        loadSingleNewContent();
    }

    public void loadSingleNewContent(){
        Intent noticia = getIntent();

        String newsId = noticia.getStringExtra("id");
        AHR_FILTER_SINGLE_NEW = AHR_FILTER_SINGLE_NEW + newsId + "/";

        if(NetworkUtils.haveNetworkConnection(this)) {

            StringRequest request = new AuthRequest(getApplicationContext(),Request.Method.GET, AHR_FILTER_SINGLE_NEW, "utf-8", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String titulo = jsonObject.optString("titulo").toString();
                        String subtitulo = jsonObject.optString("subtitulo").toString();
                        String descripcion = jsonObject.optString("descripcion").toString();
                        String img = jsonObject.optString("imagen").toString();

                        mNetworkImageView = (NetworkImageView) findViewById(R.id.imagen_principal);
                        imageLoader = MyVolleySingleton.getInstance().getImageLoader();
                        mNetworkImageView.setImageUrl(img, imageLoader);

                        mTitulo = (TextView) findViewById(R.id.titulo);
                        mTitulo.setText(titulo);

                        mDescripcion = (TextView) findViewById(R.id.descripcion);
                        mDescripcion.setText(descripcion);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });

            MyVolleySingleton.getInstance().addToRequestQueue(request);
        }else{
            Toast.makeText(this, getResources().getString(R.string.minutos), Toast.LENGTH_SHORT).show();
        }

    }

}
