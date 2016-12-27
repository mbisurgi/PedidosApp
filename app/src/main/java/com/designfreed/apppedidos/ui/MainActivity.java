package com.designfreed.apppedidos.ui;

import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.designfreed.apppedidos.R;
import com.designfreed.apppedidos.bd.CrmDbHelper;
import com.designfreed.apppedidos.bd.HojaRutaRepository;
import com.designfreed.apppedidos.entities.Chofer;
import com.designfreed.apppedidos.entities.HojaRuta;
import com.designfreed.apppedidos.services.PedidoService;
import com.designfreed.apppedidos.utils.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private TextView txtChofer;

    private CrmDbHelper dbHelper;
    private SQLiteDatabase db;
    private Chofer chofer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = CrmDbHelper.getInstance(this);
        db = dbHelper.getReadableDatabase();

        chofer = (Chofer) getIntent().getSerializableExtra("chofer");

        txtChofer = (TextView) findViewById(R.id.chofer);
        txtChofer.setText(chofer.getNombre() + " " + chofer.getApellido());
        //txtChofer.setText("Maximiliano Bisurgi");

        new DownloadHojaRutaTask().execute(chofer);
    }

    private class DownloadHojaRutaTask extends AsyncTask<Chofer, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Chofer... choferes) {
            Boolean estado = false;

            StringBuilder urlService = new StringBuilder("http://10.0.2.2:8080/PedidosAPI/services/pedidosService/getHojaRuta?");

            String fechaString = "19/12/2016";
            //Date fecha = Calendar.getInstance().getTime();

            urlService.append("fecha=" + fechaString);
            urlService.append("&");
            urlService.append("choferId=" + choferes[0].getId());

            HojaRutaRepository hojaRutaRepository = new HojaRutaRepository(getApplicationContext());

            if (hojaRutaRepository.getHojaRuta(fechaString, chofer.getId()) != null) {
                return false;
            }

            String json = "";

            HttpURLConnection httpURLConnection = null;

            HojaRuta hoja = null;

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
                        hojaRutaRepository.insertHojaRuta(hoja);

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
