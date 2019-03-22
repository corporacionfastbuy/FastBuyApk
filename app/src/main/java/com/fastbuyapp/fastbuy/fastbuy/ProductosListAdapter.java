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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fastbuyapp.omar.fastbuy.R;
import com.fastbuyapp.fastbuy.fastbuy.config.Servidor;
import com.fastbuyapp.fastbuy.fastbuy.entidades.Globales;
import com.fastbuyapp.fastbuy.fastbuy.entidades.PedidoDetalle;
import com.fastbuyapp.fastbuy.fastbuy.entidades.Producto;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by OMAR on 23/08/2018.
 */

public class ProductosListAdapter extends ArrayAdapter<Producto> {
    private Context context;
    private int layout;
    private ArrayList<Producto> productosList;
    //private Typeface fuente1;
    private ConstraintLayout capa;
    private Button bt_main;
    private ImageView imagen;
    private EditText campo;

    public ProductosListAdapter(Context context, int layout, ArrayList<Producto> _productosList) {
        super(context, layout, _productosList);
        this.context = context;
        this.layout = layout;
        this.productosList = _productosList;
    }

    @Override
    public int getCount() {
        return productosList.size();
    }

    @Override
    public Producto getItem(int pos) {
        return productosList.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView txtDescripcion;
        TextView txtCategoria;
        TextView txtPrecio;
        Button btnMas;
        Button btnMenos;
        ImageButton btnAgregar;
        TextView txtCantidad;
        ArrayList<PedidoDetalle> lista;
        TextView txtCodigo;
    }

    @Override
    public View getView(int pos, View row, ViewGroup viewGroup) {
        //View row = view;
        ViewHolder holder = null ;
        if (row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout,viewGroup,false);
            holder = new ViewHolder();
            holder.txtDescripcion = (TextView) row.findViewById(R.id.txtDescripcion);
            holder.txtPrecio = (TextView) row.findViewById(R.id.txtPrecio);
            holder.txtCategoria = (TextView) row.findViewById(R.id.txtCategoria);
            holder.imageView = (ImageView) row.findViewById(R.id.imgProductos);
            holder.btnMas = (Button) row.findViewById(R.id.btnMas);
            holder.btnMenos = (Button) row.findViewById(R.id.btnMenos);
            holder.btnAgregar = (ImageButton) row.findViewById(R.id.btnAgregar);
            holder.txtCantidad = (TextView) row.findViewById(R.id.txtCantidad);
            final ViewHolder finalHolder = holder;
            holder.btnMas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView txtCantidad = finalHolder.txtCantidad;
                    int cant = Integer.parseInt(String.valueOf(txtCantidad.getText()));
                    cant++;
                    String ncant = String.valueOf(cant);
                    txtCantidad.setText(ncant);
                }
            });
            holder.btnMenos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView txtCantidad = finalHolder.txtCantidad;
                    int cant = Integer.parseInt(String.valueOf(txtCantidad.getText()));
                    cant--;
                    if(cant<=0)
                        cant = 0;
                    String ncant = String.valueOf(cant);
                    txtCantidad.setText(ncant);
                }
            });
            holder.btnAgregar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView txtCantidad = finalHolder.txtCantidad;
                    int cant = Integer.parseInt(String.valueOf(txtCantidad.getText()));
                    int position = (int) v.getTag();
                    final Producto producto = getItem(position);
                    if(cant>0){
                        PedidoDetalle pd = new PedidoDetalle();
                        Globales g = Globales.getInstance();
                        boolean existe = false;
                        int ubic = 0 ;
                        int cont = 0;
                        int cantActual = 0;
                        for (PedidoDetalle detalle: g.getListaPedidos()) {
                            int proCodigo = detalle.getProducto().getCodigo();
                            if(proCodigo == producto.getCodigo()){
                                existe = true;
                                ubic = cont;
                                cantActual = detalle.getCantidad();
                            }
                            cont++;
                        }
                        if(existe){
                            int nc = cant + cantActual;
                            pd.setProducto(producto);
                            pd.setCantidad(nc);
                            pd.setTotal((float) (producto.getPrecio() * nc));
                            g.getListaPedidos().set(ubic,pd);
                        }else{
                            pd.setProducto(producto);
                            pd.setCantidad(cant);
                            pd.setTotal((float) (producto.getPrecio() * cant));
                            g.agregarPedido(pd);
                        }
                        Toast toast = Toast.makeText(context,"Añadido al carrito", Toast.LENGTH_SHORT);
                        View vistaToast = toast.getView();
                        vistaToast.setBackgroundResource(R.drawable.toast_exito);
                        toast.show();
                        txtCantidad.setText("0");
                    }
                    else{
                        Toast toast = Toast.makeText(context,"Debe ingresar una cantidad válida", Toast.LENGTH_SHORT);
                        View vistaToast = toast.getView();
                        vistaToast.setBackgroundResource(R.drawable.toast_alerta);
                        toast.show();
                    }
                }
            });

            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            row.setTag(holder);
        }
        else{
            holder = (ViewHolder) row.getTag();
        }

        final Producto producto = getItem(pos);
        //Toast.makeText(context, String.valueOf(pos),Toast.LENGTH_SHORT).show();
        String fuente = "fonts/fuente1.ttf";
        //this.fuente1 = Typeface.createFromAsset(context.getAssets(), fuente);

        holder.txtDescripcion.setText(producto.getDescripcion());
        //holder.txtDescripcion.setTypeface(fuente1);
        holder.txtCategoria.setText(producto.getCategoria().getDescripcion());
        holder.txtPrecio.setText("S/ "+ String.format("%.2f",producto.getPrecio()));
        //holder.txtCantidad.setTag(R.id.txtCantidad);
        holder.btnMas.setTag(Integer.valueOf(pos));
        holder.btnMenos.setTag(Integer.valueOf(pos));
        holder.btnAgregar.setTag(Integer.valueOf(pos));
        String nombreImagen = producto.getImagen();
        Servidor s = new Servidor();
        String url = "http://"+s.getServidor()+"/productos/fotos/" + nombreImagen;
        Picasso.with(context)
                .load(url)
                .error(R.mipmap.ic_launcher)
                .fit()
                .centerInside()
                .into(holder.imageView);

        return  row;


    }
}
