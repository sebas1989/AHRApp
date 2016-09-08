package autodromo.punkmkt.com.ahrapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.uber.sdk.android.rides.RideParameters;
import com.uber.sdk.android.rides.RideRequestActivity;
import com.uber.sdk.android.rides.RideRequestActivityBehavior;
import com.uber.sdk.android.core.UberSdk;
import com.uber.sdk.android.rides.RideRequestButton;
import com.uber.sdk.android.rides.RideRequestButtonCallback;
import com.uber.sdk.android.rides.RideRequestViewError;
import com.uber.sdk.core.auth.Scope;
import com.uber.sdk.rides.client.ServerTokenSession;
import com.uber.sdk.rides.client.SessionConfiguration;
import com.uber.sdk.rides.client.error.ApiError;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * Created by sebastianmendezgiron on 02/08/16.
 */

public class UberActivity extends Activity implements RideRequestButtonCallback{

    private RideRequestButton uberButton;
    private SessionConfiguration configuration;

    private static final String UBERX_PRODUCT_ID = "a1111c8c-c720-46c3-8534-2fcdd730040d";
    private static final String PICKUP_ADDR = "1455 Market Street, San Francisco";
    private static final Double PICKUP_LAT = 37.775304;
    private static final Double PICKUP_LONG = -122.417522;
    private static final String PICKUP_NICK = "Uber HQ";
    private static final String DROPOFF_ADDR = "One Embarcadero Center, San Francisco";
    private static final Double DROPOFF_LAT = 37.795079;
    private static final Double DROPOFF_LONG = -122.397805;
    private static final String DROPOFF_NICK = "Embarcadero";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.uber_content_fragment);

        SessionConfiguration config = new SessionConfiguration.Builder()
                .setClientId("Fjz1IfsMokI1cgPwQAJQb4NF3Pxug7O_") //This is necessary
                .setRedirectUri("http://localhost:8888/")
                .setEnvironment(SessionConfiguration.Environment.SANDBOX) //Useful for testing your app in the sandbox environment
            .setScopes(Arrays.asList(Scope.PROFILE, Scope.RIDE_WIDGETS)) //Your scopes for authentication here
                .build();

        //This is a convenience method and will set the default config to be used in other components without passing it directly.
        UberSdk.initialize(config);

        ServerTokenSession session = new ServerTokenSession(config);



        RideParameters rideParametersForProduct = new RideParameters.Builder()
                .setProductId(UBERX_PRODUCT_ID)
                .setPickupLocation(PICKUP_LAT, PICKUP_LONG, PICKUP_NICK, PICKUP_ADDR)
                .setDropoffLocation(DROPOFF_LAT, DROPOFF_LONG, DROPOFF_NICK, DROPOFF_ADDR)
                .build();


        uberButton = (RideRequestButton) findViewById(R.id.uber);
        uberButton.setRideParameters(rideParametersForProduct);
        uberButton.setSession(session);
        uberButton.setCallback(this);
        uberButton.loadRideInformation();

    }

    @Override
    public void onRideInformationLoaded() {
        Toast.makeText(this, "Estimates have been refreshed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError(ApiError apiError) {
        Toast.makeText(this, apiError.getClientErrors().get(0).getTitle(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError(Throwable throwable) {
        Log.e("SampleActivity", "Error obtaining Metadata", throwable);
        Toast.makeText(this, "Connection error", Toast.LENGTH_LONG).show();
    }
}
