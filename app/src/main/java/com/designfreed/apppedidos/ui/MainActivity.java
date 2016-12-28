package com.designfreed.apppedidos.ui;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.designfreed.apppedidos.R;
import com.designfreed.apppedidos.bd.CrmDbHelper;
import com.designfreed.apppedidos.bd.HojaRutaRepositoryDb;
import com.designfreed.apppedidos.entities.Chofer;
import com.designfreed.apppedidos.entities.HojaRuta;
import com.designfreed.apppedidos.repositories.HojaRutaRepository;
import com.designfreed.apppedidos.services.PedidoService;
import com.designfreed.apppedidos.utils.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private TextView txtChofer;
    private ImageButton btnVoleos;

    private CrmDbHelper dbHelper;
    private SQLiteDatabase db;
    private Chofer chofer;
    private HojaRuta hoja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //dbHelper = CrmDbHelper.getInstance(this);
        //db = dbHelper.getReadableDatabase();

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

        new DownloadHojaRutaTask().execute(chofer);
    }

    private class DownloadHojaRutaTask extends AsyncTask<Chofer, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Chofer... choferes) {
            Boolean estado = false;

            StringBuilder urlService = new StringBuilder("http://192.168.0.3:8080/PedidosAPI/services/pedidosService/getHojaRuta?");

            String fechaString = "19/12/2016";
            //Date fecha = Calendar.getInstance().getTime();

            urlService.append("fecha=" + fechaString);
            urlService.append("&");
            urlService.append("choferId=" + choferes[0].getChoferId());

            HojaRutaRepository hojaRutaRepository = new HojaRutaRepository();

            //HojaRutaRepositoryDb hojaRutaRepositoryDb = new HojaRutaRepositoryDb(getApplicationContext());

            //hoja = hojaRutaRepositoryDb.getHojaRuta(fechaString, chofer.getId());
            //hoja = hojaRutaRepository.getByIdCrm(1L);
            //hoja = HojaRuta.findById(HojaRuta.class, 1L);

            if (hoja != null) {
                return false;
            }

            String json = "";

            HttpURLConnection httpURLConnection = null;

            //HojasRuta hoja = null;

            try {
                URL url = new URL(urlService.toString());

                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();

                if (httpURLConnection.getResponseCode() == 200) {
                    InputStream inputStream = httpURLConnection.getInputStream();
                    json = Utils.readFromStream(inputStream);

                    hoja = PedidoService.getHojaRuta(json);

                    if (hoja != null) {
                        //hojaRutaRepositoryDb.insertHojaRuta(hoja);
                        Long i = hoja.save();

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
}
