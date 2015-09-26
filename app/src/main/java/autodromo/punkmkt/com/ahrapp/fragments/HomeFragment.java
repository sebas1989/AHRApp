package autodromo.punkmkt.com.ahrapp.fragments;


import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.Intent;
import android.os.CountDownTimer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.text.format.Time;
import java.lang.String;

import autodromo.punkmkt.com.ahrapp.R;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;

import autodromo.punkmkt.com.ahrapp.SingleNewDetailActivity;
import autodromo.punkmkt.com.ahrapp.utils.AuthRequest;
import autodromo.punkmkt.com.ahrapp.utils.MyVolleySingleton;
import autodromo.punkmkt.com.ahrapp.utils.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import autodromo.punkmkt.com.ahrapp.R;
/**
 * Created by sebastianmendezgiron on 19/09/15.
 */
public class HomeFragment extends Fragment {

    //private static final String TAG = MainActivity.class.getSimpleName();
    protected TextView mTextView;
    protected TextView mCurrWeather;
    protected TextView mStatus;
    protected TextView mLabels;
    CountDownTimer c;
    //private ArrayList<Weather> courses = new ArrayList<Weather>();
    protected final String CURRENT_WEAHTER_STATUS = "http://api.openweathermap.org/data/2.5/weather?id=3530597&units=metric&lang=es&APPID=0b6b95e193ac67ee6045b6f766bc6cdb";

    Time fechaEvento = new Time(Time.getCurrentTimezone());
    int hora = 13;
    int minuto = 00;
    int segundo = 00;
    int dia = 01;
    // month is zero based...7 == August
    int mes = 10;
    int ano;

    private int diasfaltantes;
    private int horasFaltantes;
    private int minutosFaltantes;
    private int segundosFaltantes;

    private String AHZ_LATEST_THREE_NEWS_ENTRIES = "http://104.236.3.158/api/noticias/ultimas_noticias/3/";
    public TextView tituloNoticia1, tituloNoticia2, tituloNoticia3;
    private ImageLoader imageLoader = MyVolleySingleton.getInstance().getImageLoader();

    NetworkImageView last,last2,last3;
    public String img1, img2, img3, nom1, nom2, nom3, subtitulo1, subtitulo2, subtitulo3, desc1, desc2, desc3;
    private Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_home,container,false);
        return v;

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        configuracionEvento();
        getCurrentWeather();
        getLatestNews();
        termsAndConditions();
        privacyPolicy();

        RelativeLayout relativeFirstNew =(RelativeLayout) getActivity().findViewById(R.id.container_main_view);
        relativeFirstNew.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent newEntryIntent = new Intent(getActivity(), SingleNewDetailActivity.class);
                newEntryIntent.putExtra("nombre", nom1);
                newEntryIntent.putExtra("subtitulo", subtitulo1);
                newEntryIntent.putExtra("descripcion", desc1);
                newEntryIntent.putExtra("imagen", img1);
                startActivity(newEntryIntent);
            }
        });

        RelativeLayout relativeSecondNew =(RelativeLayout) getActivity().findViewById(R.id.one_two);
        relativeSecondNew.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent newEntryIntent = new Intent(getActivity(), SingleNewDetailActivity.class);
                newEntryIntent.putExtra("nombre", nom2);
                newEntryIntent.putExtra("subtitulo", subtitulo2);
                newEntryIntent.putExtra("descripcion", desc2);
                newEntryIntent.putExtra("imagen", img2);
                startActivity(newEntryIntent);
            }
        });

        RelativeLayout relativeThirdNew =(RelativeLayout) getActivity().findViewById(R.id.one_three);
        relativeThirdNew.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                    Intent newEntryIntent = new Intent(getActivity(), SingleNewDetailActivity.class);
                    newEntryIntent.putExtra("nombre", nom3);
                    newEntryIntent.putExtra("subtitulo", subtitulo3);
                    newEntryIntent.putExtra("descripcion", desc3);
                    newEntryIntent.putExtra("imagen", img3);
                    startActivity(newEntryIntent);
            }
        });

    }


    private void configuracionEvento(){

        this.fechaEvento.setToNow();
        this.ano = fechaEvento.year;

        fechaEvento.set(segundo, minuto, hora, dia, mes, ano);
        fechaEvento.normalize(true);
        long confMillis = fechaEvento.toMillis(true);

        Time nowTime = new Time(Time.getCurrentTimezone());
        nowTime.setToNow();
        nowTime.normalize(true);
        long nowMillis = nowTime.toMillis(true);

        long milliDiff = confMillis - nowMillis;

        c =new CountDownTimer(milliDiff, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                segundosFaltantes = (int) (millisUntilFinished / 1000) % 60 ;
                minutosFaltantes = (int) ((millisUntilFinished / (1000*60)) % 60);
                horasFaltantes   = (int) ((millisUntilFinished / (1000*60*60)) % 24);
                diasfaltantes = (int) (millisUntilFinished/(1000*60*60*24));

                try {
                mTextView = (TextView) getActivity().findViewById(R.id.counter);//findViewById(R.id.counter);
                mTextView.setText(diasfaltantes + " : " + horasFaltantes + " : " + minutosFaltantes + " : " +segundosFaltantes);
                mLabels = (TextView) getActivity().findViewById(R.id.definicion);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String fDias = getResources().getString(R.string.dias);
                String fHoras = getResources().getString(R.string.horas);
                String fMinutos = getResources().getString(R.string.minutos);
                String fSegundos = getResources().getString(R.string.segundos);
                mLabels.setText("   " + fDias + "    " + fHoras + "    " + fMinutos + "    " + fSegundos);
            }
            @Override
            public void onFinish() {
                Context context = getActivity();
                CharSequence text = "Listo";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }

        }.start();

    }

    @Override
    public void onPause() {
        Log.i("pausa", getClass().getSimpleName() + ":onPause()");

        super.onPause();
        c.cancel();

    }

    private void getCurrentWeather(){
        if(NetworkUtils.haveNetworkConnection(getActivity())) {

            JsonObjectRequest jsonRequest = new JsonObjectRequest
                    (Request.Method.GET, CURRENT_WEAHTER_STATUS, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {

                                JSONArray JSONWeatherArray = response.getJSONArray("weather");
                                String descripcion = JSONWeatherArray.getJSONObject(0).optString("description").toString();
                                String iconCode = JSONWeatherArray.getJSONObject(0).optString("icon").toString();

                                RelativeLayout weatherRelative = (RelativeLayout) getActivity().findViewById(R.id.conWeather);

                                if (iconCode.equals("01d") || iconCode.equals("01n")){
                                    weatherRelative.setBackgroundResource(R.drawable.clear_weather_f1);
                                }
                                else if (iconCode.equals("02d") || iconCode.equals("02n")){
                                    weatherRelative.setBackgroundResource(R.drawable.few_clouds_weather_f1);
                                }
                                else if (iconCode.equals("04d") || iconCode.equals("04n")){
                                    weatherRelative.setBackgroundResource(R.drawable.broken_clouds_weather_f1);
                                }
                                else if (iconCode.equals("50d") || iconCode.equals("50n")){
                                    weatherRelative.setBackgroundResource(R.drawable.mist_weather_f1);
                                }
                                else if (iconCode.equals("10d") || iconCode.equals("10n")){
                                    weatherRelative.setBackgroundResource(R.drawable.cloudy_rain_weather_f1);
                                }
                                else if (iconCode.equals("03d") || iconCode.equals("03n")){
                                    weatherRelative.setBackgroundResource(R.drawable.cloudy_weather_f1);
                                }
                                else if (iconCode.equals("13d") || iconCode.equals("13n")){
                                    weatherRelative.setBackgroundResource(R.drawable.snow_weather_f1);
                                }
                                else if (iconCode.equals("09d") || iconCode.equals("09n")){
                                    weatherRelative.setBackgroundResource(R.drawable.sunny_rain_weather_f1);
                                }
                                else if (iconCode.equals("11d") || iconCode.equals("11n")){
                                    weatherRelative.setBackgroundResource(R.drawable.thunder_weather_f1);
                                }

                                JSONObject currTemp = response.getJSONObject("main");
                                String temp = currTemp.optString("temp").toString();
                                String cleanTemp = (temp.substring(0, temp.indexOf("."))+"º");

                                mStatus = (TextView) getActivity().findViewById(R.id.weatherStatus);
                                mCurrWeather = (TextView) getActivity().findViewById(R.id.currWeather);

                                mCurrWeather.setText(cleanTemp);
                                mStatus.setText(descripcion);

                            }catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });

            MyVolleySingleton.getInstance().addToRequestQueue(jsonRequest);
        }else{
            Toast.makeText(getActivity(), getResources().getString(R.string.minutos), Toast.LENGTH_SHORT).show();
        }
    }

    public void getLatestNews(){

        if(NetworkUtils.haveNetworkConnection(getActivity())) {

            StringRequest request = new AuthRequest(Request.Method.GET, AHZ_LATEST_THREE_NEWS_ENTRIES, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray newsArray = new JSONArray(response);
                        //Log.d(":o", response);
                        nom1 = newsArray.getJSONObject(0).optString("titulo").toString();
                        subtitulo1 = newsArray.getJSONObject(0).optString("subtitulo").toString();
                        desc1 = newsArray.getJSONObject(0).optString("descripcion").toString();
                        img1 = newsArray.getJSONObject(0).optString("imagen_rectangular").toString();
                        tituloNoticia1 = (TextView) getActivity().findViewById(R.id.tituloNoticia1);
                        tituloNoticia1.setText(nom1);

                        last = (NetworkImageView) getActivity().findViewById(R.id.netork_imageView_last);
                        last.setImageUrl(newsArray.getJSONObject(0).optString("imagen_rectangular"), imageLoader);

                        nom2 = newsArray.getJSONObject(1).optString("titulo").toString();
                        subtitulo2 = newsArray.getJSONObject(1).optString("subtitulo").toString();
                        desc2 = newsArray.getJSONObject(1).optString("descripcion").toString();
                        img2 = newsArray.getJSONObject(1).optString("imagen_rectangular").toString();
                        tituloNoticia2 = (TextView) getActivity().findViewById(R.id.tituloNoticia2);
                        tituloNoticia2.setText(nom2);

                        last2 = (NetworkImageView) getActivity().findViewById(R.id.netork_imageView);
                        last2.setImageUrl(newsArray.getJSONObject(1).optString("imagen_cuadrada"), imageLoader);

                        nom3 = newsArray.getJSONObject(2).optString("titulo").toString();
                        subtitulo3 = newsArray.getJSONObject(2).optString("subtitulo").toString();
                        desc3 = newsArray.getJSONObject(2).optString("descripcion").toString();
                        img3 = newsArray.getJSONObject(2).optString("imagen_rectangular").toString();
                        tituloNoticia3 = (TextView) getActivity().findViewById(R.id.tituloNoticia3);
                        tituloNoticia3.setText(nom3);

                        last3 = (NetworkImageView) getActivity().findViewById(R.id.netork_imageView3);
                        last3.setImageUrl(newsArray.getJSONObject(2).optString("imagen_cuadrada"), imageLoader);

                        //Log.d(":o", response);

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
        }else{
            Toast.makeText(getActivity(), getResources().getString(R.string.minutos), Toast.LENGTH_SHORT).show();
        }

    }



    public void termsAndConditions(){

        button = (Button) getActivity().findViewById(R.id.terminos);

        // add button listener
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // custom dialog
                final Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.terms_and_conditions_activity);

                Button close = (Button) dialog.findViewById(R.id.ok);
                // if button is clicked, close the custom dialog
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }

    public void privacyPolicy(){

        button = (Button) getActivity().findViewById(R.id.politicas);

        // add button listener
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // custom dialog
                final Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.privacy_policy_activity);

                Button close = (Button) dialog.findViewById(R.id.ok);
                // if button is clicked, close the custom dialog
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }


}