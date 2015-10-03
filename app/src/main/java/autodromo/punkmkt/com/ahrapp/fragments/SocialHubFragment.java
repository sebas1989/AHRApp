package autodromo.punkmkt.com.ahrapp.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import autodromo.punkmkt.com.ahrapp.R;


/**
 * Created by sebastianmendezgiron on 01/10/15.
 */
public class SocialHubFragment extends Fragment {

    private WebView mWebView;
    private final String AHZ_URL_SOCIAL_HUB = "http://104.236.3.158/api/noticias/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.social_hub_fragment,container,false);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mWebView = (WebView) getActivity().findViewById(R.id.socialHubWebView);

        mWebView.loadUrl(AHZ_URL_SOCIAL_HUB);
        //RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.my_recycler_view);
        //recyclerView.setHasFixedSize(true);

    }

}
