package com.designfreed.apppedidos.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        loadProductos();
        loadHojaRuta("19/12/2016", chofer.getChoferId());

        //new DownloadProductosTask().execute();
        //new DownloadInfoTask().execute(chofer);
    }

    private class DownloadProductosTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... strings) {
            Boolean estado = false;

            String urlService = "http://192.168.0.3:8080/PedidosAPI/services/pedidosService/getProductos";

            String json = "";

            HttpURLConnection httpURLConnection = null;

            try {
                URL url = new URL(urlService.toString());

                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();

                if (httpURLConnection.getResponseCode() == 200) {
                    InputStream inputStream = httpURLConnection.getInputStream();
                    json = Utils.readFromStream(inputStream);

                    List<Producto> productos = ProductoService.jsonToProductos(json);

                    ProductoService.insertProductos(productos);

                    estado = true;
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
            }

            return estado;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }
    }

    private class DownloadInfoTask extends AsyncTask<Chofer, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Chofer... choferes) {
            Boolean estado = false;

            StringBuilder urlService = new StringBuilder("http://192.168.0.3:8080/PedidosAPI/services/pedidosService/getHojaRuta?");

            String fechaString = "19/12/2016";
            //Date fecha = Calendar.getInstance().getTime();

            urlService.append("fecha=" + fechaString);
            urlService.append("&");
            urlService.append("choferId=" + choferes[0].getChoferId());

            try {
                hoja = HojaRutaService.getHojaRutaByFechaAndChofer(Utils.stringToDate(fechaString), chofer.getChoferId());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (hoja != null) {
                return false;
            }

            String json = "";

            HttpURLConnection httpURLConnection = null;

            try {
                URL url = new URL(urlService.toString());

                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();

                if (httpURLConnection.getResponseCode() == 200) {
                    InputStream inputStream = httpURLConnection.getInputStream();
                    json = Utils.readFromStream(inputStream);

                    hoja = HojaRutaService.jsonToHojaRuta(json);

                    if (hoja != null) {
                        HojaRutaService.insertHojaRuta(hoja);

                        estado = true;
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
            }

            return estado;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }
    }

    private void loadProductos() {
        Call<List<Producto>> call = productoApiAdapter.getAll();
        call.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
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
            return;
        }

        Call<HojaRuta> call = hojaRutaApiAdapter.getByFechaChofer(fecha, choferId);
        call.enqueue(new Callback<HojaRuta>() {
            @Override
            public void onResponse(Call<HojaRuta> call, Response<HojaRuta> response) {
                hoja = response.body();

                HojaRutaService.insertHojaRuta(hoja);
            }

            @Override
            public void onFailure(Call<HojaRuta> call, Throwable t) {

            }
        });
    }
}
