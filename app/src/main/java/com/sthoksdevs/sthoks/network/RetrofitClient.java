package com.sthoksdevs.sthoks.network;

import android.util.Base64;
import com.sthoksdevs.sthoks.interfaces.RetrofitSendInterface;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String API_PASSWORD = "mailgun_password";
    private static final String API_USERNAME = "api";
    private static final String AUTH = ("Basic " + Base64.encodeToString("api:%API_PASSWORD%".getBytes(), 2));
    private static final String BASE_URL = "your_mailgun_url_here";
    private static RetrofitClient mInstance;
    private Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient.Builder().addInterceptor(new Interceptor() {
        /* class com.sthoksdevs.sthoks.network.RetrofitClient.C06601 */

        @Override // okhttp3.Interceptor
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();
            return chain.proceed(request.newBuilder().header("Authorization", RetrofitClient.AUTH).method(request.method(), request.body()).build());
        }
    }).build()).build();

    private RetrofitClient() {
    }

    public static synchronized RetrofitClient getInstance() {
        RetrofitClient retrofitClient;
        synchronized (RetrofitClient.class) {
            if (mInstance == null) {
                mInstance = new RetrofitClient();
            }
            retrofitClient = mInstance;
        }
        return retrofitClient;
    }

    public Retrofit getClient() {
        return this.retrofit;
    }

    public RetrofitSendInterface getApi() {
        return (RetrofitSendInterface) this.retrofit.create(RetrofitSendInterface.class);
    }
}
