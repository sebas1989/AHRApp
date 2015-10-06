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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import autodromo.punkmkt.com.ahrapp.R;
import autodromo.punkmkt.com.ahrapp.models.DiaCarrera;
import autodromo.punkmkt.com.ahrapp.models.EtapaDiaCarrera;
import autodromo.punkmkt.com.ahrapp.utils.AuthRequest;
import autodromo.punkmkt.com.ahrapp.MyVolleySingleton;

/**
 * Created by sebastianmendezgiron on 29/09/15.
 */
public class HorariosFragment extends Fragment {

    ImageView notificacion_practica1;
    ImageView notificacion_practica2;
    ImageView notificacion_practica3;
    ImageView notificacion_calificacion;
    ImageView notificacion_premio;
    TableLayout tabla_informacion;
    private String AHZ_HORARIOS_JSON_API_URL = "http://104.236.3.158/api/horarios/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_horarios,container,false);
        return v;

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tabla_informacion = (TableLayout) getActivity().findViewById(R.id.tabla_informacion);

        StringRequest request = new AuthRequest(Request.Method.GET, AHZ_HORARIOS_JSON_API_URL,  "utf-8", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray object2 = object.getJSONArray("data");
                    //JSONArray etapa_set = object2.getJSONArray("etapa_dia_carrera_set");
                    for (int count = 0; count < object2.length(); count++) {
                        JSONObject anEntry = object2.getJSONObject(count);
                        DiaCarrera dia = new DiaCarrera();
                        dia.setId(Integer.parseInt(anEntry.optString("id")));
                        dia.setNombre(anEntry.optString("nombre"));
                        //Log.d("volley", dia.getNombre());  //Etapas
                        CreateTitleRow(dia);
                        JSONArray etapa_dia_carrera_set = anEntry.getJSONArray("etapa_dia_carrera_set");
                        ArrayList<EtapaDiaCarrera> etapasdiascarrera = new ArrayList<EtapaDiaCarrera>();
                        for (int count2 = 0; count2 < etapa_dia_carrera_set.length(); count2++) {
                            JSONObject anSecondEntry = etapa_dia_carrera_set.getJSONObject(count2);
                            EtapaDiaCarrera etapadiacarrera  = new EtapaDiaCarrera();
                            etapadiacarrera.setId(Integer.parseInt(anSecondEntry.optString("id")));
                            etapadiacarrera.setNombre(anSecondEntry.optString("nombre"));
                            //etapadiacarrera.setDescripcion(anSecondEntry.optString("descripcion"));
                            etapadiacarrera.setHora_inicio(anSecondEntry.optString("hora_inicio"));
                            etapadiacarrera.setHora_fin(anSecondEntry.optString("hora_fin"));
                            etapadiacarrera.setZona(anSecondEntry.optString("zona"));
                            etapasdiascarrera.add(etapadiacarrera);

                        }
                        CreateContentRow(etapasdiascarrera);
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

    public void CreateTitleRow(DiaCarrera dia){
        TableRow row_title = (TableRow) LayoutInflater.from(getActivity()).inflate(R.layout.title_diahorario, null);
        ((TextView)row_title.findViewById(R.id.dia)).setText(dia.getNombre());
        tabla_informacion.addView(row_title);
    }
    public void CreateContentRow(ArrayList<EtapaDiaCarrera> etapasdiascarrera){
        for(int count=0; count<etapasdiascarrera.size();count++){
            EtapaDiaCarrera etapadiacarrera = etapasdiascarrera.get(count);
            TableRow row_pos = (TableRow) LayoutInflater.from(getActivity()).inflate(R.layout.row_diahorario, null);
            ((TextView)row_pos.findViewById(R.id.nombre)).setText(etapadiacarrera.getNombre());
            //((TextView)row_pos.findViewById(R.id.contenido_descripcion)).setText(etapadiacarrera.getDescripcion());
            ((TextView)row_pos.findViewById(R.id.contenido_hora_inicio)).setText(etapadiacarrera.getHora_inicio());
            ((TextView)row_pos.findViewById(R.id.contenido_hora_fin)).setText(etapadiacarrera.getHora_fin());
            ((TextView)row_pos.findViewById(R.id.contenido_zona)).setText(etapadiacarrera.getZona());
            tabla_informacion.addView(row_pos);
        }
    }

}
