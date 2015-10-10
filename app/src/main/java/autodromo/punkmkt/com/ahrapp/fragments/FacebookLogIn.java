package autodromo.punkmkt.com.ahrapp.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import autodromo.punkmkt.com.ahrapp.GraciasParticiparActivity;
import autodromo.punkmkt.com.ahrapp.R;
import autodromo.punkmkt.com.ahrapp.adapters.CustomizedSpinnerAdapter;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * Created by sebastianmendezgiron on 02/10/15.
 */
public class FacebookLogIn extends Fragment {

    private EditText first_nameView;
    private EditText last_nameView;
    private EditText emailView;
    private Spinner zonaView;
    private Spinner asientoView;
    private Spinner genero;
    private Spinner edad;
    private TextView name;
    private TextView facebook_id;
    private TextView locale;
    private TextView link;
    private TextView age_range;
    CallbackManager callbackManager;
    private ParseObject customUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_login_sing_up,container,false);
        return v;

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FacebookSdk.sdkInitialize(getActivity());
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().add(R.id.fragment_container, new LoginFBFragment()).commit();
        }
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        Log.d("ohhh", loginResult.getAccessToken().toString());
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });

        first_nameView = (EditText) getActivity().findViewById(R.id.first_name);
        last_nameView = (EditText) getActivity().findViewById(R.id.last_name);
        emailView = (EditText) getActivity().findViewById(R.id.email);
        zonaView = (Spinner) getActivity().findViewById(R.id.spinner_zona);
        asientoView = (Spinner) getActivity().findViewById(R.id.spinner_asiento);
        name = (TextView) getActivity().findViewById(R.id.name);
        facebook_id = (TextView) getActivity().findViewById(R.id.facebook_id);
        locale = (TextView) getActivity().findViewById(R.id.locale);
        link = (TextView) getActivity().findViewById(R.id.link);
        age_range = (TextView) getActivity().findViewById(R.id.age_range);
        genero = (Spinner) getActivity().findViewById(R.id.spinner_gender);
        edad = (Spinner) getActivity().findViewById(R.id.spinner_edad);

        first_nameView.setHintTextColor(getResources().getColor(R.color.black));
        last_nameView.setHintTextColor(getResources().getColor(R.color.black));
        emailView.setHintTextColor(getResources().getColor(R.color.black));

        final String[] data_array_genero = getResources().getStringArray(R.array.gender_arrays);
        final String[] data_array_zonas = getResources().getStringArray(R.array.zonas_arrays);

        final String[] data_array_asientos = getResources().getStringArray(R.array.asientos_arrays);


        final ArrayList<String> data_genero = new ArrayList<String>();
        final ArrayList<String> data_edad = new ArrayList<String>();
        final ArrayList<String> data_zonas = new ArrayList<String>();
        final ArrayList<String> data_asientos = new ArrayList<String>();
        for (String valor: data_array_genero ){
            data_genero.add(valor);
        }
        for (String valor: data_array_zonas ){
            data_zonas.add(valor);
        }

        for (int i = 5; i<=99;i++){
            data_edad.add(Integer.toString(i));
        }

        for (String valor: data_array_asientos){
            data_asientos.add(valor);
        }

        data_genero.add(0,getResources().getString(R.string.selecciona_genero)); //Add element at 0th index
        data_edad.add(0,getResources().getString(R.string.selecciona_edad));
        data_zonas.add(0,getResources().getString(R.string.selecciona_zona));
        data_asientos.add(0,getResources().getString(R.string.selecciona_asientos));
        final ArrayAdapter<String> adapter_generos = new CustomizedSpinnerAdapter(
                getActivity(), android.R.layout.simple_spinner_item,
                data_genero);
        final ArrayAdapter<String> adapter_edades = new CustomizedSpinnerAdapter(
                getActivity(), android.R.layout.simple_spinner_item,
                data_edad);
        final ArrayAdapter<String> adapter_zonas = new CustomizedSpinnerAdapter(
                getActivity(), android.R.layout.simple_spinner_item,
                data_zonas);
        final ArrayAdapter<String> adapter_asientos = new CustomizedSpinnerAdapter(
                getActivity(), android.R.layout.simple_spinner_item,
                data_asientos);
        genero.setAdapter(adapter_generos);
        edad.setAdapter(adapter_edades);
        zonaView.setAdapter(adapter_zonas);
        asientoView.setAdapter(adapter_asientos);



        getActivity().findViewById(R.id.action_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                // Validate the sign up data
                boolean validationError = false;
                StringBuilder validationErrorMessage =
                        new StringBuilder(getResources().getString(R.string.error_intro));
                if (isEmpty(first_nameView)) {
                    validationError = true;
                }
                if (isEmpty(last_nameView)) {
                    validationError = true;
                }
                if(!isValidEmail(emailView.getText().toString())){
                    validationError = true;
                }

                if(!ValidateSpinner(genero, getResources().getString(R.string.selecciona_genero))){
                    validationError = true;
                }
                if(!ValidateSpinner(edad,getResources().getString(R.string.selecciona_edad))){
                    validationError = true;
                }
                if(!ValidateSpinner(zonaView,getResources().getString(R.string.selecciona_zona))){
                    validationError = true;
                }
                if(!ValidateSpinner(asientoView,getResources().getString(R.string.selecciona_asientos))){
                    validationError = true;
                }

                //validationErrorMessage.append(getResources().getString(R.string.error_end));

                // If there is a validation error, display the error
                if (validationError) {
                    //Toast.makeText(LoginSingUpActivity.this, validationErrorMessage.toString(), Toast.LENGTH_LONG)
                    //.show();
                    Crouton.makeText(getActivity(), validationErrorMessage.toString(), Style.ALERT).show();
                    return;
                }

                // Set up a progress dialog
                final ProgressDialog dlg = new ProgressDialog(getActivity());
                dlg.setTitle(getResources().getString(R.string.porfavor_espere));
                dlg.setMessage(getResources().getString(R.string.porfavor_espere_extendido));
                dlg.show();

                ParseQuery<ParseObject> query = ParseQuery.getQuery("CustomUser");
                query.whereEqualTo("email", emailView.getText().toString());
                query.getFirstInBackground(new GetCallback<ParseObject>() {
                    public void done(ParseObject object, ParseException e) {
                        if (object == null) {
                            customUser = new ParseObject("CustomUser");
                            customUser.put("first_name", first_nameView.getText().toString());
                            customUser.put("last_name", last_nameView.getText().toString());
                            customUser.put("email", emailView.getText().toString());
                            customUser.put("gender", genero.getSelectedItem().toString());
                            customUser.put("name", name.getText().toString());
                            customUser.put("facebook_id",facebook_id.getText().toString());
                            customUser.put("locale",locale.getText().toString());
                            customUser.put("link",link.getText().toString());
                            customUser.put("age",edad.getSelectedItem().toString());
                            customUser.put("zona", zonaView.getSelectedItem().toString());
                            customUser.put("grada", asientoView.getSelectedItem().toString());
                            // Call the Parse signup method
                            customUser.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    dlg.dismiss();
                                    if (e != null) {
                                        // Show the error message
                                        //Toast.makeText(LoginSingUpActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                        Crouton.makeText(getActivity(), e.getMessage(), Style.ALERT).show();
                                    } else {

                                        //Crouton.makeText(LoginSingUpActivity.this, R.string.gracias_por_registrarte, Style.INFO).show();
                                        Intent myIntent = new Intent(getActivity(), GraciasParticiparActivity.class);
                                        getActivity().startActivity(myIntent);
                                    }
                                }
                            });
                        } else {
                            dlg.dismiss();
                            Crouton.makeText(getActivity(),R.string.usuario_existente, Style.ALERT).show();
                            //Toast.makeText(LoginSingUpActivity.this,R.string.usuario_existente, Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });

    }


    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0) {
            return false;
        } else {
            return true;
        }
    }

    private boolean isMatching(EditText etText1, EditText etText2) {
        if (etText1.getText().toString().equals(etText2.getText().toString())) {
            return true;
        } else {
            return false;
        }
    }


    public final static boolean isValidEmail(CharSequence target) {
        if (target == null)
            return false;

        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public static void showHashKey(Context context) {

        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    "com.punkmkt.formula1", PackageManager.GET_SIGNATURES); //Your            package name here
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());

                Log.i("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
        } catch (NoSuchAlgorithmException e) {
        }
    }

    public final static boolean ValidateSpinner(Spinner s,String s_option){
        String st =s.getSelectedItem().toString();
        int pos =s.getSelectedItemPosition();
        if(pos==0)
        {
            return false;
        }
        if(!st.equals(s_option))
        {
            return true;
        }
        else{
            return false;
        }
    }

}
