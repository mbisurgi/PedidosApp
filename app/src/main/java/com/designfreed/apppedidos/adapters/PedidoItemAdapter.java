package com.designfreed.apppedidos.adapters;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.designfreed.apppedidos.R;
import com.designfreed.apppedidos.entities.ItemPedido;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class PedidoItemAdapter extends ArrayAdapter<ItemPedido> {
    public PedidoItemAdapter(Context context, List<ItemPedido> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.detalle_list_item, parent, false);
        }

        ItemPedido item = getItem(position);

        TextView articulo = (TextView) listItemView.findViewById(R.id.articulo);
        articulo.setText(item.getProducto().getProducto());

        TextView cantidad = (TextView) listItemView.findViewById(R.id.cantidad);
        cantidad.setText(item.getCantidad());

        TextView precio = (TextView) listItemView.findViewById(R.id.precio);
        precio.setText(formatSaldo(item.getPrecio()));

        return listItemView;
    }

    private String formatSaldo(Double saldo) {
        Locale locale = new Locale("es", "AR");

        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

        return currencyFormatter.format(saldo);
    }
}
