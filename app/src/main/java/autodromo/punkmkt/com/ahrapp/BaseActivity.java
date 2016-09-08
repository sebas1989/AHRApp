package autodromo.punkmkt.com.ahrapp;

/**
 * Created by sebastianmendezgiron on 19/09/15.
 */
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.LruCache;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.parse.ParseAnalytics;

<<<<<<< HEAD
import java.lang.ref.WeakReference;
=======
<<<<<<< HEAD
//import com.facebook.drawee.backends.pipeline.Fresco;

import com.parse.ParseAnalytics;
=======
>>>>>>> 65c70cebae4c93b3fa4175eff1e0fd5102b2d2e1
>>>>>>> 42f8a828e72bbdb328a7d02191b2ef4db24fa58f

import autodromo.punkmkt.com.ahrapp.adapters.TiendaPageAdapter;
import autodromo.punkmkt.com.ahrapp.fragments.AutodromoFragment;
import autodromo.punkmkt.com.ahrapp.fragments.CiudadMexicoActivity;
import autodromo.punkmkt.com.ahrapp.fragments.FacebookLogIn;
import autodromo.punkmkt.com.ahrapp.fragments.HomeFragment;
import autodromo.punkmkt.com.ahrapp.fragments.HorariosFragment;
import autodromo.punkmkt.com.ahrapp.fragments.NewsFragment;
import autodromo.punkmkt.com.ahrapp.fragments.NoticiasFragment;
import autodromo.punkmkt.com.ahrapp.fragments.PassionFragment;
import autodromo.punkmkt.com.ahrapp.fragments.PilotosFragment;
import autodromo.punkmkt.com.ahrapp.fragments.PremiosFragment;
import autodromo.punkmkt.com.ahrapp.fragments.ResultadosActivity;
import autodromo.punkmkt.com.ahrapp.fragments.SocialHubFragment;
import autodromo.punkmkt.com.ahrapp.fragments.TiendaFragment;
import autodromo.punkmkt.com.ahrapp.fragments.TiendaFragmentProducts;
import autodromo.punkmkt.com.ahrapp.utils.RecyclingBitmapDrawable;
import de.keyboardsurfer.android.widget.crouton.Crouton;

public class BaseActivity extends AppCompatActivity {

    //Defining Variables
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    static public LruCache<String, Bitmap> mMemoryCache;
    private static GoogleAnalytics analytics;
    private static Tracker tracker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
        ParseAnalytics.trackAppOpenedInBackground(getIntent());

        //Fresco.initialize(getApplicationContext());
=======
>>>>>>> 65c70cebae4c93b3fa4175eff1e0fd5102b2d2e1
        setContentView(R.layout.activity_main);

        ParseAnalytics.trackAppOpenedInBackground(getIntent());

        Intent intent = getIntent();

        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        // Use 1/8th of the available memory for this memory cache.
        final int cacheSize = maxMemory / 8;

        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return bitmap.getByteCount() / 1024;
            }
        };
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
            else if(fragmento.equals("mexico")){
                if (savedInstanceState == null) {
                    Fragment f1 = new CiudadMexicoActivity();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.add(R.id.frame, f1).commit();
                }
            }
            else if (fragmento.equals("tiendaRestaurantes")){
                if (savedInstanceState == null) {
                    Fragment f1 = new TiendaFragment();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.add(R.id.frame, f1).commit();
                }
            }
        }catch (Exception e){
            if (savedInstanceState == null) {
                Fragment f1 = new HomeFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.add(R.id.frame, f1).commit();

            }
        }

        ImageView mImageView = (ImageView) findViewById(R.id.profile_image);

        //loadBitmap(R.drawable.logo_ahr, mImageView,150,67);


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
                        Fragment fN = new NoticiasFragment();
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

                    case R.id.tienda:
                        getSupportActionBar().setTitle(getResources().getString(R.string.tienda_titulo));
                        getSupportActionBar().setTitle(getResources().getString(R.string.menu_tienda));
                        Boolean isFirstRunShop = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                                .getBoolean("isFirstRunShop", true);
                        //isFirstRunShop = true;
                        if (isFirstRunShop) {
                            Intent tiendaIntent = new Intent(getApplicationContext(), TutorialTiendaActivity.class);
                            tiendaIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            getApplicationContext().startActivity(tiendaIntent);
                            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                                    .putBoolean("isFirstRunShop", false).commit();
                        }else{
                            //Fragment fT = new TiendaFragmentProducts();
                            Fragment fT = new TiendaFragment();
                            FragmentTransaction ftT = getSupportFragmentManager().beginTransaction();
                            ftT.replace(R.id.frame, fT);
                            ftT.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                            ftT.addToBackStack(null);
                            ftT.commit();
                        }
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
    protected void onDestroy(){
        super.onDestroy();
        Crouton.cancelAllCroutons();
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

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }


    public void loadBitmap(int resId, ImageView imageView, int width, int height) {
        final String imageKey = String.valueOf(resId);

        final Bitmap bitmap = mMemoryCache.get(imageKey);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            //imageView.setImageResource(R.drawable.descarga);
            BitmapWorkerTask task = new BitmapWorkerTask(imageView, 100, 100);
            task.execute(resId);
        }


            new RecyclingBitmapDrawable(getResources(),bitmap);
    }

    class BitmapWorkerTask extends AsyncTask<Integer, Void, Bitmap> {
        private final WeakReference<ImageView> imageViewReference;
        private int data = 0;
        private int width;
        private int height;

        public BitmapWorkerTask(ImageView imageView, int width, int height) {
            // Use a WeakReference to ensure the ImageView can be garbage collected
            imageViewReference = new WeakReference<ImageView>(imageView);
            this.width = width;
            this.height = height;
        }

        // Decode image in background.
        @Override
        protected Bitmap doInBackground(Integer... params) {
            final Bitmap bitmap = decodeSampledBitmapFromResource(getResources(), params[0], width,height);
            addBitmapToMemoryCache(String.valueOf(params[0]), bitmap);
            return bitmap;
        }

        // Once complete, see if ImageView is still around and set bitmap.
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (imageViewReference != null && bitmap != null) {
                final ImageView imageView = imageViewReference.get();
                if (imageView != null) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        }
    }
    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static boolean cancelPotentialWork(int data, ImageView imageView) {
        final BitmapWorkerTask bitmapWorkerTask = getBitmapWorkerTask(imageView);

        if (bitmapWorkerTask != null) {
            final int bitmapData = bitmapWorkerTask.data;
            // If bitmapData is not yet set or it differs from the new data
            if (bitmapData == 0 || bitmapData != data) {
                // Cancel previous task
                bitmapWorkerTask.cancel(true);
            } else {
                // The same work is already in progress
                return false;
            }
        }
        // No task associated with the ImageView, or an existing task was cancelled
        return true;
    }

    private static BitmapWorkerTask getBitmapWorkerTask(ImageView imageView) {
        if (imageView != null) {
            final Drawable drawable = imageView.getDrawable();
            if (drawable instanceof AsyncDrawable) {
                final AsyncDrawable asyncDrawable = (AsyncDrawable) drawable;
                return asyncDrawable.getBitmapWorkerTask();
            }
        }
        return null;
    }

    static class AsyncDrawable extends BitmapDrawable {
        private final WeakReference<BitmapWorkerTask> bitmapWorkerTaskReference;

        public AsyncDrawable(Resources res, Bitmap bitmap,
                             BitmapWorkerTask bitmapWorkerTask) {
            super(res, bitmap);
            bitmapWorkerTaskReference =
                    new WeakReference<BitmapWorkerTask>(bitmapWorkerTask);
        }

        public BitmapWorkerTask getBitmapWorkerTask() {
            return bitmapWorkerTaskReference.get();
        }
    }
}
