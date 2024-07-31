package com.auraXP.aura;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface GeminiService {
    @Multipart
    @POST("/process-image")
    Call<GeminiResponse> processImage(@Part MultipartBody.Part file);
}