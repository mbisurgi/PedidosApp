package com.designfreed.apppedidos.ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.designfreed.apppedidos.R;
import com.designfreed.apppedidos.entities.Chofer;
import com.designfreed.apppedidos.rest.adapters.LoginApiAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText txtUsername;
    private EditText txtPassword;
    private Button btnLogin;
    private ProgressBar pbProgress;

    private LoginApiAdapter loginApiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsername = (EditText) findViewById(R.id.username);
        txtPassword = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.login);
        pbProgress = (ProgressBar) findViewById(R.id.progress);

        pbProgress.setVisibility(View.GONE);

        loginApiAdapter = new LoginApiAdapter();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {
                    login();
                } else {
                    pbProgress.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "No hay conexion a internet", Toast.LENGTH_LONG);
                }
            }
        });
    }

    private void login() {
        Call<Chofer> call = loginApiAdapter.login(txtUsername.getText().toString(), txtPassword.getText().toString());
        call.enqueue(new Callback<Chofer>() {
            @Override
            public void onResponse(Call<Chofer> call, Response<Chofer> response) {
                Chofer chofer = response.body();

                if (chofer != null) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("chofer", chofer);

                    startActivity(intent);
                }

                pbProgress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Chofer> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
            }
        });

        pbProgress.setVisibility(View.VISIBLE);
    }
}
