package autodromo.punkmkt.com.ahrapp.fragments;

/**
 * Created by sebastianmendezgiron on 25/09/15.
 */

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v4.app.Fragment;
import autodromo.punkmkt.com.ahrapp.R;
import autodromo.punkmkt.com.ahrapp.adapters.PilotosAdapter;
import autodromo.punkmkt.com.ahrapp.models.Piloto;

import java.util.ArrayList;

/**
 * Created by germanpunk on 20/09/15.
 */
public class PilotosFragment extends Fragment {

    private RecyclerView.Adapter adapter;

    private final String AHZ_PILOTOS_JSON_API_URL = "http://104.236.3.158/api/pilotos/";
    private ArrayList<Piloto> pilotos = new ArrayList<Piloto>();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        Piloto p1 = new Piloto(0, "Alexander Rossi",        "alexander_rossi_car.jpg" ,     "53",       "MARUSSIA",         "Estados Unidos",       "09/25/1991",        "N/A",  "2",    "N/A");
        Piloto p2 = new Piloto(1, "Carlos Sainz",           "carlos_sainz_car.jpg",         "55",       "TORO ROSSO",       "España",               "09/01/1994",        "N/A",  "14",   "N/A");
        Piloto p3 = new Piloto(2, "Daniel Ricciardo",       "daniel_ricciardo_car.jpg",     "3",        "RED BULL RACING",  "Australia",            "07/01/1989",        "N/A",  "83",   "10");
        Piloto p4 = new Piloto(3, "Daniil Kviat",           "daniil_kvyat_car.jpg",         "26",       "RED BULL RACICNG", "Rusia",                "04/26/1994",        "N/A",  "33",   "1");
        Piloto p5 = new Piloto(4, "Felipe Massa",           "felipe_massa_car.jpg",         "19",       "WILLIAMS",         "Brasil",               "04/25/1981",        "N/A",  "225",  "41");
        Piloto p6 = new Piloto(5, "Felipe Nasr",            "felipe_nassar_car.jpg",        "12",       "SAUBER",           "Brasil",               "08/21/1992",        "N/A",  "14",   "N/A");
        Piloto p7 = new Piloto(6, "Fernando Alonso",        "fernando_alonso_car.jpg",      "14",       "MCLAREN",          "España",               "07/29/1981",        "2",    "249",  "97");
        Piloto p8 = new Piloto(7, "Jenson Button",          "jenson_button_car.jpg",        "22",       "MCLAREN",          "Gran Bretaña",         "01/19/1980",        "1",    "282",  "51");
        Piloto p9 = new Piloto(8, "Kimi Räikkönen",         "kimi_rnikkinen_car.jpg",       "7",        "FERRARI",          "Finlandia",            "10/17/1979",        "1",    "227",  "79");
        Piloto p10 = new Piloto(9, "Lewis Hamilton",        "lewis_hamilton_car.jpg",       "44",       "MERCEDES",         "Gran Bretaña",         "01/07/1985",        "2",    "162",  "82");
        Piloto p11 = new Piloto(10, "Marcus Ericsson",      "marcus_ericsson_car.jpg",      "9",        "SAUBER",           "Suecia",               "09/02/1990",        "N/A",  "30",   "N/A");
        Piloto p12 = new Piloto(11, "Max Verstappen",       "max_verstappen_car.jpg",       "33",       "TORO ROSSO",       "Pasíses Bajos",        "09/30/1997",        "N/A",  "14",   "N/A");
        Piloto p13 = new Piloto(12, "Nico Hülkenberg",      "nico_hulkenberg_car.jpg",      "27",       "FORCE INDIA",      "Alemania",             "08/19/1987",        "N/A",  "91",   "N/A");
        Piloto p14 = new Piloto(13, "Nico Rosberg",         "nico_rosberg_car.jpg",         "6",        "MERCEDES",         "Alemania",             "06/27/1985",        "N/A",  "180",  "37");
        Piloto p15 = new Piloto(14, "Pastor Maldonado",     "pastor_maldonado_car.jpg",     "13",       "LOTUS",            "Venezuela",            "03/10/1985",        "N/A",  "91",   "1");
        Piloto p16 = new Piloto(15, "Romain Grosjean",      "romain_grosjean_car.jpg",      "8",        "LOTUS",            "Francia",              "04/17/1986",        "N/A",  "78",   "10");
        Piloto p17 = new Piloto(16, "Sebastian Vettel",     "sebastian_vettel_car.jpg",     "5",        "FERRARI",          "Alemania",             "07/03/1987",        "4",    "153",  "76");
        Piloto p18 = new Piloto(17, "Sergio Pérez",         "sergio_perez_car.jpg",         "11",       "FORCE INDIA",      "México",               "01/26/1990",        "N/A",  "90",   "4");
        Piloto p19 = new Piloto(18, "Valtteri Bottas",      "valtteri_bottas_car.jpg",      "77",       "WILLIAMS",         "Finlandia",            "08/28/1989",        "N/A",  "52",   "7");
        Piloto p20 = new Piloto(19, "Will Stevens",         "will_stevens_car.jpg",         "28",       "MARUSSIA",         "Gran Bretaña",         "06/28/1991",        "N/A",  "14", "N/A");

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
        adapter = new PilotosAdapter(pilotos);

        //Por si quieren configurar algom como Grill solo cambian la linea de arriba por esta:
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getActivity().getApplicationContext(), R.dimen.item_offset);
        recyclerView.addItemDecoration(itemDecoration);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmentpiloto, container, false);
        return rootView;
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
