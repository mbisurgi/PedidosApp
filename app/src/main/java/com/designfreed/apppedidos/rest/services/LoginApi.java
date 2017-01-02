package com.designfreed.apppedidos.rest.services;

import com.designfreed.apppedidos.entities.Chofer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LoginApi {
    @GET("/PedidosAPI/services/pedidosService/login")
    Call<Chofer> login(@Query("username") String username, @Query("password") String password);
}
