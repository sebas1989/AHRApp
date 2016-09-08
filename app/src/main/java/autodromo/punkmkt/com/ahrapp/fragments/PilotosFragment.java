package autodromo.punkmkt.com.ahrapp.fragments;

/**
 * Created by sebastianmendezgiron on 25/09/15.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import autodromo.punkmkt.com.ahrapp.MyVolleySingleton;
import autodromo.punkmkt.com.ahrapp.R;
import autodromo.punkmkt.com.ahrapp.adapters.PilotosAdapter;
import autodromo.punkmkt.com.ahrapp.models.Piloto;
import autodromo.punkmkt.com.ahrapp.utils.BitmapManager;
import java.util.ArrayList;

/**
 * Created by germanpunk on 20/09/15.
 */
public class PilotosFragment extends Fragment {

    private RecyclerView.Adapter adapter;
    private LruCache<String, Bitmap> mMemoryCache;

    private ArrayList<Piloto> pilotos = new ArrayList<Piloto>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmentpiloto, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Tracker tracker = ((MyVolleySingleton) getActivity().getApplication()).getTracker(MyVolleySingleton.TrackerName.APP_TRACKER);
        tracker.setScreenName(getString(R.string.menu_pilotos));
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);


        int width = 150;
        int height = 150;

        Piloto p1 = new Piloto(0, "Alexander Rossi" ,    "53",       "MARUSSIA",         "Estados Unidos",       "09/25/1991",        "N/A",  "2",    "N/A");
        Piloto p2 = new Piloto(1, "Carlos Sainz",    "55",       "TORO ROSSO",       "España",               "09/01/1994",        "N/A",  "15",   "N/A");
        Piloto p3 = new Piloto(2, "Daniel Ricciardo",   "3",        "RED BULL RACING",  "Australia",            "07/01/1989",        "N/A",  "84",   "10");
        Piloto p4 = new Piloto(3, "Daniil Kviat",        "26",       "RED BULL RACICNG", "Rusia",                "04/26/1994",        "N/A",  "34",   "1");
        Piloto p5 = new Piloto(4, "Felipe Massa",      "19",       "WILLIAMS",         "Brasil",               "04/25/1981",        "N/A",  "226",  "41");
        Piloto p6 = new Piloto(5, "Felipe Nasr",       "12",       "SAUBER",           "Brasil",               "08/21/1992",        "N/A",  "15",   "N/A");
        Piloto p7 = new Piloto(6, "Fernando Alonso",     "14",       "MCLAREN",          "España",               "07/29/1981",        "2",    "250",  "97");
        Piloto p8 = new Piloto(7, "Jenson Button",      "22",       "MCLAREN",          "Gran Bretaña",         "01/19/1980",        "1",    "283",  "51");
        Piloto p9 = new Piloto(8, "Kimi Räikkönen",     "7",        "FERRARI",          "Finlandia",            "10/17/1979",        "1",    "228",  "79");
        Piloto p10 = new Piloto(9, "Lewis Hamilton",   "44",       "MERCEDES",         "Gran Bretaña",         "01/07/1985",        "2",    "163",  "83");
        Piloto p11 = new Piloto(10, "Marcus Ericsson",   "9",        "SAUBER",           "Suecia",               "09/02/1990",        "N/A",  "31",   "N/A");
        Piloto p12 = new Piloto(11, "Max Verstappen",  "33",       "TORO ROSSO",       "Pasíses Bajos",        "09/30/1997",        "N/A",  "15",   "N/A");
        Piloto p13 = new Piloto(12, "Nico Hülkenberg",   "27",       "FORCE INDIA",      "Alemania",             "08/19/1987",        "N/A",  "92",   "N/A");
        Piloto p14 = new Piloto(13, "Nico Rosberg",    "6",        "MERCEDES",         "Alemania",             "06/27/1985",        "N/A",  "181",  "37");
        Piloto p15 = new Piloto(14, "Pastor Maldonado",  "13",       "LOTUS",            "Venezuela",            "03/10/1985",        "N/A",  "92",   "1");
        Piloto p16 = new Piloto(15, "Romain Grosjean",   "8",        "LOTUS",            "Francia",              "04/17/1986",        "N/A",  "79",   "10");
        Piloto p17 = new Piloto(16, "Roberto Merhi",   "98",        "MARUSSIA",            "España",              "22/03/1991",        "N/A",  "12",   "N/A");
        Piloto p18 = new Piloto(17, "Sebastian Vettel",  "5",        "FERRARI",          "Alemania",             "07/03/1987",        "4",    "154",  "77");
        Piloto p19 = new Piloto(18, "Sergio Pérez",  "11",       "FORCE INDIA",      "México",               "01/26/1990",        "N/A",  "91",   "5");
        Piloto p20 = new Piloto(19, "Valtteri Bottas",   "77",       "WILLIAMS",         "Finlandia",            "08/28/1989",        "N/A",  "53",   "7");
        Piloto p21 = new Piloto(20, "Will Stevens",   "28",       "MARUSSIA",         "Gran Bretaña",         "06/28/1991",        "N/A",  "15", "N/A");

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
        pilotos.add(p21);

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
//
//
//    private void unbindDrawables(View view) {
//        if (view.getBackground() != null) {
//            view.getBackground().setCallback(null);
//        }
//        if (view instanceof ViewGroup) {
//            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
//                unbindDrawables(((ViewGroup) view).getChildAt(i));
//            }
//            ((ViewGroup) view).removeAllViews();
//        }
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        unbindDrawables(getActivity().findViewById(R.id.container_fragment));
//        System.gc();
//    }
//
//
//    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
//        if (getBitmapFromMemCache(key) == null) {
//            mMemoryCache.put(key, bitmap);
//        }
//    }
//
//    public Bitmap getBitmapFromMemCache(String key) {
//        return mMemoryCache.get(key);
//    }

}
