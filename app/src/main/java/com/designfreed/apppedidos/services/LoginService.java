package com.designfreed.apppedidos.services;

import com.designfreed.apppedidos.entities.Chofer;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginService {
    public static Chofer getChofer(String json) {
        Chofer cho = new Chofer();

        try {
            JSONObject jsonChofer = new JSONObject(json);

            Long id = jsonChofer.getLong("id");
            String nombre = jsonChofer.getString("nombre");
            String apellido = jsonChofer.getString("apellido");

            cho.setId(id);
            cho.setNombre(nombre);
            cho.setApellido(apellido);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cho;
    }
}
