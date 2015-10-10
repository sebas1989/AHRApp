package autodromo.punkmkt.com.ahrapp;

/**
 * Created by sebastianmendezgiron on 02/10/15.
 */
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.parse.ParseAnalytics;
import com.parse.ParsePushBroadcastReceiver;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by germanpunk on 13/09/15.
 */

public class MyPushBroadcastReceiver extends ParsePushBroadcastReceiver {

    @Override
    public void onPushOpen(Context context, Intent intent) {

        //To track "App Opens"
        ParseAnalytics.trackAppOpenedInBackground(intent);

        String pushTitle="";
        try {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                String jsonData = extras.getString("com.parse.Data");
                String Channel = extras.getString("com.parse.Channel");


                JSONObject jsonDataFinal;
                jsonDataFinal = new JSONObject(jsonData);
                String pushContent = jsonDataFinal.getString("alert");
                String tipo = jsonDataFinal.getString("tipo");

                if (Channel!=""){
                    if("notificaciones".equals(Channel)){
                        if(tipo.equals("noticia")){
                            String noticia = jsonDataFinal.getString("noticia");
                            Intent i = new Intent(context, BaseActivity.class);
                            i.putExtras(intent.getExtras());
                            i.putExtra("fragmento", "noticias");
                            i.putExtra("id", noticia);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(i);
                        }
                        else if (tipo.equals("premio")){
                            String premio = jsonDataFinal.getString("premio");
                            Intent i = new Intent(context, BaseActivity.class);
                            i.putExtras(intent.getExtras());
                            i.putExtra("fragmento", "premios");
                            i.putExtra("id", premio);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(i);
                        }
                        else{
                            IniciarMainActivity(context, intent);
                        }

                    }else{
                        IniciarMainActivity(context, intent);
                    }
                }
                else{
                    IniciarMainActivity(context, intent);
                }
            }
        } catch (JSONException e) {
            IniciarMainActivity(context, intent);
            e.printStackTrace();
        }
    }

    public void IniciarMainActivity(Context context, Intent intent){
        Intent i = new Intent(context, BaseActivity.class);
        i.putExtras(intent.getExtras());
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
