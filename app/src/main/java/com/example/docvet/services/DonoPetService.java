package com.example.docvet.services;

import com.example.docvet.model.DonoPet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface DonoPetService {

    @POST("/api/v1/dono-pets/")
    Call<Void> cadastrarDonoPet(@Body DonoPet donoPet);

}
