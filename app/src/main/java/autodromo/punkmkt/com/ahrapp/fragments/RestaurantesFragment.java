package autodromo.punkmkt.com.ahrapp.fragments;

/**
 * Created by sebastianmendezgiron on 30/09/15.
 */
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import autodromo.punkmkt.com.ahrapp.MyVolleySingleton;
import autodromo.punkmkt.com.ahrapp.R;
import autodromo.punkmkt.com.ahrapp.adapters.RestauranteAdapter;
import autodromo.punkmkt.com.ahrapp.models.Restaurante;
import autodromo.punkmkt.com.ahrapp.utils.AuthRequest;
import autodromo.punkmkt.com.ahrapp.utils.BitmapManager;

/**
 * Created by germanpunk on 24/09/15.
 */
public class RestaurantesFragment extends Fragment {
    private RecyclerView.Adapter adapter;
    private final String AHZ_URL_RESTAURANTES = "http://104.236.3.158/api/restaurantes/";
    private ArrayList<Restaurante> restaurantes = new ArrayList<Restaurante>();
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Tracker tracker = ((MyVolleySingleton) getActivity().getApplication()).getTracker(MyVolleySingleton.TrackerName.APP_TRACKER);
        tracker.setScreenName(getString(R.string.restaurantes));
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
        int width = 48;
        int height = 48;
        //ReclyclerView, Adapter
        Button res = (Button) getActivity().findViewById(R.id.restaurantes);
        //res.setBackground(getResources().getDrawable(R.drawable.restaurant_icon_hover_96));
        res.setBackground(new BitmapDrawable(getResources(), BitmapManager.decodeSampledBitmapFromResource(getResources(), R.drawable.restaurant_icon_hover_96, width, height)));
        Button hospedaje = (Button) getActivity().findViewById(R.id.hoteles);
        //hospedaje.setBackground(getResources().getDrawable(R.drawable.hotel_icon));
        hospedaje.setBackground(new BitmapDrawable(getResources(), BitmapManager.decodeSampledBitmapFromResource(getResources(), R.drawable.hotel_icon, width, height)));
        Button lug = (Button) getActivity().findViewById(R.id.lugares);
        lug.setBackground(new BitmapDrawable(getResources(), BitmapManager.decodeSampledBitmapFromResource(getResources(), R.drawable.city_icon, width, height)));



        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        adapter = new RestauranteAdapter(restaurantes,getActivity());


        Cache mCache = MyVolleySingleton.getInstance().getRequestQueue().getCache();
        Cache.Entry mEntry = mCache.get(AHZ_URL_RESTAURANTES);
        if (mEntry != null) {
            try {
                String cacheData = new String(mEntry.data, "UTF-8");
                JSONArray object = new JSONArray(cacheData);
                for (int count = 0; count < object.length(); count++) {
                    JSONObject anEntry = object.getJSONObject(count);
                    Restaurante restaurante = new Restaurante();
                    restaurante.setId(Integer.parseInt(anEntry.optString("id")));
                    restaurante.setNombre(anEntry.getString("nombre"));
                    restaurante.setUbicacion(anEntry.getString("ubicacion"));
                    restaurante.setTelefono(anEntry.getString("telefono"));
                    restaurante.setImagen(anEntry.getString("imagen"));
                    restaurante.setLatitud_mapa(anEntry.getString("latitud_mapa"));
                    restaurante.setLongitud_mapa(anEntry.getString("longitud_mapa"));
                    restaurante.setUrlmap(anEntry.getString("website"));
                    restaurantes.add(restaurante);
                }
                adapter.notifyDataSetChanged();
            } catch (UnsupportedEncodingException |JSONException e) {
                e.printStackTrace();
            }
        } else {
            StringRequest request = new AuthRequest(getActivity().getApplicationContext(),Request.Method.GET, AHZ_URL_RESTAURANTES, "UTF-8", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray object = new JSONArray(response);
                        for (int count = 0; count < object.length(); count++) {
                            JSONObject anEntry = object.getJSONObject(count);
                            Restaurante restaurante = new Restaurante();
                            restaurante.setId(Integer.parseInt(anEntry.optString("id")));
                            restaurante.setNombre(anEntry.getString("nombre"));
                            restaurante.setUbicacion(anEntry.getString("ubicacion"));
                            restaurante.setTelefono(anEntry.getString("telefono"));
                            restaurante.setImagen(anEntry.getString("imagen"));
                            restaurante.setLatitud_mapa(anEntry.getString("latitud_mapa"));
                            restaurante.setLongitud_mapa(anEntry.getString("longitud_mapa"));
                            restaurante.setUrlmap(anEntry.getString("website"));
                            restaurantes.add(restaurante);
                        }
                        adapter.notifyDataSetChanged();

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
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmentdetalleciudadmexico, container, false);
        return rootView;
    }

}
