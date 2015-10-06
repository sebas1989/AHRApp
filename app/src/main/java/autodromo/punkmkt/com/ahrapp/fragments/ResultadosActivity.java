package autodromo.punkmkt.com.ahrapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import autodromo.punkmkt.com.ahrapp.R;
import autodromo.punkmkt.com.ahrapp.adapters.PremiosAdapter;
import autodromo.punkmkt.com.ahrapp.models.Premio;
import autodromo.punkmkt.com.ahrapp.utils.AuthRequest;
import autodromo.punkmkt.com.ahrapp.MyVolleySingleton;

/**
 * Created by sebastianmendezgiron on 25/09/15.
 */
public class ResultadosActivity extends Fragment {

    TextView ranking_general;
    ImageButton ranking_general_icon;
    private RecyclerView.Adapter adapter;

    private final String AHZ_PREMIOS_JSON_API_URL = "http://104.236.3.158/api/premios/";
    private ArrayList<Premio> premios = new ArrayList<Premio>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmentpremios,container,false);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);


        adapter = new PremiosAdapter(premios);

        StringRequest request = new AuthRequest(Request.Method.GET, AHZ_PREMIOS_JSON_API_URL,"UTF-8", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray object = new JSONArray(response);
                    for (int count = 0; count < object.length(); count++) {
                        JSONObject anEntry = object.getJSONObject(count);
                        Premio premio = new Premio();
                        premio.setId(Integer.parseInt(anEntry.optString("id")));
                        premio.setName(anEntry.optString("nombre"));
                        premio.setImage(anEntry.optString("bandera"));
                        premio.setImageCategoria(anEntry.optString("imagen_categoria"));
                        premios.add(premio);
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


        /*ranking_general = (TextView) getActivity().findViewById(R.id.ranking_general);
        ranking_general_icon = (ImageButton) getActivity().findViewById(R.id.ranking_general_icon);


        ranking_general.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), RankingGeneralActivity.class);
                ResultadosActivity.this.startActivity(myIntent);

            }
        });
        ranking_general_icon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), RankingGeneralActivity.class);
                ResultadosActivity.this.startActivity(myIntent);

            }
        });*/

    }
}
