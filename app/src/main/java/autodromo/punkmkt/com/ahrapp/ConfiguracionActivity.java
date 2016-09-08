package autodromo.punkmkt.com.ahrapp;

/**
 * Created by germanpunk on 02/10/15.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import autodromo.punkmkt.com.ahrapp.adapters.ConfigurationPageAdapter;


/**
 * Created by sebastianmendezgiron on 28/09/15.
 */
public class ConfiguracionActivity extends Activity{


    private ViewPager myPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_configuration_activity);
        ConfigurationPageAdapter pageAdapter = new ConfigurationPageAdapter(getResources(),this);
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
        Intent myIntent = new Intent(ConfiguracionActivity.this, BaseActivity.class);
        ConfiguracionActivity.this.startActivity(myIntent);
        finish();
    }

    static public void onCheckedChanged(Activity activity, boolean isChecked) {
        if (isChecked) {
            activity.startActivityForResult(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS), 100);
        } else {
            activity.startActivityForResult(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS), 100);
        }
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