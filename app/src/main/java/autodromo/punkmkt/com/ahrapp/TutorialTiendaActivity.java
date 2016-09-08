package autodromo.punkmkt.com.ahrapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import autodromo.punkmkt.com.ahrapp.adapters.TiendaPageAdapter;
import autodromo.punkmkt.com.ahrapp.fragments.TiendaFragment;
import autodromo.punkmkt.com.ahrapp.fragments.TiendaFragmentProducts;

/**
 * Created by sebastianmendezgiron on 05/08/16.
 */

public class TutorialTiendaActivity extends AppCompatActivity {

    private ViewPager myPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_configuration_activity);
        TiendaPageAdapter pageAdapter = new TiendaPageAdapter(getResources(),this);
        myPager = (ViewPager) findViewById(R.id.mytwopanelpager);
        myPager.setAdapter(pageAdapter);
        myPager.setCurrentItem(0);

    }

    private int getItem(int i) {
        return myPager.getCurrentItem() + i;
    }

    public void NextViewPager(View v){
        myPager.setCurrentItem(getItem(+1),true);
    }

    public void BackViewPager(View v){
        myPager.setCurrentItem(getItem(-1), true);
    }

    public void EndViewPager(View v){
        Intent myIntent = new Intent(this, BaseActivity.class);
        myIntent.putExtra("fragmento","tiendaRestaurantes");
        this.startActivity(myIntent);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        unbindDrawables(findViewById(R.id.container));
        System.gc();
    }
    public void unbindDrawables(View view) {//pass your parent view here
        try {
            if (view.getBackground() != null)
                view.getBackground().setCallback(null);

            if (view instanceof ImageView) {
                ImageView imageView = (ImageView) view;
                imageView.setImageBitmap(null);
            } else if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++)
                    unbindDrawables(viewGroup.getChildAt(i));

                if (!(view instanceof AdapterView))
                    viewGroup.removeAllViews();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
