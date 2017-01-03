package com.designfreed.apppedidos.rest.adapters;

import com.designfreed.apppedidos.entities.Cliente;
import com.designfreed.apppedidos.rest.services.ClienteApi;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClienteApiAdapter {
    private Retrofit restAdapter;
    private ClienteApi api;
    private static final String LOGIN_URL = "http://192.168.0.10:8080";

    public ClienteApiAdapter() {
        restAdapter = new Retrofit.Builder()
                .baseUrl(LOGIN_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .serializeNulls()
                        .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                        .create()))
                .build();

        api = restAdapter.create(ClienteApi.class);
    }

    public Call<Cliente> getById(Long id) {
        return api.getById(id);
    }

    public Call<Cliente> getByRazonSocial(String razonSocial) {
        return api.getByRazonSocial(razonSocial);
    }

    public Call<Cliente> getByTelefono(String telefono) {
        return api.getByTelefono(telefono);
    }

    public Call<List<Cliente>> getAllByAltura(String altura) {
        return api.getAllByAltura(altura);
    }
}
