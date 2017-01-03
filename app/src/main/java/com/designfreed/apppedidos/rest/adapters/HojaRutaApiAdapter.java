package com.designfreed.apppedidos.rest.adapters;

import com.designfreed.apppedidos.entities.HojaRuta;
import com.designfreed.apppedidos.rest.services.HojaRutaApi;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HojaRutaApiAdapter {
    private Retrofit restAdapter;
    private HojaRutaApi api;
    private static final String LOGIN_URL = "http://192.168.0.10:8080";

    public HojaRutaApiAdapter() {
        restAdapter = new Retrofit.Builder()
                .baseUrl(LOGIN_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .serializeNulls()
                        .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                        .registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                            @Override
                            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                                return new Date(json.getAsJsonPrimitive().getAsLong());
                            }
                        })
                        .create()))
                .build();

        api = restAdapter.create(HojaRutaApi.class);
    }

    public Call<HojaRuta> getByFechaChofer(String fecha, Long id) {
        return api.getByFechaChofer(fecha, id);
    }
}
