package com.designfreed.apppedidos.rest.adapters;

import com.designfreed.apppedidos.entities.Chofer;
import com.designfreed.apppedidos.rest.services.LoginApi;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginApiAdapter {
    private Retrofit restAdapter;
    private LoginApi api;
    private static final String LOGIN_URL = "http://192.168.0.3:8080";

    public LoginApiAdapter() {
        restAdapter = new Retrofit.Builder()
                .baseUrl(LOGIN_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .serializeNulls()
                        .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                        .create()))
                .build();

        api = restAdapter.create(LoginApi.class);
    }

    public Call<Chofer> login(String username, String password) {
        return api.login(username, password);
    }
}
