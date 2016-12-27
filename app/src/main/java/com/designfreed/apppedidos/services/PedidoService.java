package com.designfreed.apppedidos.services;

import com.designfreed.apppedidos.entities.HojaRuta;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PedidoService {
    public static HojaRuta getHojaRuta(String json) {
        HojaRuta hoja = new HojaRuta();

        try {
            JSONObject jsonHojaRuta = new JSONObject(json);

            JSONObject jsonChofer = jsonHojaRuta.getJSONObject("chofer");

            Long id = jsonHojaRuta.getLong("id");
            Date fecha = new Date(jsonHojaRuta.getLong("fecha"));
            Long choferId = jsonChofer.getLong("id");
            Boolean estado = jsonHojaRuta.getBoolean("estado");

            hoja.setId(id);
            hoja.setFecha(fecha);
            hoja.setChoferId(choferId);
            hoja.setEstado(estado);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return hoja;
    }
}
