package com.designfreed.apppedidos.rest.services;


import com.designfreed.apppedidos.entities.Cliente;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ClienteApi {
    @GET("/PedidosAPI/services/pedidosService/getClienteById")
    Call<Cliente> getById(@Query("clienteId") Long id);

    @GET("/PedidosAPI/services/pedidosService/getClienteByRazonSocial")
    Call<Cliente> getByRazonSocial(@Query("razonSocial") String razonSocial);

    @GET("/PedidosAPI/services/pedidosService/getClienteByTelefono")
    Call<Cliente> getByTelefono(@Query("telefono") String telefono);

    @GET("/PedidosAPI/services/pedidosService/getClientesByAltura")
    Call<List<Cliente>> getAllByAltura(@Query("altura") String altura);
}
