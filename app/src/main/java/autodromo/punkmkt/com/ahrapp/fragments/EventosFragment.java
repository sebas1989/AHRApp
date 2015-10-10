package autodromo.punkmkt.com.ahrapp.fragments;

/**
 * Created by sebastianmendezgiron on 30/09/15.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import autodromo.punkmkt.com.ahrapp.MyVolleySingleton;
import autodromo.punkmkt.com.ahrapp.R;
import autodromo.punkmkt.com.ahrapp.adapters.EventosAdapter;
import autodromo.punkmkt.com.ahrapp.models.Evento;
import autodromo.punkmkt.com.ahrapp.utils.AuthRequest;

/**
 * Created by germanpunk on 24/09/15.
 */

public class EventosFragment extends Fragment {
    private RecyclerView.Adapter adapter;
    private final String AHZ_URL_EVENTOS = "http://104.236.3.158/api/eventos/";
    private ArrayList<Evento> eventos = new ArrayList<Evento>();
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        adapter = new EventosAdapter(eventos);

        Cache mCache = MyVolleySingleton.getInstance().getRequestQueue().getCache();
        Cache.Entry mEntry = mCache.get(AHZ_URL_EVENTOS);
        if (mEntry != null) {
            try {
                String cacheData = new String(mEntry.data, "UTF-8");
                JSONArray object = new JSONArray(cacheData);
                for (int count = 0; count < object.length(); count++) {
                    JSONObject anEntry = object.getJSONObject(count);
                    Evento evento = new Evento();

                    evento.setId(Integer.parseInt(anEntry.optString("id")));
                    evento.setNombre(anEntry.getString("nombre"));
                    evento.setUbicacion(anEntry.getString("ubicacion"));
                    evento.setTelefono(anEntry.getString("telefono"));
                    evento.setImagen(anEntry.getString("imagen"));
                    evento.setLatitud_mapa(anEntry.getString("latitud_mapa"));
                    evento.setLongitud_mapa(anEntry.getString("longitud_mapa"));
                    evento.setUrlmap(anEntry.getString("website"));
                    eventos.add(evento);
                }
                adapter.notifyDataSetChanged();
            } catch (UnsupportedEncodingException |JSONException e) {
                e.printStackTrace();
            }
        } else {
            StringRequest request = new AuthRequest(Request.Method.GET, AHZ_URL_EVENTOS, "UTF-8", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {

                        JSONArray object = new JSONArray(response);
                        for (int count = 0; count < object.length(); count++) {
                            JSONObject anEntry = object.getJSONObject(count);
                            Evento evento = new Evento();

                            evento.setId(Integer.parseInt(anEntry.optString("id")));
                            evento.setNombre(anEntry.getString("nombre"));
                            evento.setUbicacion(anEntry.getString("ubicacion"));
                            evento.setTelefono(anEntry.getString("telefono"));
                            evento.setImagen(anEntry.getString("imagen"));
                            evento.setLatitud_mapa(anEntry.getString("latitud_mapa"));
                            evento.setLongitud_mapa(anEntry.getString("longitud_mapa"));
                            evento.setUrlmap(anEntry.getString("website"));
                            eventos.add(evento);
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
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //Por si quieren configurar algom como Grilla solo cambian la linea de arriba por esta:
        //recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmentdetalleciudadmexico, container, false);
        return rootView;
    }
}
