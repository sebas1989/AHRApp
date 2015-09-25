package autodromo.punkmkt.com.ahrapp;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.content.Intent;

import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import autodromo.punkmkt.com.ahrapp.utils.MyVolleySingleton;
import autodromo.punkmkt.com.ahrapp.utils.BitmapManager;
import com.android.volley.toolbox.NetworkImageView;

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

/**
 * Created by sebastianmendezgiron on 21/09/15.
 */
public class SingleNewDetailActivity extends FragmentActivity {

    public static final String ALBUM_PATH = "/Download/AHR_Noticias/";
    ImageLoader imageLoader = MyVolleySingleton.getInstance().getImageLoader();
    NetworkImageView mNetworkImageView;
    private TextView mTitulo, mSubtitulo, mDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_selected_one);

        loadSingleNewContent();

        /*try {
            shareNews(true);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/

    }

    public void loadSingleNewContent(){
        Intent noticia = getIntent();

        mNetworkImageView = (NetworkImageView) this.findViewById(R.id.imagen_principal);
        imageLoader = MyVolleySingleton.getInstance().getImageLoader();
        mNetworkImageView.setImageUrl(noticia.getStringExtra("imagen"), imageLoader);

        mTitulo = (TextView) findViewById(R.id.titulo);
        mTitulo.setText(noticia.getStringExtra("nombre").toString());

        mSubtitulo = (TextView) findViewById(R.id.subtitulo);
        mSubtitulo.setText(noticia.getStringExtra("subtitulo"));

        mDescripcion = (TextView) findViewById(R.id.descripcion);
        mDescripcion.setText(noticia.getStringExtra("descripcion"));
    }


}
