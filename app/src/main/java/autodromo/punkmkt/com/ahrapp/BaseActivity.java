package autodromo.punkmkt.com.ahrapp;

/**
 * Created by sebastianmendezgiron on 19/09/15.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Toast;

import autodromo.punkmkt.com.ahrapp.fragments.ContentFragment;
import autodromo.punkmkt.com.ahrapp.fragments.HomeFragment;
import autodromo.punkmkt.com.ahrapp.fragments.NewsFragment;
import autodromo.punkmkt.com.ahrapp.fragments.PassionFragment;
import autodromo.punkmkt.com.ahrapp.fragments.ResultadosFragment;

public class BaseActivity extends AppCompatActivity {

    //Defining Variables
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            Fragment f1 = new HomeFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame, f1); // f1_container is your FrameLayout container
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.addToBackStack(null);
            ft.commit();
        }




        //configuracionEvento();
        //getCurrentWeather();
        // Initializing Toolbar and setting it as the actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {


                //Checking if the item is in checked state or not, if not make it in checked state
                if(menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //Closing drawer on item click
                drawerLayout.closeDrawers();

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()){


                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.home:
                        Fragment fH = new HomeFragment();
                        FragmentTransaction ftH = getSupportFragmentManager().beginTransaction();
                        ftH.replace(R.id.frame, fH);
                        ftH.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        ftH.addToBackStack(null);
                        ftH.commit();
                        return true;

                    // For rest of the options we just show a toast on click
                    case R.id.autodromo:
                        Toast.makeText(getApplicationContext(),"autodromo",Toast.LENGTH_SHORT).show();
                        HomeFragment fragment2 = new HomeFragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction2.replace(R.id.frame, fragment2);
                        return true;
                    case R.id.horarios:

                        //Log.d("fragment","resultados");
                        Fragment f = new ResultadosFragment();
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.frame, f);
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        ft.addToBackStack(null);
                        ft.commit();
                        return true;

                    case R.id.noticias:
                        Toast.makeText(getApplicationContext(),"pasion",Toast.LENGTH_SHORT).show();
                        Fragment fN = new NewsFragment();
                        FragmentTransaction ftN = getSupportFragmentManager().beginTransaction();
                        ftN.replace(R.id.frame, fN);
                        ftN.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        ftN.addToBackStack(null);
                        ftN.commit();
                        return true;
                    case R.id.resultados:
                        Toast.makeText(getApplicationContext(),"resultados",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.pilotos:
                        Toast.makeText(getApplicationContext(),"pilotos",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.pasion:
                        Toast.makeText(getApplicationContext(),"pasion",Toast.LENGTH_SHORT).show();
                        Fragment fP = new PassionFragment();
                        FragmentTransaction ftP = getSupportFragmentManager().beginTransaction();
                        ftP.replace(R.id.frame, fP);
                        ftP.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        ftP.addToBackStack(null);
                        ftP.commit();
                        //passionContainer
                        return true;
                    case R.id.mexico:
                        Toast.makeText(getApplicationContext(),"mexico",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.social_hub:
                        Toast.makeText(getApplicationContext(),"Social hub",Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        Toast.makeText(getApplicationContext(),"otro",Toast.LENGTH_SHORT).show();
                        return true;

                }
            }
        });



        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.openDrawer, R.string.closeDrawer){

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
