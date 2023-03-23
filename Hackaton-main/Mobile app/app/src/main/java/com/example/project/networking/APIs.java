package com.example.project.networking;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIs {

    @POST("login")
    Call<LoginResponse> AllLoginUser(@Body LoginRequest loginRequest);

    @POST("signup")
    Call<RegisterResponse> registerUsers(@Body RegisterRequest registerRequest);

    @GET("listProducts/{productId}")
    Call<ArrayList<Product>>getAllProducts(Integer productId);

    @GET("users")
    Call<ArrayList<User>>getAllRestaurants();
}

