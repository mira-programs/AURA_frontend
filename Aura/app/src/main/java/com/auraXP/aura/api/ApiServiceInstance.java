package com.auraXP.aura.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceInstance {
    private static final String BASE_URL = "http://10.0.2.2:8000/";
    private static Retrofit retrofit;
    private static ApiService apiService;
    private static GeminiService geminiService;

    private static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static ApiService getApiService() {
        if (apiService == null) {
            apiService = getRetrofit().create(ApiService.class);
        }
        return apiService;
    }

    public static GeminiService getGeminiService() {
        if (geminiService == null) {
            geminiService = getRetrofit().create(GeminiService.class);
        }
        return geminiService;
    }
}
