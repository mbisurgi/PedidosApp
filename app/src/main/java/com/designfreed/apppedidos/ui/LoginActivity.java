package com.designfreed.apppedidos.ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.designfreed.apppedidos.R;
import com.designfreed.apppedidos.entities.Chofer;
import com.designfreed.apppedidos.services.LoginService;
import com.designfreed.apppedidos.utils.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {
    private EditText txtUsername;
    private EditText txtPassword;
    private Button btnLogin;
    private ProgressBar pbProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsername = (EditText) findViewById(R.id.username);
        txtPassword = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.login);
        pbProgress = (ProgressBar) findViewById(R.id.progress);

        pbProgress.setVisibility(View.GONE);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {
                    new UserLoginTask().execute(txtUsername.getText().toString(), txtPassword.getText().toString());

                    pbProgress.setVisibility(View.VISIBLE);
                } else {
                    pbProgress.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "No hay conexion a internet", Toast.LENGTH_LONG);
                }
            }
        });
    }

    private class UserLoginTask extends AsyncTask<String, Void, Chofer> {
        @Override
        protected Chofer doInBackground(String... strings) {
            String username = strings[0];
            String password = strings[1];

            StringBuilder urlService = new StringBuilder("http://192.168.0.3:8080/PedidosAPI/services/pedidosService/login?");

            urlService.append("username=" + username);
            urlService.append("&");
            urlService.append("password=" + password);

            String json = "";

            HttpURLConnection httpURLConnection = null;

            Chofer chofer = null;

            try {
                URL url = new URL(urlService.toString());

                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();

                if (httpURLConnection.getResponseCode() == 200) {
                    InputStream inputStream = httpURLConnection.getInputStream();
                    json = Utils.readFromStream(inputStream);

                    chofer = LoginService.getChofer(json);
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

            return chofer;
        }

        @Override
        protected void onPostExecute(Chofer chofer) {
            super.onPostExecute(chofer);

            if (chofer != null) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("chofer", chofer);

                startActivity(intent);
            }

            pbProgress.setVisibility(View.GONE);
        }
    }
}
