package com.designfreed.apppedidos.bd;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.designfreed.apppedidos.entities.HojaRuta;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HojaRutaRepository {
    private CrmDbHelper dbHelper;
    private SQLiteDatabase db;

    public HojaRutaRepository(Context context) {
        dbHelper = CrmDbHelper.getInstance(context);
    }

    public HojaRuta getHojaRuta(HojaRuta hojaRuta) {
        db = dbHelper.getReadableDatabase();

        HojaRuta hoja = null;

        String selection = CrmContract.HojaRuta.COLUMN_ID_HOJA_RUTA + "=?";
        String[] selectionArgs = {hojaRuta.getId().toString()};

        Cursor c = db.query(
                CrmContract.HojaRuta.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.getCount() > 0) {
            c.moveToFirst();

            try {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                hoja = new HojaRuta();

                Long id = c.getLong(c.getColumnIndex(CrmContract.HojaRuta.COLUMN_ID_HOJA_RUTA));
                Date fecha = formatter.parse(c.getString(c.getColumnIndex(CrmContract.HojaRuta.COLUMN_FECHA)));
                Long choferId = c.getLong(c.getColumnIndex(CrmContract.HojaRuta.COLUMN_ID_CHOFER));
                Boolean estado = null;

                if (c.getInt(c.getColumnIndex(CrmContract.HojaRuta.COLUMN_ESTADO)) == CrmContract.HojaRuta.ESTADO_ABIERTA) {
                    estado = true;
                } else {
                    estado = false;
                }

                hoja.setId(id);
                hoja.setFecha(fecha);
                hoja.setChoferId(choferId);
                hoja.setEstado(estado);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return hoja;
    }

    public void insertHojaRuta(HojaRuta hojaRuta) {
        
    }
}
