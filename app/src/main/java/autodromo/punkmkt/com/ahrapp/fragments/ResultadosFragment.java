package autodromo.punkmkt.com.ahrapp.fragments;

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
public class ResultadosFragment extends Fragment{

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_resultados,container,false);
        return v;
    }
}