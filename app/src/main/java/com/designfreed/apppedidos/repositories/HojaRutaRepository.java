package com.designfreed.apppedidos.repositories;

import com.activeandroid.query.Select;
import com.designfreed.apppedidos.entities.HojaRuta;
import com.designfreed.apppedidos.repositories.generic.GenericRepository;

import java.util.Date;

public class HojaRutaRepository extends GenericRepository<HojaRuta, Long> {

    @Override
    public HojaRuta getByIdCrm(Long id) {
        return new Select().from(HojaRuta.class).where("id_hoja_ruta = ?", id).executeSingle();
    }

    public HojaRuta getByFechaAndChofer(Date fecha, Long id) {
        return new Select().from(HojaRuta.class).where("fecha = ? and id_chofer = ?", fecha.getTime(), id).executeSingle();
    }
}
