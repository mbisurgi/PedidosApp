package com.designfreed.apppedidos.rest.adapters;

import com.designfreed.apppedidos.entities.Producto;
import com.designfreed.apppedidos.rest.services.ProductoApi;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductoApiAdapter {
    private Retrofit restAdapter;
    private ProductoApi api;
    private static final String LOGIN_URL = "http://192.168.0.10:8080";

    public ProductoApiAdapter() {
        restAdapter = new Retrofit.Builder()
                .baseUrl(LOGIN_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .serializeNulls()
                        .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                        .create()))
                .build();

        api = restAdapter.create(ProductoApi.class);
    }

    public Call<List<Producto>> getAll() {
        return api.getAll();
    }
}
