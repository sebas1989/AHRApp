package autodromo.punkmkt.com.ahrapp.fragments;

/**
 * Created by sebastianmendezgiron on 30/09/15.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import autodromo.punkmkt.com.ahrapp.MyVolleySingleton;
import autodromo.punkmkt.com.ahrapp.R;
import autodromo.punkmkt.com.ahrapp.adapters.HospedajeAdapter;
import autodromo.punkmkt.com.ahrapp.models.Hotel;
import autodromo.punkmkt.com.ahrapp.models.Noticias;
import autodromo.punkmkt.com.ahrapp.utils.AuthRequest;

/**
 * Created by germanpunk on 24/09/15.
 */
public class HospedajeFragment extends Fragment {
    private RecyclerView.Adapter adapter;
    private final String AHZ_URL_HOTELES = "http://104.236.3.158/api/hoteles/";
    private ArrayList<Hotel> hoteles = new ArrayList<Hotel>();
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //ReclyclerView, Adapter

        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        adapter = new HospedajeAdapter(hoteles);


        StringRequest request = new AuthRequest(Request.Method.GET, AHZ_URL_HOTELES,"UTF-8", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //Log.d(":o", response);
                    JSONArray object = new JSONArray(response);
                    for (int count = 0; count < object.length(); count++) {
                        JSONObject anEntry = object.getJSONObject(count);
                        Hotel hotel = new Hotel();

                        hotel.setId(Integer.parseInt(anEntry.optString("id")));
                        hotel.setNombre(anEntry.getString("nombre"));
                        hotel.setUbicacion(anEntry.getString("ubicacion"));
                        hotel.setTelefono(anEntry.getString("telefono"));
                        hotel.setImagen(anEntry.getString("imagen"));
                        hotel.setLatitud_mapa(anEntry.getString("latitud_mapa"));
                        hotel.setLongitud_mapa(anEntry.getString("longitud_mapa"));
                        hotel.setUrlmap(anEntry.getString("website"));
                        hoteles.add(hotel);
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmentdetalleciudadmexico, container, false);
        return rootView;
    }
}
