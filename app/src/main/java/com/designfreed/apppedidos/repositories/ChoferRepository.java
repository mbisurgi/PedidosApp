package com.designfreed.apppedidos.repositories;


import com.activeandroid.query.Select;
import com.designfreed.apppedidos.entities.Chofer;
import com.designfreed.apppedidos.repositories.generic.GenericRepository;

public class ChoferRepository extends GenericRepository<Chofer, Long> {
    @Override
    public Chofer getByIdCrm(Long id) {
        return new Select().from(Chofer.class).where("choferId = ?", id).executeSingle();
    }
}
