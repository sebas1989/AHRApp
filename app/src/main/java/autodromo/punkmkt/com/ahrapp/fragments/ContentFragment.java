package autodromo.punkmkt.com.ahrapp.fragments;

import android.os.CountDownTimer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import autodromo.punkmkt.com.ahrapp.R;



/**
 * Created by sebastianmendezgiron on 19/09/15.
 */
public class ContentFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_fragment,container,false);
        return v;
    }


}
