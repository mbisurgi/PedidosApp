package com.designfreed.apppedidos.services;

import com.designfreed.apppedidos.entities.HojaRuta;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PedidoService {
    public static HojaRuta getHojaRuta(String json) {
        HojaRuta hoja = new HojaRuta();

        try {
            JSONObject jsonHojaRuta = new JSONObject(json);

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

            Long id = jsonHojaRuta.getLong("id");
            Date fecha = formatter.parse(jsonHojaRuta.getString("fecha"));
            Boolean estado = jsonHojaRuta.getBoolean("estado");

            hoja.setId(id);
            hoja.setFecha(fecha);
            hoja.setEstado(estado);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
