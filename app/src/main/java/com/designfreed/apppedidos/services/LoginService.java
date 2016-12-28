package com.designfreed.apppedidos.services;

import com.designfreed.apppedidos.entities.Chofer;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginService {
    public static Chofer jsonToChofer(String json) {
        Chofer cho = new Chofer();

        try {
            JSONObject jsonChofer = new JSONObject(json);

            Long choferId = jsonChofer.getLong("id");
            String nombre = jsonChofer.getString("nombre");
            String apellido = jsonChofer.getString("apellido");

            cho.setChoferId(choferId);
            cho.setNombre(nombre);
            cho.setApellido(apellido);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cho;
    }
}
