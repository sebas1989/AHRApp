package autodromo.punkmkt.com.ahrapp.fragments;


import android.content.Intent;
import android.os.CountDownTimer;

import android.os.Bundle;

import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.Button;
import android.widget.ImageButton;
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
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;

import autodromo.punkmkt.com.ahrapp.SingleNewDetailActivity;
import autodromo.punkmkt.com.ahrapp.utils.AuthRequest;
import autodromo.punkmkt.com.ahrapp.MyVolleySingleton;
import autodromo.punkmkt.com.ahrapp.utils.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;

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
    public String img1, img2, img3, id, id2, id3, nom1, nom2, nom3, subtitulo1, subtitulo2, subtitulo3, desc1, desc2, desc3;
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
        IniciarPassion();
        IniciarSocialHub();
        getLatestNews();
        IniciarRegistro();

        RelativeLayout relativeFirstNew =(RelativeLayout) getActivity().findViewById(R.id.container_main_view);
        relativeFirstNew.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent newEntryIntent = new Intent(getActivity(), SingleNewDetailActivity.class);
                newEntryIntent.putExtra("id", id);
                startActivity(newEntryIntent);
            }
        });

        RelativeLayout relativeSecondNew =(RelativeLayout) getActivity().findViewById(R.id.one_two);
        relativeSecondNew.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent newEntryIntent = new Intent(getActivity(), SingleNewDetailActivity.class);
                newEntryIntent.putExtra("id", id2);
                startActivity(newEntryIntent);
            }
        });

        RelativeLayout relativeThirdNew =(RelativeLayout) getActivity().findViewById(R.id.one_three);
        relativeThirdNew.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                    Intent newEntryIntent = new Intent(getActivity(), SingleNewDetailActivity.class);
                    newEntryIntent.putExtra("id", id3);
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

        c = new CountDownTimer(milliDiff, 1000){
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
                }
                String fDias = getResources().getString(R.string.dias);
                String fHoras = getResources().getString(R.string.horas);
                String fMinutos = getResources().getString(R.string.minutos);
                String fSegundos = getResources().getString(R.string.segundos);
                mLabels.setText(fDias+"\u00A0"+"\u00A0 \u00A0 \u00A0"+fHoras+"\u00A0"+"\u00A0 \u00A0"+fMinutos+"\u00A0"+"\u00A0 \u00A0"+fSegundos);
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
        super.onPause();
        c.cancel();

    }
    public void onResume(){
        super.onResume();
        configuracionEvento();
    }

    public void IniciarPassion(){

        ImageButton button = (ImageButton) getActivity().findViewById(R.id.quickLinkToPassion);
        // add button listener
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Fragment fP = new PassionFragment();
                android.support.v4.app.FragmentTransaction ftP = getFragmentManager().beginTransaction();
                ftP.replace(R.id.frame, fP); // f1_container is your FrameLayout container
                ftP.setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ftP.addToBackStack(null);
                ftP.commit();
            }
        });
    }
    public void IniciarSocialHub(){

        ImageButton button = (ImageButton) getActivity().findViewById(R.id.quickLinkToSocialHub);

        // add button listener
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Fragment fSH = new SocialHubFragment();
                android.support.v4.app.FragmentTransaction ftSH = getFragmentManager().beginTransaction();
                ftSH.replace(R.id.frame, fSH); // f1_container is your FrameLayout container
                ftSH.setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ftSH.addToBackStack(null);
                ftSH.commit();
            }
        });
    }



    public void IniciarRegistro(){
        ImageButton button = (ImageButton) getActivity().findViewById(R.id.quickLinkToRegistration);
        // add button listener
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Fragment fFL = new FacebookLogIn();
                android.support.v4.app.FragmentTransaction ftFL = getFragmentManager().beginTransaction();
                ftFL.replace(R.id.frame, fFL); // f1_container is your FrameLayout container
                ftFL.setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ftFL.addToBackStack(null);
                ftFL.commit();
            }
        });
    }

    public void getLatestNews(){

        if(NetworkUtils.haveNetworkConnection(getActivity())) {

            StringRequest request = new AuthRequest(Request.Method.GET, AHZ_LATEST_THREE_NEWS_ENTRIES, "utf-8", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray newsArray = new JSONArray(response);

                        id = newsArray.getJSONObject(0).optString("id").toString();
                        nom1 = newsArray.getJSONObject(0).optString("titulo").toString();
                        subtitulo1 = newsArray.getJSONObject(0).optString("subtitulo").toString();
                        desc1 = newsArray.getJSONObject(0).optString("descripcion").toString();
                        img1 = newsArray.getJSONObject(0).optString("imagen").toString();
                        tituloNoticia1 = (TextView) getActivity().findViewById(R.id.tituloNoticia1);
                        tituloNoticia1.setText(nom1);

                        last = (NetworkImageView) getActivity().findViewById(R.id.netork_imageView_last);
                        last.setImageUrl(newsArray.getJSONObject(0).optString("imagen"), imageLoader);

                        id2 = newsArray.getJSONObject(1).optString("id").toString();
                        nom2 = newsArray.getJSONObject(1).optString("titulo").toString();
                        subtitulo2 = newsArray.getJSONObject(1).optString("subtitulo").toString();
                        desc2 = newsArray.getJSONObject(1).optString("descripcion").toString();
                        img2 = newsArray.getJSONObject(1).optString("imagen").toString();
                        tituloNoticia2 = (TextView) getActivity().findViewById(R.id.tituloNoticia2);
                        tituloNoticia2.setText(nom2);

                        last2 = (NetworkImageView) getActivity().findViewById(R.id.netork_imageView);
                        last2.setImageUrl(newsArray.getJSONObject(1).optString("imagen_cuadrada"), imageLoader);

                        id3 = newsArray.getJSONObject(2).optString("id").toString();
                        nom3 = newsArray.getJSONObject(2).optString("titulo").toString();
                        subtitulo3 = newsArray.getJSONObject(2).optString("subtitulo").toString();
                        desc3 = newsArray.getJSONObject(2).optString("descripcion").toString();
                        img3 = newsArray.getJSONObject(2).optString("imagen").toString();
                        tituloNoticia3 = (TextView) getActivity().findViewById(R.id.tituloNoticia3);
                        tituloNoticia3.setText(nom3);

                        last3 = (NetworkImageView) getActivity().findViewById(R.id.netork_imageView3);
                        last3.setImageUrl(newsArray.getJSONObject(2).optString("imagen_cuadrada"), imageLoader);

                    } catch (JSONException e) {
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            MyVolleySingleton.getInstance().addToRequestQueue(request);
        }else{

        }

    }


}