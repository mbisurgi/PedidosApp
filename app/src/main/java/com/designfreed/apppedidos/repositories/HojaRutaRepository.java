package com.designfreed.apppedidos.repositories;

import com.activeandroid.query.Select;
import com.designfreed.apppedidos.entities.HojaRuta;
import com.designfreed.apppedidos.repositories.generic.GenericRepository;

public class HojaRutaRepository extends GenericRepository<HojaRuta, Long> {

    @Override
    public HojaRuta getByIdCrm(Long id) {
        return new Select().from(HojaRuta.class).where("hojaRutaId = ?", id).executeSingle();
    }
}
