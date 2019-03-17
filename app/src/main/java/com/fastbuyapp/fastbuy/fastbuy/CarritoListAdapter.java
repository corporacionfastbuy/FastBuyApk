package com.fastbuyapp.fastbuy.fastbuy;

import android.content.Context;
import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.fastbuyapp.omar.fastbuy.R;
import com.fastbuyapp.fastbuy.fastbuy.entidades.PedidoDetalle;

import java.util.ArrayList;

/**
 * Created by OMAR on 23/08/2018.
 */

public class CarritoListAdapter extends ArrayAdapter<PedidoDetalle> {
    private Context context;
    private int layout;
    private ArrayList<PedidoDetalle> pedidoList;
    private Typeface fuente1;
    private ConstraintLayout capa;
    private Button bt_main;
    private ImageView imagen;
    private EditText campo;

    public CarritoListAdapter(Context context, int layout, ArrayList<PedidoDetalle> _productosList) {
        super(context, layout, _productosList);
        this.context = context;
        this.layout = layout;
        this.pedidoList = _productosList;
    }

    @Override
    public int getCount() {
        return pedidoList.size();
    }

    @Override
    public PedidoDetalle getItem(int pos) {
        return pedidoList.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    private class ViewHolder{
        TextView txtCantidadPedido;
        TextView txtNombreProducto;
        TextView txtNombreEmpresa;
        TextView txtPrecio;
    }

    @Override
    public View getView(int pos, View row, ViewGroup viewGroup) {
        //View row = view;
        ViewHolder holder = null ;
        if (row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout,viewGroup,false);
            holder = new ViewHolder();
            holder.txtCantidadPedido = (TextView) row.findViewById(R.id.txtCantidadPedido);
            holder.txtPrecio = (TextView) row.findViewById(R.id.txtPrecio);
            holder.txtNombreProducto = (TextView) row.findViewById(R.id.txtNombreProducto);
            holder.txtNombreEmpresa = (TextView) row.findViewById(R.id.txtNombreEmpresa);
            row.setTag(holder);
        }
        else{
            holder = (ViewHolder) row.getTag();
        }

        final PedidoDetalle pedidoDetalle = getItem(pos);
        //String fuente = "fonts/fuente1.ttf";
        //this.fuente1 = Typeface.createFromAsset(context.getAssets(), fuente);
        holder.txtCantidadPedido.setText(String.valueOf(pedidoDetalle.getCantidad()));
        holder.txtNombreProducto.setText(pedidoDetalle.getProducto().getDescripcion());
        holder.txtNombreEmpresa.setText(pedidoDetalle.getProducto().getEmpresa().getNombreComercial());
        //holder.txtCantidadPedido.setTypeface(fuente1);*/
        holder.txtPrecio.setText("S/ " + String.format("%.2f",pedidoDetalle.getTotal()));
        return  row;


    }
}
