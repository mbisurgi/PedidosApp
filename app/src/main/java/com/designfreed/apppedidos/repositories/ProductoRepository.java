package com.designfreed.apppedidos.repositories;

import com.activeandroid.query.Select;
import com.designfreed.apppedidos.entities.Producto;
import com.designfreed.apppedidos.repositories.generic.GenericRepository;

import java.util.List;

public class ProductoRepository extends GenericRepository<Producto, Long> {
    @Override
    public Producto getByIdCrm(Long id) {
        return new Select().from(Producto.class).where("id_producto = ?", id).executeSingle();
    }

    public Producto getByCodigo(Integer codigo) {
        return new Select().from(Producto.class).where("codigo_producto = ?", codigo).executeSingle();
    }

    public List<Producto> getAll() {
        return new Select().from(Producto.class).execute();
    }
}
