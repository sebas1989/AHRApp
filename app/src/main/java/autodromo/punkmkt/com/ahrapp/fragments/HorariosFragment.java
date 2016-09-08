package autodromo.punkmkt.com.ahrapp.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
//import com.facebook.common.util.UriUtil;
//import com.facebook.drawee.view.SimpleDraweeView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

import autodromo.punkmkt.com.ahrapp.BaseActivity;
import autodromo.punkmkt.com.ahrapp.R;
import autodromo.punkmkt.com.ahrapp.models.DiaCarrera;
import autodromo.punkmkt.com.ahrapp.models.EtapaDiaCarrera;
import autodromo.punkmkt.com.ahrapp.utils.AuthRequest;
import autodromo.punkmkt.com.ahrapp.MyVolleySingleton;

/**
 * Created by sebastianmendezgiron on 29/09/15.
 */
public class HorariosFragment extends Fragment {


    TableLayout tabla_informacion;
    private String AHZ_HORARIOS_JSON_API_URL = "http://104.236.3.158/api/horariosnuevos/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_horarios,container,false);
        return v;

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Tracker tracker = ((MyVolleySingleton) getActivity().getApplication()).getTracker(MyVolleySingleton.TrackerName.APP_TRACKER);
        tracker.setScreenName(getString(R.string.menu_horarios));
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
        ImageView mImageView = (ImageView) getActivity().findViewById(R.id.imageView);
        ((BaseActivity) getActivity()).loadBitmap(R.drawable.horarios, mImageView,375,137);
        //Uri uri = new Uri.Builder()
               // .scheme(UriUtil.LOCAL_RESOURCE_SCHEME) // "res"
               // .path(String.valueOf(R.drawable.horarios))
               // .build();
        //SimpleDraweeView draweeView = (SimpleDraweeView) getActivity().findViewById(R.id.imageView);
        //draweeView.setImageURI(uri);
        /*Uri uri = new Uri.Builder()
                .scheme(UriUtil.LOCAL_RESOURCE_SCHEME) // "res"
                .path(String.valueOf(R.drawable.horarios))
                .build();
        SimpleDraweeView draweeView = (SimpleDraweeView) getActivity().findViewById(R.id.imageView);
        draweeView.setImageURI(uri);*/

        tabla_informacion = (TableLayout) getActivity().findViewById(R.id.tabla_informacion);
        Cache mCache = MyVolleySingleton.getInstance().getRequestQueue().getCache();
        Cache.Entry mEntry = mCache.get(AHZ_HORARIOS_JSON_API_URL);

            StringRequest request = new AuthRequest(getActivity().getApplicationContext(),Request.Method.GET, AHZ_HORARIOS_JSON_API_URL, "utf-8", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject object = new JSONObject(response);
                        JSONArray object2 = object.getJSONArray("data");

                        for (int count = 0; count < object2.length(); count++) {
                            JSONObject anEntry = object2.getJSONObject(count);
                            DiaCarrera dia = new DiaCarrera();
                            dia.setId(Integer.parseInt(anEntry.optString("id")));
                            dia.setNombre(anEntry.optString("nombre"));
                            CreateTitleRow(dia);
                            JSONArray tipoetapasdiascarrera = anEntry.getJSONArray("tipoetapadiacarrera");
                            for(int count2 = 0; count2 < tipoetapasdiascarrera.length();count2++){
                                JSONObject anSecondEntry = tipoetapasdiascarrera.getJSONObject(count2);
                                String titletipodiacarrera = anSecondEntry.optString("nombre");
                                //if(!titletipodiacarrera.equals("Carreras")){
                                    CreateTitleTipoDiaCarrera(titletipodiacarrera);
                                //}

                                JSONArray etapa_dia_carrera_set = anSecondEntry.getJSONArray("etapa_dia_carrera_set");
                                ArrayList<EtapaDiaCarrera> etapasdiascarrera = new ArrayList<EtapaDiaCarrera>();
                                for (int count3 = 0; count3 < etapa_dia_carrera_set.length(); count3++) {
                                    JSONObject anThirdEntry = etapa_dia_carrera_set.getJSONObject(count3);
                                    EtapaDiaCarrera etapadiacarrera = new EtapaDiaCarrera();
                                    etapadiacarrera.setId(Integer.parseInt(anThirdEntry.optString("id")));
                                    etapadiacarrera.setNombre(anThirdEntry.optString("nombre"));
                                    //etapadiacarrera.setDescripcion(anSecondEntry.optString("descripcion"));
                                    etapadiacarrera.setHora_inicio(anThirdEntry.optString("hora_inicio"));
                                    etapadiacarrera.setHora_fin(anThirdEntry.optString("hora_fin"));
                                    etapadiacarrera.setZona(anThirdEntry.optString("zona"));
                                    etapasdiascarrera.add(etapadiacarrera);
                                }
                                CreateContentRow(etapasdiascarrera);
                            }
                            //endfor
                        }

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

    //}

    public void CreateTitleRow(DiaCarrera dia){
        LayoutInflater inflater = (LayoutInflater) getActivity().getApplicationContext().getSystemService(getActivity().getApplicationContext().LAYOUT_INFLATER_SERVICE );
        //LayoutInflater inflater = LayoutInflater.from(getActivity().getApplicationContext());
        TableRow row_title = (TableRow) inflater.inflate(R.layout.title_diahorario, null);
        ((TextView)row_title.findViewById(R.id.dia)).setText(dia.getNombre());
        tabla_informacion.addView(row_title);
    }
    public void CreateContentRow(ArrayList<EtapaDiaCarrera> etapasdiascarrera){
        for(int count=0; count<etapasdiascarrera.size();count++){
            EtapaDiaCarrera etapadiacarrera = etapasdiascarrera.get(count);
            LayoutInflater inflater = (LayoutInflater) getActivity().getApplicationContext().getSystemService(getActivity().getApplicationContext().LAYOUT_INFLATER_SERVICE );
            TableRow row_pos = (TableRow)inflater.inflate(R.layout.row_diahorario, null);
            ((TextView)row_pos.findViewById(R.id.nombre)).setText(etapadiacarrera.getNombre());
            //((TextView)row_pos.findViewById(R.id.contenido_descripcion)).setText(etapadiacarrera.getDescripcion());
            ((TextView)row_pos.findViewById(R.id.contenido_hora_inicio)).setText(etapadiacarrera.getHora_inicio());
            ((TextView)row_pos.findViewById(R.id.contenido_hora_fin)).setText(etapadiacarrera.getHora_fin());
            ((TextView)row_pos.findViewById(R.id.contenido_zona)).setText(etapadiacarrera.getZona());
            tabla_informacion.addView(row_pos);
        }
    }

    public void CreateTitleTipoDiaCarrera(String title){
        LayoutInflater inflater = (LayoutInflater) getActivity().getApplicationContext().getSystemService(getActivity().getApplicationContext().LAYOUT_INFLATER_SERVICE );
        TableRow row_title = (TableRow) inflater.inflate(R.layout.title_tipodiahorario, null);
        ((TextView)row_title.findViewById(R.id.dia)).setText(title);
        tabla_informacion.addView(row_title);
    }

}
