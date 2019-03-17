package com.fastbuyapp.fastbuy.fastbuy;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fastbuyapp.fastbuy.fastbuy.config.Servidor;
import com.fastbuyapp.fastbuy.fastbuy.entidades.Categoria;
import com.fastbuyapp.fastbuy.fastbuy.entidades.Globales;
import com.fastbuyapp.omar.fastbuy.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class FragmentListaCategoriasProductos extends Fragment {
    ArrayList<Categoria> list;
    CategoriaListAdapter adapter = null;
    RecyclerView recicler;
    public void listarCategorias(String empresa, View view) throws UnsupportedEncodingException {
        Servidor s = new Servidor();
        String consulta = "http://"+s.getServidor()+"/app/controllers/CargarCategoriaPorEmpresa.php?empresa="+empresa;
        EnviarRecibirDatos(consulta, view);
    }
    private Typeface fuente1;

    public FragmentListaCategoriasProductos() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_lista_categorias_productos, container, false);
        try {
            listarCategorias(String.valueOf(Globales.empresaSeleccionada), view);
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
        String[] mDataset;
        list = new ArrayList<>();
        for(int i = 0; i < ja.length(); i++) {
            JSONObject objeto = ja.getJSONObject(i);
            Categoria categoria = new Categoria();
            categoria.setCodigo(objeto.getInt("codigo"));
            categoria.setDescripcion(objeto.getString("descripcion"));
            list.add(categoria);
        }
        //Empresa empresa = new Empresa();
        //empresa.setCodigo(Integer.parseInt(codigo));
        //empresa.setNombreComercial(nombreComercial);

        ReciclerAdapterCategorias adapter = new ReciclerAdapterCategorias(list);
        recicler = (RecyclerView) view.findViewById(R.id.rcvListaCategoriasProductos);
        recicler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        //adapter = new CategoriaListAdapter(getActivity(), R.layout.list_categoria_item, list);
        recicler.setAdapter(new ReciclerAdapterCategorias(list));
    }
}
