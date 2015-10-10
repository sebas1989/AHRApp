package autodromo.punkmkt.com.ahrapp.fragments;

/**
 * Created by sebastianmendezgiron on 25/09/15.
 */

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
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
import android.widget.GridView;
import autodromo.punkmkt.com.ahrapp.R;
import autodromo.punkmkt.com.ahrapp.adapters.PilotosAdapter;
import autodromo.punkmkt.com.ahrapp.models.Piloto;

import java.util.ArrayList;

/**
 * Created by germanpunk on 20/09/15.
 */
public class PilotosFragment extends Fragment {

    private RecyclerView.Adapter adapter;
    Bitmap b;Bitmap b2;Bitmap b3;Bitmap b4;Bitmap b5;Bitmap b6;Bitmap b7;Bitmap b8;Bitmap b9;Bitmap b10;
    Bitmap b11;Bitmap b12;Bitmap b13;Bitmap b14;Bitmap b15;Bitmap b16;Bitmap b17;Bitmap b18;Bitmap b19;Bitmap b20;
    private ArrayList<Piloto> pilotos = new ArrayList<Piloto>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmentpiloto, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        final BitmapFactory.Options options = new BitmapFactory.Options();
        //options.inJustDecodeBounds=true;
        options.inSampleSize = 2;


        b =BitmapFactory.decodeResource(this.getResources(), R.drawable.alexander_rossi_pilot,options);
        b2 =BitmapFactory.decodeResource(this.getResources(), R.drawable.carlos_sainz_pilot,options);
        b3 =BitmapFactory.decodeResource(this.getResources(), R.drawable.daniel_ricciardo_pilot,options);
        b4 =BitmapFactory.decodeResource(this.getResources(), R.drawable.daniil_kvyat_pilot,options);
        b5 =BitmapFactory.decodeResource(this.getResources(), R.drawable.felipe_massa_pilot,options);
        b6 =BitmapFactory.decodeResource(this.getResources(), R.drawable.felipe_nassar_pilot,options);
        b7 =BitmapFactory.decodeResource(this.getResources(), R.drawable.fernando_alonso_pilot,options);
        b8 =BitmapFactory.decodeResource(this.getResources(), R.drawable.jenson_button_pilot,options);
        b9 =BitmapFactory.decodeResource(this.getResources(), R.drawable.kimi_rnikkinen_pilot,options);
        b10 =BitmapFactory.decodeResource(this.getResources(), R.drawable.lewis_hamilton_pilot,options);
        b11 =BitmapFactory.decodeResource(this.getResources(), R.drawable.marcus_ericsson_pilot,options);
        b12 =BitmapFactory.decodeResource(this.getResources(), R.drawable.max_verstappen_pilot,options);
        b13 =BitmapFactory.decodeResource(this.getResources(), R.drawable.nico_hulkenberg_pilot,options);
        b14 =BitmapFactory.decodeResource(this.getResources(), R.drawable.nico_rosberg_pilot,options);
        b15 =BitmapFactory.decodeResource(this.getResources(), R.drawable.pastor_maldonado_pilot,options);
        b16 =BitmapFactory.decodeResource(this.getResources(), R.drawable.romain_grosjean_pilot,options);
        b17 =BitmapFactory.decodeResource(this.getResources(), R.drawable.sebastian_vettel_pilot,options);
        b18 =BitmapFactory.decodeResource(this.getResources(), R.drawable.sergio_perez_pilot,options);
        b19 =BitmapFactory.decodeResource(this.getResources(), R.drawable.valtteri_bottas_pilot,options);
        b20 =BitmapFactory.decodeResource(this.getResources(), R.drawable.will_stevens_pilot,options);


        Piloto p1 = new Piloto(0, "Alexander Rossi", b ,    "53",       "MARUSSIA",         "Estados Unidos",       "09/25/1991",        "N/A",  "2",    "N/A");
        Piloto p2 = new Piloto(1, "Carlos Sainz",    b2,    "55",       "TORO ROSSO",       "España",               "09/01/1994",        "N/A",  "14",   "N/A");
        Piloto p3 = new Piloto(2, "Daniel Ricciardo",b3,    "3",        "RED BULL RACING",  "Australia",            "07/01/1989",        "N/A",  "83",   "10");
        Piloto p4 = new Piloto(3, "Daniil Kviat",    b4,    "26",       "RED BULL RACICNG", "Rusia",                "04/26/1994",        "N/A",  "33",   "1");
        Piloto p5 = new Piloto(4, "Felipe Massa",    b5,    "19",       "WILLIAMS",         "Brasil",               "04/25/1981",        "N/A",  "225",  "41");
        Piloto p6 = new Piloto(5, "Felipe Nasr",     b6,    "12",       "SAUBER",           "Brasil",               "08/21/1992",        "N/A",  "14",   "N/A");
        Piloto p7 = new Piloto(6, "Fernando Alonso", b7,    "14",       "MCLAREN",          "España",               "07/29/1981",        "2",    "249",  "97");
        Piloto p8 = new Piloto(7, "Jenson Button",   b8,    "22",       "MCLAREN",          "Gran Bretaña",         "01/19/1980",        "1",    "282",  "51");
        Piloto p9 = new Piloto(8, "Kimi Räikkönen",  b9,    "7",        "FERRARI",          "Finlandia",            "10/17/1979",        "1",    "227",  "79");
        Piloto p10 = new Piloto(9, "Lewis Hamilton", b10,    "44",       "MERCEDES",         "Gran Bretaña",         "01/07/1985",        "2",    "162",  "82");
        Piloto p11 = new Piloto(10, "Marcus Ericsson",b11,   "9",        "SAUBER",           "Suecia",               "09/02/1990",        "N/A",  "30",   "N/A");
        Piloto p12 = new Piloto(11, "Max Verstappen", b12,   "33",       "TORO ROSSO",       "Pasíses Bajos",        "09/30/1997",        "N/A",  "14",   "N/A");
        Piloto p13 = new Piloto(12, "Nico Hülkenberg",b13,   "27",       "FORCE INDIA",      "Alemania",             "08/19/1987",        "N/A",  "91",   "N/A");
        Piloto p14 = new Piloto(13, "Nico Rosberg",   b14,   "6",        "MERCEDES",         "Alemania",             "06/27/1985",        "N/A",  "180",  "37");
        Piloto p15 = new Piloto(14, "Pastor Maldonado",b15,  "13",       "LOTUS",            "Venezuela",            "03/10/1985",        "N/A",  "91",   "1");
        Piloto p16 = new Piloto(15, "Romain Grosjean", b16,  "8",        "LOTUS",            "Francia",              "04/17/1986",        "N/A",  "78",   "10");
        Piloto p17 = new Piloto(16, "Sebastian Vettel",b17,  "5",        "FERRARI",          "Alemania",             "07/03/1987",        "4",    "153",  "76");
        Piloto p18 = new Piloto(17, "Sergio Pérez",b18,   "11",       "FORCE INDIA",      "México",               "01/26/1990",        "N/A",  "90",   "4");
        Piloto p19 = new Piloto(18, "Valtteri Bottas",b19,   "77",       "WILLIAMS",         "Finlandia",            "08/28/1989",        "N/A",  "52",   "7");
        Piloto p20 = new Piloto(19, "Will Stevens",  b20,   "28",       "MARUSSIA",         "Gran Bretaña",         "06/28/1991",        "N/A",  "14", "N/A");

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

        adapter = new PilotosAdapter(pilotos,getActivity().getApplicationContext());

        //Por si quieren configurar algom como Grill solo cambian la linea de arriba por esta:
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getActivity().getApplicationContext(), R.dimen.item_offset);
        recyclerView.addItemDecoration(itemDecoration);
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
