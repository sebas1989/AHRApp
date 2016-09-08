package autodromo.punkmkt.com.ahrapp.utils;

/**
 * Created by sebastianmendezgiron on 25/09/15.
 */
import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import autodromo.punkmkt.com.ahrapp.R;

/**
 * Created by germanpunk on 14/09/15.
 */
public class AuthRequest extends StringRequest {
    private String charset = null;
    private Context context;
    public AuthRequest(Context context, int method, String url, String charset, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
        this.charset = charset;
        this.context = context;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return createBasicAuthHeader(this.context.getResources().getString(R.string.user_api_token));
    }

    Map<String, String> createBasicAuthHeader(String token) {
        Map<String, String> headerMap = new HashMap<String, String>();

        //String credentials = username + ":" + password;
        //String encodedCredentials = Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        headerMap.put("Content-Type", "application/json; charset=utf-8");
        headerMap.put("Authorization", "Token " + token);

        return headerMap;
    }


    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            if(charset != null) {
                parsed = new String(response.data, charset);
            } else {
                parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            }
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }

    /**
     * @return the Parse Charset Encoding
     */
    public String getCharset() {
        return charset;
    }

    /**
     * set the Parse Charset Encoding
     * @param charset
     */
    public void setCharset(String charset) {
        this.charset = charset;
    }

    public static Cache.Entry parseIgnoreCacheHeaders(NetworkResponse response) {
        long now = System.currentTimeMillis();

        Map<String, String> headers = response.headers;
        long serverDate = 0;
        String serverEtag = null;
        String headerValue;

        headerValue = headers.get("Date");
        if (headerValue != null) {
            serverDate = HttpHeaderParser.parseDateAsEpoch(headerValue);
        }

        serverEtag = headers.get("ETag");

        final long cacheHitButRefreshed = 3 * 60 * 1000; // in 3 minutes cache will be hit, but also refreshed on background
        final long cacheExpired = 24 * 60 * 60 * 1000; // in 24 hours this cache entry expires completely
        final long softExpire = now + cacheHitButRefreshed;
        final long ttl = now + cacheExpired;

        Cache.Entry entry = new Cache.Entry();
        entry.data = response.data;
        entry.etag = serverEtag;
        entry.softTtl = softExpire;
        entry.ttl = ttl;
        entry.serverDate = serverDate;
        entry.responseHeaders = headers;

        return entry;
    }

    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        Log.d("DEBUG", "parseNetworkError");
        return super.parseNetworkError(volleyError);
    }

    @Override
    public void deliverError(final VolleyError error) {
        Log.d("DEBUG", "deliverError");
    }
    /*private String charset = null;
    private Context context;
    public AuthRequest(Context context, int method, String url, String charset, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
        this.charset = charset;
        this.context = context;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return createBasicAuthHeader(this.context.getResources().getString(R.string.user_api),this.context.getResources().getString(R.string.password_api));
    }

    Map<String, String> createBasicAuthHeader(String username, String password) {
        Map<String, String> headerMap = new HashMap<String, String>();

        String credentials = username + ":" + password;
        String encodedCredentials = Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        headerMap.put("Authorization", "Basic " + encodedCredentials);

        return headerMap;
    }


    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            if(charset != null) {
                parsed = new String(response.data, charset);
            } else {
                parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            }
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        return Response.success(parsed, parseIgnoreCacheHeaders(response));
    }

    /**
     * @return the Parse Charset Encoding
     *
    public String getCharset() {
        return charset;
    }

    /**
     * set the Parse Charset Encoding
     * @param charset
     *
    public void setCharset(String charset) {
        this.charset = charset;
    }

    public static Cache.Entry parseIgnoreCacheHeaders(NetworkResponse response) {
        long now = System.currentTimeMillis();

        Map<String, String> headers = response.headers;
        long serverDate = 0;
        String serverEtag = null;
        String headerValue;

        headerValue = headers.get("Date");
        if (headerValue != null) {
            serverDate = HttpHeaderParser.parseDateAsEpoch(headerValue);
        }

        serverEtag = headers.get("ETag");

        final long cacheHitButRefreshed = 3 * 60 * 1000; // in 3 minutes cache will be hit, but also refreshed on background
        final long cacheExpired = 24 * 60 * 60 * 1000; // in 24 hours this cache entry expires completely
        final long softExpire = now + cacheHitButRefreshed;
        final long ttl = now + cacheExpired;

        Cache.Entry entry = new Cache.Entry();
        entry.data = response.data;
        entry.etag = serverEtag;
        entry.softTtl = softExpire;
        entry.ttl = ttl;
        entry.serverDate = serverDate;
        entry.responseHeaders = headers;

        return entry;
    }

    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        Log.d("DEBUG", "parseNetworkError");
        return super.parseNetworkError(volleyError);
    }

    @Override
    public void deliverError(final VolleyError error) {
        Log.d("DEBUG", "deliverError");
    }
    */

}









