package com.fastbuyapp.fastbuy.fastbuy;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fastbuyapp.fastbuy.fastbuy.config.Servidor;
import com.fastbuyapp.fastbuy.fastbuy.entidades.Empresa;
import com.fastbuyapp.omar.fastbuy.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by OMAR on 22/08/2018.
 */

public class EmpresaListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Empresa> empresaList;

    public EmpresaListAdapter(Context context, int layout, ArrayList<Empresa> empresaList) {
        this.context = context;
        this.layout = layout;
        this.empresaList = empresaList;
    }

    @Override
    public int getCount() {
        return empresaList.size();
    }

    @Override
    public Object getItem(int pos) {
        return empresaList.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView txtNombreEmpresa;
        TextView txtDescripcion;
        TextView txtEstadoAbierto;
        TextView txtPrecio;
    }

    private Typeface fuente1;

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row = view;
        ViewHolder holder = new ViewHolder();
        if (row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);
            holder.txtNombreEmpresa = (TextView) row.findViewById(R.id.txtNombreEmpresa);
            holder.imageView = (ImageView) row.findViewById(R.id.imgEmpresas2);
            holder.txtDescripcion = (TextView) row.findViewById(R.id.txtDescripcion);
            holder.txtEstadoAbierto = (TextView) row.findViewById(R.id.lblEstadoAbierto);
            //holder.txtPrecio = (TextView) row.findViewById(R.id.lblPrecio);
            row.setTag(holder);
        }
        else{
            holder = (ViewHolder) row.getTag();
        }
        String fuente = "fonts/fuente1.ttf";
        this.fuente1 = Typeface.createFromAsset( context.getAssets(), fuente);
        Empresa empresa = empresaList.get(i);
        holder.txtNombreEmpresa.setText(empresa.getNombreComercial());
        holder.txtNombreEmpresa.setTypeface(fuente1);
        holder.txtDescripcion.setText(empresa.getRazonSocial());
        holder.txtEstadoAbierto.setText(empresa.getEstadoAbierto());
        String estadoabierto = empresa.getEstadoAbierto();
        //Toast.makeText(context,"'"+estadoabierto+"'",Toast.LENGTH_SHORT).show();
        if(estadoabierto.equals("Abierto")){
            holder.txtEstadoAbierto.setBackgroundColor(ContextCompat.getColor(context, R.color.etiqueta));
            holder.txtEstadoAbierto.setTextColor(ContextCompat.getColor(context, R.color.fastbuy));
        }else{
            holder.txtEstadoAbierto.setBackgroundColor(ContextCompat.getColor(context, R.color.etiquetaRoja));
            holder.txtEstadoAbierto.setTextColor(ContextCompat.getColor(context, R.color.alert));
        }
        //holder.txtPrecio.setText(String.format("S/ %.2f",empresa.getPrecio()));
        String nombreImagen = empresa.getImagen();
        Servidor s = new Servidor();
        String url = "http://"+s.getServidor()+"/empresas/logos/" + nombreImagen;
        Picasso.with(context)
                .load(url)
                .error(R.mipmap.ic_launcher)
                .fit()
                .centerInside()
                .into(holder.imageView);
        return  row;
    }
}
