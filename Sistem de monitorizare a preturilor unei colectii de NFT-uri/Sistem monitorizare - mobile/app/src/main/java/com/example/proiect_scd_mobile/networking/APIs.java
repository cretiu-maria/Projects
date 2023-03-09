package com.example.proiect_scd_mobile.networking;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIs {

    @GET("nftread")
    Call<ArrayList<collection>> getAllCollectionList();
}



