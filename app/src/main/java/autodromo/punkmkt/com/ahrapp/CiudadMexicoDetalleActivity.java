package autodromo.punkmkt.com.ahrapp;

/**
 * Created by sebastianmendezgiron on 30/09/15.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import autodromo.punkmkt.com.ahrapp.fragments.HospedajeFragment;
import autodromo.punkmkt.com.ahrapp.fragments.LugaresFragment;
import autodromo.punkmkt.com.ahrapp.fragments.RestaurantesFragment;


public class CiudadMexicoDetalleActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciudad_mexico_detalle);

        Intent intent = getIntent();
        String fragmento = intent.getStringExtra("fragment");

        if (fragmento.equals("hospedaje")) {
            Fragment f1 = new HospedajeFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container_fragment, f1); // f1_container is your FrameLayout container
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            //ft.addToBackStack(null);
            ft.commit();
        }else if (fragmento.equals("restaurantes")){
            if (savedInstanceState == null) {
                Fragment f1 = new RestaurantesFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.container_fragment, f1); // f1_container is your FrameLayout container
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                //ft.addToBackStack(null);
                ft.commit();
            }
        }
        else if (fragmento.equals("adondeir")){
            if (savedInstanceState == null) {
                Fragment f1 = new LugaresFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.container_fragment, f1); // f1_container is your FrameLayout container
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                //ft.addToBackStack(null);
                ft.commit();
            }
        }else {

        }
    }

    public void MostrarHoteles(View v){
        Fragment f = new HospedajeFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container_fragment, f);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        //ft.addToBackStack(null);
        ft.commit();
    }
    public void MostrarRestaurantes(View v){
        Fragment f = new RestaurantesFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container_fragment, f);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        //ft.addToBackStack(null);
        ft.commit();
    }
    public void MostrarAdondeir(View v){
        Fragment f = new LugaresFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container_fragment, f);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        //ft.addToBackStack(null);
        ft.commit();
    }
}
