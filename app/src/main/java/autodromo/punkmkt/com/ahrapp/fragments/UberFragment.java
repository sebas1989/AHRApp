package autodromo.punkmkt.com.ahrapp.fragments;

import android.app.Activity;
import android.location.LocationManager;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.location.LocationListener;
import com.uber.sdk.android.core.UberSdk;
import com.uber.sdk.android.rides.RideParameters;
import com.uber.sdk.android.rides.RideRequestActivityBehavior;
import com.uber.sdk.android.rides.RideRequestButton;
import com.uber.sdk.android.rides.RideRequestButtonCallback;
import com.uber.sdk.core.auth.Scope;
import com.uber.sdk.rides.auth.OAuth2Credentials;
import com.uber.sdk.rides.client.ServerTokenSession;
import com.uber.sdk.rides.client.SessionConfiguration;
import com.uber.sdk.rides.client.error.ApiError;
import autodromo.punkmkt.com.ahrapp.BuildConfig;

import java.util.Arrays;

import autodromo.punkmkt.com.ahrapp.MyVolleySingleton;
import autodromo.punkmkt.com.ahrapp.R;


import static com.uber.sdk.android.core.utils.Preconditions.checkNotNull;
import static com.uber.sdk.android.core.utils.Preconditions.checkState;


/**
 * Created by sebastianmendezgiron on 01/08/16.
 */

public class UberFragment extends Fragment implements RideRequestButtonCallback{

    private RideRequestButton uberButton;
    private SessionConfiguration configuration;

    public static final String CLIENT_ID = BuildConfig.CLIENT_ID;
    public static final String SERVER_TOKEN = BuildConfig.SERVER_TOKEN;
    public static final String CLIENT_SECRET = BuildConfig.CLIENT_SECRET;
    public static final String REDIRECT_URI = BuildConfig.REDIRECT_URI;

    private static final String UBERX_PRODUCT_ID = "a1111c8c-c720-46c3-8534-2fcdd730040d";
    private static final String DROPOFF_ADDR = "Autódromo Hermanos Rodriguez";
    private static final Double DROPOFF_LAT = 19.403419;
    private static final Double DROPOFF_LONG = -99.088515;
    private static final String DROPOFF_NICK = "Autódromo Hermanos Rodriguez, Pista de carreras de coches";
    private static final int WIDGET_REQUEST_CODE = 1234;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.uber_content_fragment,container,false);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        SessionConfiguration config = new SessionConfiguration.Builder()
                .setClientId(CLIENT_ID) //This is necessary
                .setClientSecret(CLIENT_SECRET)
                .setServerToken(SERVER_TOKEN)
                .setRedirectUri(REDIRECT_URI)
                .setEnvironment(SessionConfiguration.Environment.SANDBOX)//SessionConfiguration.Environment.SANDBOX) //Useful for testing your app in the sandbox environment
                .setScopes(Arrays.asList(Scope.PROFILE, Scope.RIDE_WIDGETS)) //Your scopes for authentication here
                .build();
        UberSdk.initialize(config);

        RideParameters rideParametersForProduct = new RideParameters.Builder()
                .setProductId(UBERX_PRODUCT_ID)
                .setPickupToMyLocation()
                .setDropoffLocation(DROPOFF_LAT, DROPOFF_LONG, DROPOFF_NICK, DROPOFF_ADDR)
                .build();

        OAuth2Credentials credentials = new OAuth2Credentials.Builder()
                .setSessionConfiguration(config)
                .build();
        ServerTokenSession session = new ServerTokenSession(config);

        RideRequestActivityBehavior rideRequestActivityBehavior = new RideRequestActivityBehavior(getActivity(),
                WIDGET_REQUEST_CODE, config);


        uberButton = (RideRequestButton) getActivity().findViewById(R.id.uber);
        uberButton.setRequestBehavior(rideRequestActivityBehavior);
        uberButton.setRideParameters(rideParametersForProduct);
        uberButton.setSession(session);
        uberButton.setCallback(this);
        //uberButton.loadRideInformation();

    }

    @Override
    public void onRideInformationLoaded() {
        Toast.makeText(getActivity(), "Estimates have been refreshed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError(ApiError apiError) {
        Toast.makeText(getActivity(), apiError.getClientErrors().get(0).getTitle(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError(Throwable throwable) {
        Log.e("SampleActivity", "Error obtaining Metadata", throwable);
        Toast.makeText(getActivity(), "Connection error", Toast.LENGTH_LONG).show();
    }

    private void validateConfiguration(SessionConfiguration configuration) {
        String nullError = "%s must not be null";
        String sampleError = "Please update your %s in the gradle.properties of the project before " +
                "using the Uber SDK Sample app. For a more secure storage location, " +
                "please investigate storing in your user home gradle.properties ";

        checkNotNull(configuration, String.format(nullError, "SessionConfiguration"));
        checkNotNull(configuration.getClientId(), String.format(nullError, "Client ID"));
        checkNotNull(configuration.getRedirectUri(), String.format(nullError, "Redirect URI"));
        checkNotNull(configuration.getServerToken(), String.format(nullError, "Server Token"));
        checkState(!configuration.getClientId().equals("Fjz1IfsMokI1cgPwQAJQb4NF3Pxug7O_"),
                    String.format(sampleError, "Client ID"));
        checkState(!configuration.getRedirectUri().equals("ahrapp://uberSSO"),
                String.format(sampleError, "Redirect URI"));
        checkState(!configuration.getRedirectUri().equals("1Hf9sYDHqcpTnMIR_gs6TogPrjzaNPl3ASxkETV4"),
                String.format(sampleError, "Server Token"));
    }
}
