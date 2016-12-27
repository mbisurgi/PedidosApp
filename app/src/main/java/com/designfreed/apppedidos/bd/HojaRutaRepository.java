package com.designfreed.apppedidos.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.designfreed.apppedidos.entities.HojaRuta;
import com.designfreed.apppedidos.utils.Utils;

import java.text.ParseException;
import java.util.Date;

public class HojaRutaRepository {
    private CrmDbHelper dbHelper;
    private SQLiteDatabase db;

    public HojaRutaRepository(Context context) {
        dbHelper = CrmDbHelper.getInstance(context);
    }

    public HojaRuta getHojaRuta(String paramFecha, Long paramChoferId) {
        db = dbHelper.getReadableDatabase();

        HojaRuta hoja = null;

        String selection = CrmContract.HojaRuta.COLUMN_FECHA + "=?" + " AND " + CrmContract.HojaRuta.COLUMN_ID_CHOFER + "=?";
        String[] selectionArgs = {paramFecha, paramChoferId.toString()};

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
                hoja = new HojaRuta();

                Long id = c.getLong(c.getColumnIndex(CrmContract.HojaRuta.COLUMN_ID_HOJA_RUTA));
                Date fecha = Utils.stringToDate(c.getString(c.getColumnIndex(CrmContract.HojaRuta.COLUMN_FECHA)));
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
        db = dbHelper.getWritableDatabase();

        Integer estado = null;
        if (hojaRuta.getEstado()) {
            estado = CrmContract.HojaRuta.ESTADO_ABIERTA;
        } else {
            estado = CrmContract.HojaRuta.ESTADO_CERRADA;
        }

        ContentValues values = new ContentValues();
        values.put(CrmContract.HojaRuta.COLUMN_ID_HOJA_RUTA, hojaRuta.getId());
        values.put(CrmContract.HojaRuta.COLUMN_FECHA, Utils.dateToString(hojaRuta.getFecha()));
        values.put(CrmContract.HojaRuta.COLUMN_ID_CHOFER, hojaRuta.getChoferId());
        values.put(CrmContract.HojaRuta.COLUMN_ESTADO, estado);

        db.insert(CrmContract.HojaRuta.TABLE_NAME, null, values);
    }
}
