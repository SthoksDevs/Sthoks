package com.sthoksdevs.sthoks.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.LruCache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class MyVolleySingleton {
    private static Context mContext;
    private static MyVolleySingleton mInstance;
    public final String TAG = MyVolleySingleton.class.getSimpleName();
    private ImageLoader mImageLoader;
    private RequestQueue mRequestQueue;

    private MyVolleySingleton(Context context) {
        mContext = context;
        this.mRequestQueue = getRequestQueue();
        this.mImageLoader = new ImageLoader(this.mRequestQueue, new ImageLoader.ImageCache() {
            /* class com.sthoksdevs.sthoks.network.MyVolleySingleton.C06591 */
            private final LruCache<String, Bitmap> cache = new LruCache<>(52428800);

            @Override // com.android.volley.toolbox.ImageLoader.ImageCache
            public Bitmap getBitmap(String str) {
                return this.cache.get(str);
            }

            @Override // com.android.volley.toolbox.ImageLoader.ImageCache
            public void putBitmap(String str, Bitmap bitmap) {
                this.cache.put(str, bitmap);
            }
        });
    }

    public static synchronized MyVolleySingleton getInstance(Context context) {
        MyVolleySingleton myVolleySingleton;
        synchronized (MyVolleySingleton.class) {
            if (mInstance == null) {
                mInstance = new MyVolleySingleton(context);
            }
            myVolleySingleton = mInstance;
        }
        return myVolleySingleton;
    }

    public RequestQueue getRequestQueue() {
        if (this.mRequestQueue == null) {
            this.mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return this.mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request, String str) {
        if (TextUtils.isEmpty(str)) {
            str = this.TAG;
        }
        request.setTag(str);
        getRequestQueue().add(request);
    }

    public <T> void addToRequestQueue(Request<T> request) {
        request.setTag(this.TAG);
        getRequestQueue().add(request);
    }

    public ImageLoader getImageLoader() {
        return this.mImageLoader;
    }

    public void cancelPendingRequest(Object obj) {
        if (this.mRequestQueue != null) {
            this.mRequestQueue.cancelAll(obj);
        }
    }
}
