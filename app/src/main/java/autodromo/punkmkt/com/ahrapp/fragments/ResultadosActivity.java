package autodromo.punkmkt.com.ahrapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;
import autodromo.punkmkt.com.ahrapp.R;
import autodromo.punkmkt.com.ahrapp.RankingGeneralActivity;
import autodromo.punkmkt.com.ahrapp.models.Premio;

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
        View v = inflater.inflate(R.layout.activity_resultados,container,false);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container_fragment, new PremiosFragment())
                    .commit();
        }
        ranking_general = (TextView) getActivity().findViewById(R.id.ranking_general);
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
        });

    }
}
