package com.designfreed.apppedidos.rest.adapters;

import com.designfreed.apppedidos.entities.HojaRuta;
import com.designfreed.apppedidos.rest.services.HojaRutaApi;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HojaRutaApiAdapter {
    private Retrofit restAdapter;
    private HojaRutaApi api;
    private static final String LOGIN_URL = "http://192.168.0.3:8080";

    public HojaRutaApiAdapter() {
        restAdapter = new Retrofit.Builder()
                .baseUrl(LOGIN_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .serializeNulls()
                        .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                        .create()))
                .build();

        api = restAdapter.create(HojaRutaApi.class);
    }

    public Call<HojaRuta> getByFechaChofer(String fecha, Long id) {
        return api.getByFechaChofer(fecha, id);
    }
}
