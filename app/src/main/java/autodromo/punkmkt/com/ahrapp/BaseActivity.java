package autodromo.punkmkt.com.ahrapp;

/**
 * Created by sebastianmendezgiron on 19/09/15.
 */
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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

import autodromo.punkmkt.com.ahrapp.fragments.AutodromoFragment;
import autodromo.punkmkt.com.ahrapp.fragments.CiudadMexicoActivity;
import autodromo.punkmkt.com.ahrapp.fragments.ContentFragment;
import autodromo.punkmkt.com.ahrapp.fragments.FacebookLogIn;
import autodromo.punkmkt.com.ahrapp.fragments.HomeFragment;
import autodromo.punkmkt.com.ahrapp.fragments.HorariosFragment;
import autodromo.punkmkt.com.ahrapp.fragments.LoginFBFragment;
import autodromo.punkmkt.com.ahrapp.fragments.NewsFragment;
import autodromo.punkmkt.com.ahrapp.fragments.PassionFragment;
import autodromo.punkmkt.com.ahrapp.fragments.PilotosFragment;
import autodromo.punkmkt.com.ahrapp.fragments.PremiosFragment;
import autodromo.punkmkt.com.ahrapp.fragments.ResultadosActivity;
import autodromo.punkmkt.com.ahrapp.fragments.ResultadosFragment;
import autodromo.punkmkt.com.ahrapp.fragments.SocialHubFragment;

public class BaseActivity extends AppCompatActivity {

    //Defining Variables
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();

        try{
            String fragmento = intent.getStringExtra("fragmento");
            if(fragmento.equals("noticias")){
                if (savedInstanceState == null) {
                    Fragment f1 = new NewsFragment();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.add(R.id.frame, f1).commit();
                }
            }
            else if(fragmento.equals("premios")){
                if (savedInstanceState == null) {
                    Fragment f1 = new PremiosFragment();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.add(R.id.frame, f1).commit();
                }
            }
        }catch (Exception e){
            if (savedInstanceState == null) {
                Fragment f1 = new HomeFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                //ft.replace(R.id.frame, f1); // f1_container is your FrameLayout container
                ft.add(R.id.frame, f1).commit();
                //ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                //ft.addToBackStack(null);
                // ft.commit();
            }
        }



        // Initializing Toolbar and setting it as the actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
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
                        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
                        Fragment fH = new HomeFragment();
                        FragmentTransaction ftH = getSupportFragmentManager().beginTransaction();
                        ftH.replace(R.id.frame, fH);
                        ftH.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        ftH.addToBackStack(null);
                        ftH.commit();
                        return true;

                    // For rest of the options we just show a toast on click
                    case R.id.autodromo:
                        getSupportActionBar().setTitle(getResources().getString(R.string.menu_autodromo));
                        Fragment fA = new AutodromoFragment();
                        FragmentTransaction ftA = getSupportFragmentManager().beginTransaction();
                        ftA.replace(R.id.frame, fA);
                        ftA.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        ftA.addToBackStack(null);
                        ftA.commit();
                        return true;

                    case R.id.horarios:
                        getSupportActionBar().setTitle(getResources().getString(R.string.menu_horarios));
                        Fragment fHF = new HorariosFragment();
                        FragmentTransaction ftHF = getSupportFragmentManager().beginTransaction();
                        ftHF.replace(R.id.frame, fHF);
                        ftHF.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        ftHF.addToBackStack(null);
                        ftHF.commit();
                        return true;

                    case R.id.noticias:
                        getSupportActionBar().setTitle(getResources().getString(R.string.menu_noticias));
                        Fragment fN = new NewsFragment();
                        FragmentTransaction ftN = getSupportFragmentManager().beginTransaction();
                        ftN.replace(R.id.frame, fN);
                        ftN.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        ftN.addToBackStack(null);
                        ftN.commit();
                        return true;

                    case R.id.resultados:
                        getSupportActionBar().setTitle(getResources().getString(R.string.menu_resultados));
                        Fragment fRA = new ResultadosActivity();
                        FragmentTransaction ftRA = getSupportFragmentManager().beginTransaction();
                        ftRA.replace(R.id.frame, fRA);
                        ftRA.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        ftRA.addToBackStack(null);
                        ftRA.commit();
                        return true;

                    case R.id.pilotos:
                        getSupportActionBar().setTitle(getResources().getString(R.string.menu_pilotos));
                        Fragment fPT = new PilotosFragment();
                        FragmentTransaction ftPP = getSupportFragmentManager().beginTransaction();
                        ftPP.replace(R.id.frame, fPT);
                        ftPP.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        ftPP.addToBackStack(null);
                        ftPP.commit();
                        return true;

                    case R.id.pasion:
                        getSupportActionBar().setTitle(getResources().getString(R.string.menu_pasion));
                        Fragment fP = new PassionFragment();
                        FragmentTransaction ftP = getSupportFragmentManager().beginTransaction();
                        ftP.replace(R.id.frame, fP);
                        ftP.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        ftP.addToBackStack(null);
                        ftP.commit();
                        //passionContainer
                        return true;

                    case R.id.mexico:
                        getSupportActionBar().setTitle(getResources().getString(R.string.menu_cd_mexico));
                        Fragment fM = new CiudadMexicoActivity();
                        FragmentTransaction ftM = getSupportFragmentManager().beginTransaction();
                        ftM.replace(R.id.frame, fM);
                        ftM.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        ftM.addToBackStack(null);
                        ftM.commit();
                        return true;

                    case R.id.social_hub:
                        getSupportActionBar().setTitle(getResources().getString(R.string.menu_social_hub));
                        Fragment fSH = new SocialHubFragment();
                        FragmentTransaction ftSH = getSupportFragmentManager().beginTransaction();
                        ftSH.replace(R.id.frame, fSH);
                        ftSH.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        ftSH.addToBackStack(null);
                        ftSH.commit();
                        return true;

                    case R.id.registrate:
                        getSupportActionBar().setTitle(getResources().getString(R.string.menu_registrate_gana));
                        Fragment fRT = new FacebookLogIn();
                        FragmentTransaction ftRT = getSupportFragmentManager().beginTransaction();
                        ftRT.replace(R.id.frame, fRT);
                        ftRT.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        ftRT.addToBackStack(null);
                        ftRT.commit();
                        return true;

                    case R.id.configuraciones:
                        getSupportActionBar().setTitle(getResources().getString(R.string.menu_configuraciones));
                        Intent myIntent = new Intent(BaseActivity.this, SettingsActivity.class);
                        BaseActivity.this.startActivity(myIntent);
                        return true;

                    default:
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
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
