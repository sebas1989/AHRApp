package autodromo.punkmkt.com.ahrapp.utils;

/**
 * Created by germanpunk on 17/10/15.
 */

        import android.os.Build;
        import android.support.v4.util.LruCache;

public class LruCacheManager {

    private LruCache<String, RecyclingBitmapDrawable> mMemoryCache;

    private static LruCacheManager instance;

    public static LruCacheManager getInstance() {
        if(instance == null) {
            instance = new LruCacheManager();
            instance.init();
        }

        return instance;
    }

    private void init() {

        // We are declaring a cache of 6Mb for our use.
        // You need to calculate this on the basis of your need
        mMemoryCache = new LruCache<String, RecyclingBitmapDrawable>(6 * 1024 * 1024) {
            @Override
            protected int sizeOf(String key, RecyclingBitmapDrawable bitmapDrawable) {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
                    return bitmapDrawable.getBitmap().getByteCount() ;
                } else {
                    return bitmapDrawable.getBitmap().getRowBytes() * bitmapDrawable.getBitmap().getHeight();
                }
            }

            @Override
            protected void entryRemoved(boolean evicted, String key, RecyclingBitmapDrawable oldValue, RecyclingBitmapDrawable newValue) {
                super.entryRemoved(evicted, key, oldValue, newValue);
                oldValue.setIsCached(false);
            }
        };

    }

    public void addBitmapToMemoryCache(String key, RecyclingBitmapDrawable bitmapDrawable) {
        if (getBitmapFromMemCache(key) == null) {
            // The removed entry is a recycling drawable, so notify it
            // that it has been added into the memory cache
            bitmapDrawable.setIsCached(true);
            mMemoryCache.put(key, bitmapDrawable);
        }
    }

    public RecyclingBitmapDrawable getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }

    public void clear() {
        mMemoryCache.evictAll();
    }
}