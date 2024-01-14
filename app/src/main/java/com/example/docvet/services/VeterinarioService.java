package com.example.docvet.services;

import com.example.docvet.model.Credenciais;
import com.example.docvet.model.Pessoa;
import com.example.docvet.model.Veterinario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface VeterinarioService {

    @GET("/api/v1/veterinarios/")
    Call<List<Veterinario>> getVeterinarios();

    @POST("/api/v1/veterinarios/")
    Call<Void> cadastrarVeterinario(@Body Veterinario veterinario);


}
