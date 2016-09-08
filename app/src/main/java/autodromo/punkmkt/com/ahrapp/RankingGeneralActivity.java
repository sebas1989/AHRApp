package autodromo.punkmkt.com.ahrapp;

/**
 * Created by sebastianmendezgiron on 28/09/15.
 */
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import autodromo.punkmkt.com.ahrapp.models.Posicion;

import autodromo.punkmkt.com.ahrapp.utils.AuthRequest;

public class RankingGeneralActivity extends Activity {
    private String AHZ_RANKING_GENERAL = "http://104.236.3.158/api/premios/ranking_general/";
    ImageLoader imageLoader = MyVolleySingleton.getInstance().getImageLoader();
    RelativeLayout MyrLayout;
    TableLayout tabla_resultados;
    private ArrayList<Posicion> posiciones_ranking_general = new ArrayList<Posicion>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking_general);
        tabla_resultados = (TableLayout) findViewById(R.id.tabla_resultados);


//        Cache mCache = MyVolleySingleton.getInstance().getRequestQueue().getCache();
//        Cache.Entry mEntry = mCache.get(AHZ_RANKING_GENERAL);
//        if (mEntry != null) {
//            try {
//                String cacheData = new String(mEntry.data, "UTF-8");
//                JSONObject object = new JSONObject(cacheData);
//                JSONArray data = object.getJSONArray("data");
//                for (int count = 0; count < data.length(); count++) {
//                    JSONObject anEntry = data.getJSONObject(count);
//                    //Log.d("volley",anSecondEntry.toString());
//                    Posicion posicion = new Posicion();
//                    posicion.setPiloto_sobrenombre(anEntry.optString("piloto"));
//                    posicion.setEscuderia(anEntry.optString("escuderia"));
//                    posicion.setPuntos(anEntry.optString("puntos"));
//                    posiciones_ranking_general.add(posicion);
//                }
//                iniciarranking();
//
//            } catch (UnsupportedEncodingException |JSONException e) {
//                e.printStackTrace();
//            }
//        } else {
            StringRequest request = new AuthRequest(getApplicationContext(),Request.Method.GET, AHZ_RANKING_GENERAL, "utf-8", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject object = new JSONObject(response);
                        JSONArray data = object.getJSONArray("data");
                        for (int count = 0; count < data.length(); count++) {
                            JSONObject anEntry = data.getJSONObject(count);
                            //Log.d("volley",anSecondEntry.toString());
                            Posicion posicion = new Posicion();
                            posicion.setPiloto_sobrenombre(anEntry.optString("piloto"));
                            posicion.setEscuderia(anEntry.optString("escuderia"));
                            posicion.setPuntos(anEntry.optString("puntos"));
                            posiciones_ranking_general.add(posicion);
                        }
                        iniciarranking();

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
       // }
    }



    public void iniciarranking() {
        ArrayList<Posicion> copia = new ArrayList<Posicion>();
        copia = posiciones_ranking_general;
        tabla_resultados.removeAllViews();
        TableRow row = (TableRow) getLayoutInflater().inflate(R.layout.title_ranking_general, null);

        tabla_resultados.addView(row);
        for (int count = 0; count < copia.size(); count++) {
            Posicion posicion = copia.get(count);
            TableRow row_pos = (TableRow) getLayoutInflater().inflate(R.layout.row_ranking_general, null);
            ((TextView) row_pos.findViewById(R.id.pos)).setText(Integer.toString(count + 1));
            ((TextView) row_pos.findViewById(R.id.piloto)).setText(posicion.getPiloto_sobrenombre());
            ((NetworkImageView) row_pos.findViewById(R.id.escuderia)).setImageUrl(posicion.getEscuderia(), imageLoader);
            ((TextView) row_pos.findViewById(R.id.puntos)).setText(posicion.getPuntos());
            tabla_resultados.addView(row_pos);
        }
    }


}
