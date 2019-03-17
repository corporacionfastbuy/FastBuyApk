package com.fastbuyapp.fastbuy.fastbuy;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fastbuyapp.fastbuy.fastbuy.entidades.Rubro;
import com.fastbuyapp.omar.fastbuy.R;
import com.fastbuyapp.fastbuy.fastbuy.config.Servidor;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by OMAR on 11/03/2019.
 */

public class RubroListAdapter  extends BaseAdapter {
    private Typeface fuente1;
    private Context context;
    private int layout;
    private ArrayList<Rubro> rubroList;

    public RubroListAdapter(Context context, int layout, ArrayList<Rubro> rubroList) {
        this.context = context;
        this.layout = layout;
        this.rubroList = rubroList;
    }

    @Override
    public int getCount() {
        return rubroList.size();
    }

    @Override
    public Object getItem(int position) {
        return rubroList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        int codigo;
        TextView descripcion;
        ImageView imagen;
    }

    @Override
    public View getView(final int i, View row, ViewGroup viewGroup) {
        RubroListAdapter.ViewHolder holder = null;
        if (row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout,viewGroup,false);
            holder = new RubroListAdapter.ViewHolder();
            holder.descripcion = (TextView) row.findViewById(R.id.txtNombreRubro);
            holder.imagen = (ImageView) row.findViewById(R.id.ivimagenrubro);
            row.setTag(holder);
        }
        else{
            holder = (RubroListAdapter.ViewHolder) row.getTag();
        }

        String fuente = "fonts/fuente1.ttf";
        this.fuente1 = Typeface.createFromAsset(context.getAssets(), fuente);
        Rubro rubro = rubroList.get(i);
        holder.descripcion.setText(rubro.getDescripcion());
        holder.codigo = rubro.getCodigo();
        holder.descripcion.setTypeface(fuente1);
        String nombreImagen = rubro.getImagen();
        Servidor s = new Servidor();
        String url = "http://"+s.getServidor()+"/rubros/imagenes/" + nombreImagen;
        Picasso.with(context)
                .load(url)
                .error(R.mipmap.ic_launcher)
                .fit()
                .centerInside()
                .into(holder.imagen);
        return  row;
    }
}
