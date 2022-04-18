package com.example.mungoapp.network;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    @Headers("Accept: application/json")
    @POST(Constants.LOGIN)
    Call<ResponseBody> Login(@Body RequestBody body);
}
