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

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import autodromo.punkmkt.com.ahrapp.R;
import autodromo.punkmkt.com.ahrapp.adapters.NoticiasAdapter;
import autodromo.punkmkt.com.ahrapp.models.Noticia;
import autodromo.punkmkt.com.ahrapp.utils.AuthRequest;
import autodromo.punkmkt.com.ahrapp.MyVolleySingleton;

/**
 * Created by sebastianmendezgiron on 25/09/15.
 */
public class NewsFragment extends Fragment {

    public RecyclerView.Adapter adapter;
    private final String AHZ_URL_NOTICIAS = "http://104.236.3.158:82/api/premio/news/category_news/1/news/";
    private ArrayList<Noticia> noticias = new ArrayList<Noticia>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment,container,false);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Tracker tracker = ((MyVolleySingleton) getActivity().getApplication()).getTracker(MyVolleySingleton.TrackerName.APP_TRACKER);
        tracker.setScreenName(getString(R.string.menu_noticias));
        tracker.send(new HitBuilders.ScreenViewBuilder().build());

        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        adapter = new NoticiasAdapter(noticias,getActivity());

        StringRequest request = new AuthRequest(getActivity().getApplicationContext(), Request.Method.GET, AHZ_URL_NOTICIAS, "UTF-8", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray array_object = object.getJSONArray("results");
                    for (int count = 0; count < array_object.length(); count++) {
                        JSONObject anEntry = array_object.getJSONObject(count);
                        Noticia noticia = new Noticia();
                        noticia.setId(Integer.parseInt(anEntry.optString("id")));
                        noticia.setTitle(anEntry.optString("short_title"));
                        noticia.setShort_description(anEntry.optString("short_description"));
                        noticia.setThumbnail(anEntry.optString("thumbnail"));
                        noticias.add(noticia);
                    }
                    Log.d("news",noticias.toString());
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
        request.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyVolleySingleton.getInstance().addToRequestQueue(request);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //Por si quieren configurar algom como Grilla solo cambian la linea de arriba por esta:
        //recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }
}
