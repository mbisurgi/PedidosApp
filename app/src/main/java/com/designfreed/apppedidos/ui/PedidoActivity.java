package com.designfreed.apppedidos.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.designfreed.apppedidos.R;
import com.designfreed.apppedidos.adapters.PedidoItemAdapter;
import com.designfreed.apppedidos.entities.ItemPedido;
import com.designfreed.apppedidos.entities.Producto;

import java.util.ArrayList;
import java.util.List;

public class PedidoActivity extends AppCompatActivity {
    private PedidoItemAdapter adapter;
    private ListView itemsListView;
    private EditText txtArticulo;
    private EditText txtCantidad;
    private EditText txtPrecio;
    private Button btnAgregar;

    private List<ItemPedido> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        adapter = new PedidoItemAdapter(this, items);

        itemsListView = (ListView) findViewById(R.id.list);
        itemsListView.setAdapter(adapter);

        txtArticulo = (EditText) findViewById(R.id.txtArticulo);
        txtCantidad = (EditText) findViewById(R.id.txtCantidad);
        txtPrecio = (EditText) findViewById(R.id.txtPrecio);

        btnAgregar = (Button) findViewById(R.id.agregar);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemPedido itemPedido = new ItemPedido();

                Producto pro = new Producto(1L, "Garrafa 10kg");

                Integer cantidad = Integer.valueOf(txtCantidad.getText().toString());
                Double precio = Double.valueOf(txtPrecio.getText().toString());

                itemPedido.setProducto(pro);
                itemPedido.setCantidad(cantidad);
                itemPedido.setPrecio(precio);

                items.add(itemPedido);

                adapter.notifyDataSetChanged();

                txtArticulo.setText("");
                txtCantidad.setText("");
                txtPrecio.setText("");
            }
        });
    }
}
