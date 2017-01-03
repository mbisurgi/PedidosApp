package com.designfreed.apppedidos.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.designfreed.apppedidos.R;
import com.designfreed.apppedidos.entities.Chofer;
import com.designfreed.apppedidos.entities.HojaRuta;
import com.designfreed.apppedidos.entities.Producto;
import com.designfreed.apppedidos.rest.adapters.HojaRutaApiAdapter;
import com.designfreed.apppedidos.rest.adapters.ProductoApiAdapter;
import com.designfreed.apppedidos.services.HojaRutaService;
import com.designfreed.apppedidos.services.ProductoService;
import com.designfreed.apppedidos.utils.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView txtChofer;
    private ImageButton btnVoleos;
    private ImageButton btnBuscar;

    private Chofer chofer;
    private HojaRuta hoja;

    private ProductoApiAdapter productoApiAdapter;
    private HojaRutaApiAdapter hojaRutaApiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chofer = (Chofer) getIntent().getSerializableExtra("chofer");

        txtChofer = (TextView) findViewById(R.id.chofer);
        txtChofer.setText(chofer.getNombre() + " " + chofer.getApellido());

        btnVoleos = (ImageButton) findViewById(R.id.voleos);
        btnVoleos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PedidoActivity.class);
                intent.putExtra("hoja", hoja);

                startActivity(intent);
            }
        });

        btnBuscar = (ImageButton) findViewById(R.id.buscar);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BuscarClienteActivity.class);

                startActivity(intent);
            }
        });

        productoApiAdapter = new ProductoApiAdapter();
        hojaRutaApiAdapter = new HojaRutaApiAdapter();

        loadProductos();
        loadHojaRuta("19/12/2016", chofer.getChoferId());
    }

    private void loadProductos() {
        Call<List<Producto>> call = productoApiAdapter.getAll();
        call.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                Log.i("INFO", "METHOD -- loadProductos() -- DETAIL -- Callback<List<Producto>>");
                Log.i("INFO", "METHOD -- loadProducto() -- DETAIL -- Response message: " + response.raw());

                List<Producto> productos = response.body();

                if (productos != null) {
                    ProductoService.insertProductos(productos);
                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
            }
        });
    }

    private void loadHojaRuta(String fecha, Long choferId) {
        try {
            hoja = HojaRutaService.getHojaRutaByFechaAndChofer(Utils.stringToDate(fecha), chofer.getChoferId());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (hoja != null) {
            Log.i("INFO", "METHOD -- loadHojaRuta() -- DETAIL -- Hoja Ruta: " + hoja);
            return;
        }

        Call<HojaRuta> call = hojaRutaApiAdapter.getByFechaChofer(fecha, choferId);
        call.enqueue(new Callback<HojaRuta>() {
            @Override
            public void onResponse(Call<HojaRuta> call, Response<HojaRuta> response) {
                Log.i("INFO", "METHOD -- loadHojaRuta() -- DETAIL -- Callback<HojaRuta>");
                Log.i("INFO", "METHOD -- loadHojaRuta() -- DETAIL -- Response message: " + response.raw());

                hoja = response.body();

                HojaRutaService.insertHojaRuta(hoja);
            }

            @Override
            public void onFailure(Call<HojaRuta> call, Throwable t) {
                Log.i("INFO", "METHOD -- loadHojaRuta() -- DETAIL -- Callback<HojaRuta>");
                Log.i("INFO", "METHOD -- loadHojaRuta() -- DETAIL -- Exception: " + t.toString());

                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
            }
        });
    }
}
