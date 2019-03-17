package com.fastbuyapp.fastbuy.fastbuy;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fastbuyapp.omar.fastbuy.R;
import com.fastbuyapp.fastbuy.fastbuy.config.Servidor;
import com.fastbuyapp.fastbuy.fastbuy.entidades.Categoria;
import com.fastbuyapp.fastbuy.fastbuy.entidades.Empresa;
import com.fastbuyapp.fastbuy.fastbuy.entidades.Globales;
import com.fastbuyapp.fastbuy.fastbuy.entidades.Producto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class FragmentListaProductos extends Fragment {
    GridView gridView;
    public String codigo;
    public String nombreComercial;
    public String categoria;
    ArrayList<Producto> list;
    ProductosListAdapter adapter = null;

    public FragmentListaProductos() {
        // Required empty public constructor
    }
    public void listarProductos(String empresa, String filtro, String categoria, View v) throws UnsupportedEncodingException {
        Servidor s = new Servidor();
        String c = URLEncoder.encode(filtro, "UTF-8");
        String consulta = "http://"+s.getServidor()+"/app/controllers/CargarProductosPorEmpresa.php?empresa="+empresa+"&filtro="+filtro+"&categoria="+categoria;
        EnviarRecibirDatos(consulta, v);
    }

    private Typeface fuente1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_lista_productos, container, false);
        String fuente = "fonts/fuente1.ttf";
        this.fuente1 = Typeface.createFromAsset(getActivity().getAssets(), fuente);
        codigo = String.valueOf(Globales.empresaSeleccionada);
        //nombreComercial = getIntent().getStringExtra("nombreComercial");
        categoria = String.valueOf(Globales.catProductoSeleccionado);
        try {
            listarProductos(codigo,"",categoria, view);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return view;
    }
    public void EnviarRecibirDatos(String URL, final View view){

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.length()>0){
                    try {
                        JSONArray ja = new JSONArray(response);
                        CargarLista(ja, view);
                        //Toast.makeText(getApplicationContext(),"a: ", Toast.LENGTH_LONG);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);

    }

    public void CargarLista(JSONArray ja, View view) throws JSONException {
        list = new ArrayList<>();
        for(int i = 0; i < ja.length(); i++) {
            JSONObject objeto = ja.getJSONObject(i);
            Producto producto = new Producto();
            producto.setCodigo(objeto.getInt("codigo"));
            producto.setDescripcion(objeto.getString("descripcion"));
            producto.setPrecio(objeto.getDouble("precio"));
            producto.setImagen(objeto.getString("imagen"));
            producto.setEstado(objeto.getInt("estado"));

            JSONObject objeto2 = objeto.getJSONObject("categoria");
            Categoria categoria = new Categoria();
            categoria.setDescripcion(objeto2.getString("descripcion"));
            producto.setCategoria(categoria);

            JSONObject objeto3 = objeto.getJSONObject("empresa");
            Empresa empresa = new Empresa();
            empresa.setCodigo(objeto3.getInt("codigo"));
            empresa.setNombreComercial(objeto3.getString("nombreComercial"));
            producto.setEmpresa(empresa);
            list.add(producto);
        }
        gridView = (GridView) view.findViewById(R.id.tablaListaProductos);
        adapter = new ProductosListAdapter(getActivity(), R.layout.list_productos_item, list);
        gridView.setAdapter(adapter);
    }

}
