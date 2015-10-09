package autodromo.punkmkt.com.ahrapp.fragments;

/**
 * Created by sebastianmendezgiron on 25/09/15.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView.OnItemClickListener;
import android.support.v4.app.Fragment;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import autodromo.punkmkt.com.ahrapp.PilotosDetalleActivity;
import autodromo.punkmkt.com.ahrapp.R;
import autodromo.punkmkt.com.ahrapp.adapters.PilotosAdapter;
import autodromo.punkmkt.com.ahrapp.adapters.PilotosAdapterGrid;
import autodromo.punkmkt.com.ahrapp.models.Piloto;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by germanpunk on 20/09/15.
 */
public class PilotosFragment extends Fragment {

    private RecyclerView.Adapter adapter;

    private final String AHZ_PILOTOS_JSON_API_URL = "http://104.236.3.158/api/pilotos/";
    private ArrayList<Piloto> pilotos = new ArrayList<Piloto>();
    public GridView grid;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmentpiloto, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        /*grid = (GridView) getActivity().findViewById(R.id.pilotGrid);
        PilotosAdapterGrid adapter = new PilotosAdapterGrid(getActivity(), title, Imageid);
        grid.setAdapter(adapter);*/

        grid = (GridView) getActivity().findViewById(R.id.pilotGrid);

        /*RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);*/

        Piloto p1 = new Piloto(0, "Alexander Rossi", R.drawable.alexander_rossi_pilot,    "53",       "MARUSSIA",         "Estados Unidos",       "09/25/1991",        "N/A",  "2",    "N/A");
        Piloto p2 = new Piloto(1, "Carlos Sainz",    R.drawable.carlos_sainz_pilot,    "55",       "TORO ROSSO",       "España",               "09/01/1994",        "N/A",  "14",   "N/A");
        Piloto p3 = new Piloto(2, "Daniel Ricciardo",R.drawable.daniel_ricciardo_pilot,    "3",        "RED BULL RACING",  "Australia",            "07/01/1989",        "N/A",  "83",   "10");
        Piloto p4 = new Piloto(3, "Daniil Kviat",    R.drawable.daniil_kvyat_pilot,    "26",       "RED BULL RACICNG", "Rusia",                "04/26/1994",        "N/A",  "33",   "1");
        Piloto p5 = new Piloto(4, "Felipe Massa",    R.drawable.felipe_massa_pilot,    "19",       "WILLIAMS",         "Brasil",               "04/25/1981",        "N/A",  "225",  "41");
        Piloto p6 = new Piloto(5, "Felipe Nasr",     R.drawable.felipe_nassar_pilot,    "12",       "SAUBER",           "Brasil",               "08/21/1992",        "N/A",  "14",   "N/A");
        Piloto p7 = new Piloto(6, "Fernando Alonso", R.drawable.fernando_alonso_pilot,    "14",       "MCLAREN",          "España",               "07/29/1981",        "2",    "249",  "97");
        Piloto p8 = new Piloto(7, "Jenson Button",   R.drawable.jenson_button_pilot,    "22",       "MCLAREN",          "Gran Bretaña",         "01/19/1980",        "1",    "282",  "51");
        Piloto p9 = new Piloto(8, "Kimi Räikkönen",  R.drawable.kimi_rnikkinen_pilot,    "7",        "FERRARI",          "Finlandia",            "10/17/1979",        "1",    "227",  "79");
        Piloto p10 = new Piloto(9, "Lewis Hamilton", R.drawable.lewis_hamilton_pilot,    "44",       "MERCEDES",         "Gran Bretaña",         "01/07/1985",        "2",    "162",  "82");
        Piloto p11 = new Piloto(10, "Marcus Ericsson",R.drawable.marcus_ericsson_pilot,   "9",        "SAUBER",           "Suecia",               "09/02/1990",        "N/A",  "30",   "N/A");
        Piloto p12 = new Piloto(11, "Max Verstappen", R.drawable.max_verstappen_pilot,   "33",       "TORO ROSSO",       "Pasíses Bajos",        "09/30/1997",        "N/A",  "14",   "N/A");
        Piloto p13 = new Piloto(12, "Nico Hülkenberg",R.drawable.nico_hulkenberg_pilot,   "27",       "FORCE INDIA",      "Alemania",             "08/19/1987",        "N/A",  "91",   "N/A");
        Piloto p14 = new Piloto(13, "Nico Rosberg",   R.drawable.nico_rosberg_pilot,   "6",        "MERCEDES",         "Alemania",             "06/27/1985",        "N/A",  "180",  "37");
        Piloto p15 = new Piloto(14, "Pastor Maldonado",R.drawable.pastor_maldonado_pilot,  "13",       "LOTUS",            "Venezuela",            "03/10/1985",        "N/A",  "91",   "1");
        Piloto p16 = new Piloto(15, "Romain Grosjean", R.drawable.romain_grosjean_pilot,  "8",        "LOTUS",            "Francia",              "04/17/1986",        "N/A",  "78",   "10");
        Piloto p17 = new Piloto(16, "Sebastian Vettel",R.drawable.sebastian_vettel_pilot,  "5",        "FERRARI",          "Alemania",             "07/03/1987",        "4",    "153",  "76");
        Piloto p18 = new Piloto(17, "Sergio Pérez",   R.drawable.sergio_perez_pilot,   "11",       "FORCE INDIA",      "México",               "01/26/1990",        "N/A",  "90",   "4");
        Piloto p19 = new Piloto(18, "Valtteri Bottas",R.drawable.valtteri_bottas_pilot,   "77",       "WILLIAMS",         "Finlandia",            "08/28/1989",        "N/A",  "52",   "7");
        Piloto p20 = new Piloto(19, "Will Stevens",   R.drawable.will_stevens_pilot,   "28",       "MARUSSIA",         "Gran Bretaña",         "06/28/1991",        "N/A",  "14", "N/A");

        pilotos.add(p1);
        pilotos.add(p2);
        pilotos.add(p3);
        pilotos.add(p4);
        pilotos.add(p5);
        pilotos.add(p6);
        pilotos.add(p7);
        pilotos.add(p8);
        pilotos.add(p9);
        pilotos.add(p10);
        pilotos.add(p11);
        pilotos.add(p12);
        pilotos.add(p13);
        pilotos.add(p14);
        pilotos.add(p15);
        pilotos.add(p16);
        pilotos.add(p17);
        pilotos.add(p18);
        pilotos.add(p19);
        pilotos.add(p20);

        PilotosAdapterGrid adapter = new PilotosAdapterGrid(getActivity(), R.layout.row_piloto, pilotos);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                Piloto clicked_piloto = (Piloto) grid.getItemAtPosition(position);
                Toast.makeText(getActivity(), "You Clicked at " + clicked_piloto.getNombre(), Toast.LENGTH_SHORT).show();
                Intent pilotoDetalle = new Intent (getActivity(), PilotosDetalleActivity.class);
                pilotoDetalle.putExtra("id", Integer.toString(clicked_piloto.getId()));
                pilotoDetalle.putExtra("nombre", clicked_piloto.getNombre());
                pilotoDetalle.putExtra("image", clicked_piloto.getFoto());
                pilotoDetalle.putExtra("equipo", clicked_piloto.getEquipo());
                pilotoDetalle.putExtra("numero", clicked_piloto.getNumero());
                pilotoDetalle.putExtra("nacionalidad", clicked_piloto.getNacionalidad());
                pilotoDetalle.putExtra("fecha_nacimiento", clicked_piloto.getFecha_nacimiento());
                pilotoDetalle.putExtra("campeonatos", clicked_piloto.getCampeonatos());
                pilotoDetalle.putExtra("grands_prix", clicked_piloto.getGrands_prix());
                pilotoDetalle.putExtra("podiums", clicked_piloto.getPodiums());
                startActivity(pilotoDetalle);            }
        });

        /*grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {

                Piloto clicked_piloto = (Piloto) grid.getItemAtPosition(position);
                Intent pilotoDetalle = new Intent (getActivity(), PilotosDetalleActivity.class);
                pilotoDetalle.putExtra("id", Integer.toString(clicked_piloto.getId()));
                pilotoDetalle.putExtra("nombre", clicked_piloto.getNombre());
                pilotoDetalle.putExtra("image", clicked_piloto.getFoto());
                pilotoDetalle.putExtra("equipo", clicked_piloto.getEquipo());
                pilotoDetalle.putExtra("numero", clicked_piloto.getNumero());
                pilotoDetalle.putExtra("nacionalidad", clicked_piloto.getNacionalidad());
                pilotoDetalle.putExtra("fecha_nacimiento", clicked_piloto.getFecha_nacimiento());
                pilotoDetalle.putExtra("campeonatos", clicked_piloto.getCampeonatos());
                pilotoDetalle.putExtra("grands_prix", clicked_piloto.getGrands_prix());
                pilotoDetalle.putExtra("podiums", clicked_piloto.getPodiums());
                startActivity(pilotoDetalle);

            }

        });*/

        /*adapter = new PilotosAdapter(pilotos);

        //Por si quieren configurar algom como Grill solo cambian la linea de arriba por esta:
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getActivity().getApplicationContext(), R.dimen.item_offset);
        recyclerView.addItemDecoration(itemDecoration);*/
    }


    public class ItemOffsetDecoration extends RecyclerView.ItemDecoration {

        private int mItemOffset;

        public ItemOffsetDecoration(int itemOffset) {
            mItemOffset = itemOffset;
        }

        public ItemOffsetDecoration(@NonNull Context context, @DimenRes int itemOffsetId) {
            this(context.getResources().getDimensionPixelSize(itemOffsetId));
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                   RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(mItemOffset, mItemOffset, mItemOffset, mItemOffset);
        }
    }

}
