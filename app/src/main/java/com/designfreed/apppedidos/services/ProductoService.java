package com.designfreed.apppedidos.services;

import com.designfreed.apppedidos.entities.Producto;
import com.designfreed.apppedidos.repositories.ProductoRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductoService {
    private static ProductoRepository productoRepository = new ProductoRepository();

    public static Producto jsonToProducto(String json) {
        return null;
    }

    public static List<Producto> jsonToProductos(String json) {
        List<Producto> productos = new ArrayList<>();

        try {
            JSONArray jsonProductos = new JSONArray(json);

            for (int i = 0; i < jsonProductos.length(); i++) {
                JSONObject jsonProducto = jsonProductos.getJSONObject(i);

                Long id = jsonProducto.getLong("id");
                Integer productoCodigo = jsonProducto.getInt("productoCodigo");
                String producto = jsonProducto.getString("producto");

                productos.add(new Producto(id, productoCodigo, producto));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return productos;
    }

    public static List<Producto> getAll() {
        return productoRepository.getAll();
    }

    public static void insertProductos(List<Producto> productos) {
        for (Producto pro: productos) {
            if (productoRepository.getByCodigo(pro.getProductoCodigo()) == null) {
                pro.save();
            }
        }
    }
}
