package com.designfreed.apppedidos.rest.services;

import com.designfreed.apppedidos.entities.HojaRuta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HojaRutaApi {
    @GET("PedidosAPI/services/pedidosService/getHojaRuta")
    Call<HojaRuta> getByFechaChofer(@Query("fecha") String fecha, @Query("choferId") Long id);
}
