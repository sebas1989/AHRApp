package autodromo.punkmkt.com.ahrapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.Toast;

import autodromo.punkmkt.com.ahrapp.adapters.ConfigurationPageAdapter;

/**
 * Created by sebastianmendezgiron on 28/09/15.
 */
public class ConfiguracionActivity extends Activity {//} implements CompoundButton.OnCheckedChangeListener{

    public SwitchCompat mSwitchGPS = null;

    public SwitchCompat mSwitchGPS2 = null;
    public SwitchCompat mSwitchGP3S = null;
    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_configuration_activity);

        ConfigurationPageAdapter pageAdapter = new ConfigurationPageAdapter();
        ViewPager myPager = (ViewPager) findViewById(R.id.mytwopanelpager);
        myPager.setAdapter(pageAdapter);
        myPager.setCurrentItem(0);

        mSwitchGPS = (SwitchCompat) findViewById(R.id.turnGPSOn);

        //mSwitchGPS = (Switch) findViewById(R.id.turnGPSOn);
        //mSwitchGPS.setOnCheckedChangeListener(this);


        //mSwitchGPS.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) ConfiguracionActivity.this);

        /*mSwitchGPS = (Switch) findViewById(R.id.turnGPSOn);
        mSwitchGPS.setOnCheckedChangeListener(new OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton sw, boolean s) {

            }
        });
        try {
            if (mSwitchGPS != null) {
                mSwitchGPS.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        // TODO Auto-generated method stub
                        //String state="OFF";
                        if(isChecked){

                            Toast.makeText(getApplicationContext(), "Switch 1 is " , Toast.LENGTH_LONG).show();
                        }

                    }
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }*/

    }
    /*
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            startActivityForResult(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS), 100);
        } else {
            startActivityForResult(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS), 100);
        }
    }*/

}