package com.auraXP.aura.api;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface GeminiService {
    @Multipart
    @POST("/predict")
    Call<GeminiResponse> processImage(
            @Part MultipartBody.Part file,
            @Part("description") RequestBody description
    );

}