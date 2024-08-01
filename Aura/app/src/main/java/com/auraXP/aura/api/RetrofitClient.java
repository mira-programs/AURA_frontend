package com.auraXP.aura.api;

import android.util.Log;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl) {
        Log.d("RetrofitClient", "getClient called with baseUrl: " + baseUrl);
        if (retrofit == null) {
            Log.d("RetrofitClient", "Creating new Retrofit instance");
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Log.d("RetrofitClient", "Retrofit instance created: " + retrofit);
        }
        return retrofit;
    }
}
