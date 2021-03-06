package com.designfreed.apppedidos.services;

import com.designfreed.apppedidos.entities.HojaRuta;
import com.designfreed.apppedidos.repositories.HojaRutaRepository;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HojaRutaService {
    private static HojaRutaRepository hojaRutaRepository = new HojaRutaRepository();

    public static HojaRuta jsonToHojaRuta(String json) {
        HojaRuta hoja = new HojaRuta();

        try {
            JSONObject jsonHojaRuta = new JSONObject(json);

            JSONObject jsonChofer = jsonHojaRuta.getJSONObject("chofer");

            Long hojaRutaId = jsonHojaRuta.getLong("id");
            Date fecha = new Date(jsonHojaRuta.getLong("fecha"));
            Long choferId = jsonChofer.getLong("id");
            Boolean estado = jsonHojaRuta.getBoolean("estado");

            hoja.sethojaRutaId(hojaRutaId);
            hoja.setFecha(fecha);
            hoja.setChoferId(choferId);
            hoja.setEstado(estado);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return hoja;
    }

    public static HojaRuta getHojaRutaByFechaAndChofer(Date fecha, Long id) {
        return hojaRutaRepository.getByFechaAndChofer(fecha, id);
    }

    public static Long insertHojaRuta(HojaRuta hoja) {
        return hojaRutaRepository.saveOrUpdate(hoja);
    }
}
