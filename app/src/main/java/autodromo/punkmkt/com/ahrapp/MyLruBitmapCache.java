package autodromo.punkmkt.com.ahrapp;

/**
 * Created by sebastianmendezgiron on 05/10/15.
 */
/**
 * Created by germanpunk on 28/07/15.
 */
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;


/**
 * copied from official documentation
 */

import com.android.volley.toolbox.ImageLoader.ImageCache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

public class MyLruBitmapCache extends LruCache<String, Bitmap> implements
        ImageCache {
    public static int getDefaultLruCacheSize() {
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 8;

        return cacheSize;
    }

    public MyLruBitmapCache() {
        this(getDefaultLruCacheSize());
    }

    public MyLruBitmapCache(int sizeInKiloBytes) {
        super(sizeInKiloBytes);
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        return value.getRowBytes() * value.getHeight() / 1024;
    }

    @Override
    public Bitmap getBitmap(String url) {
        return get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        put(url, bitmap);
    }
}