package autodromo.punkmkt.com.ahrapp;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.content.Intent;

import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import autodromo.punkmkt.com.ahrapp.utils.AuthRequest;
import autodromo.punkmkt.com.ahrapp.utils.MyVolleySingleton;
import autodromo.punkmkt.com.ahrapp.utils.BitmapManager;
import autodromo.punkmkt.com.ahrapp.utils.NetworkUtils;

import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sebastianmendezgiron on 21/09/15.
 */
public class SingleNewDetailActivity extends FragmentActivity {

    public static final String ALBUM_PATH = "/Download/AHR_Noticias/";
    private String AHR_FILTER_SINGLE_NEW = "http://104.236.3.158/api/noticias/";
    ImageLoader imageLoader = MyVolleySingleton.getInstance().getImageLoader();
    NetworkImageView mNetworkImageView;
    private String titulo, subtitulo, descripcion, img;
    private TextView mTitulo, mSubtitulo, mDescripcion;
    StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_selected_one);

        loadSingleNewContent();
    }

    public void loadSingleNewContent(){
        Intent noticia = getIntent();

        String newsId = noticia.getStringExtra("id");
        AHR_FILTER_SINGLE_NEW = AHR_FILTER_SINGLE_NEW + newsId + "/";

        if(NetworkUtils.haveNetworkConnection(this)) {

            StringRequest request = new AuthRequest(Request.Method.GET, AHR_FILTER_SINGLE_NEW, "utf-8", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        //JSONArray newsArray = new JSONArray(response);
                        JSONObject jsonObject = new JSONObject(response);
                        Log.d(":o", response);
                        String titulo = jsonObject.optString("titulo").toString();
                        String subtitulo = jsonObject.optString("subtitulo").toString();
                        String descripcion = jsonObject.optString("descripcion").toString();
                        String img = jsonObject.optString("imagen").toString();

                        mNetworkImageView = (NetworkImageView) findViewById(R.id.imagen_principal);
                        imageLoader = MyVolleySingleton.getInstance().getImageLoader();
                        mNetworkImageView.setImageUrl(img, imageLoader);

                        mTitulo = (TextView) findViewById(R.id.titulo);
                        mTitulo.setText(titulo);

                        mSubtitulo = (TextView) findViewById(R.id.subtitulo);
                        mSubtitulo.setText(subtitulo);

                        mDescripcion = (TextView) findViewById(R.id.descripcion);
                        mDescripcion.setText(descripcion);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("volley", "Error during request");
                    error.printStackTrace();
                }
            });

            MyVolleySingleton.getInstance().addToRequestQueue(request);
        }else{
            Toast.makeText(this, getResources().getString(R.string.minutos), Toast.LENGTH_SHORT).show();
        }
    }


}
