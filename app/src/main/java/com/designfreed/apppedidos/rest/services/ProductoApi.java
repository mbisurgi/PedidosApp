package com.designfreed.apppedidos.rest.services;

import com.designfreed.apppedidos.entities.Producto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductoApi {
    @GET("PedidosAPI/services/pedidosService/getProductos")
    Call<List<Producto>> getAll();
}
