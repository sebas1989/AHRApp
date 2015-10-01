package autodromo.punkmkt.com.ahrapp.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import autodromo.punkmkt.com.ahrapp.R;
import autodromo.punkmkt.com.ahrapp.adapters.NoticiasAdapter;
import autodromo.punkmkt.com.ahrapp.models.Noticias;
import autodromo.punkmkt.com.ahrapp.utils.AuthRequest;
import autodromo.punkmkt.com.ahrapp.utils.MyVolleySingleton;

/**
 * Created by sebastianmendezgiron on 25/09/15.
 */
public class NewsFragment extends Fragment {

    public RecyclerView.Adapter adapter;
    private final String AHZ_URL_NOTICIAS = "http://104.236.3.158/api/noticias/";
    private ArrayList<Noticias> noticias = new ArrayList<Noticias>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment,container,false);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        adapter = new NoticiasAdapter(noticias);
        StringRequest request = new AuthRequest(Request.Method.GET, AHZ_URL_NOTICIAS,  "utf-8" , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //Log.d(":o", response);
                    JSONArray object = new JSONArray(response);
                    for (int count = 0; count < object.length(); count++) {
                        JSONObject anEntry = object.getJSONObject(count);
                        Noticias noticia = new Noticias();

                        noticia.setId(Integer.parseInt(anEntry.optString("id")));
                        noticia.setTitulo(anEntry.optString("titulo"));
                        noticia.setImagen_portada_rectangular(anEntry.optString("imagen_rectangular"));
                        noticia.setDescripcion_corta(anEntry.optString("descripcion_corta"));
                        noticia.setSubtitulo(anEntry.optString("subtitulo"));
                        noticia.setDescripcion(anEntry.optString("descripcion"));

                        noticias.add(noticia);
                    }
                    adapter.notifyDataSetChanged();

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

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //Por si quieren configurar algom como Grilla solo cambian la linea de arriba por esta:
        //recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }
    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment, container, false);
        return rootView;
    }*/
}
