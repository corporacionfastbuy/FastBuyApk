package com.fastbuyapp.fastbuy.fastbuy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fastbuyapp.omar.fastbuy.R;
import com.fastbuyapp.fastbuy.fastbuy.config.Servidor;
import com.fastbuyapp.fastbuy.fastbuy.entidades.Empresa;
import com.fastbuyapp.fastbuy.fastbuy.entidades.Globales;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class FragmentListaEmpresas extends Fragment {
    GridView gridView;
    ArrayList<Empresa> list;
    EmpresaListAdapter adapter = null;

    public FragmentListaEmpresas() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_lista_empresas, container, false);
        Servidor s = new Servidor();
        String rubro = String.valueOf(Globales.Subcategoria);
        String ciudad = String.valueOf(1);
        String rub = null;
        String ciu = null;
        try {
            rub = URLEncoder.encode(rubro, "UTF-8");
            ciu = URLEncoder.encode(ciudad, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //Toast.makeText(getActivity(),rub + "-" + ciu,Toast.LENGTH_SHORT).show();
        String consulta = "http://"+s.getServidor()+"/app/consultasapp/app_listaempresas_xjurisdiccion_xrubro.php?jurisdiccion=" + ciu + "&rubro=" + rub;
        EnviarRecibirDatos(consulta, view);
        GridView gv = (GridView) view.findViewById(R.id.tablaListaEmpresas);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int codigo =(list.get(i).getCodigo());
                Globales.empresaSeleccionada = codigo;
                String nombreComercial = list.get(i).getNombreComercial();
                //String descripcion = list.get(i).getRazonSocial();
                //Toast.makeText(view.getContext(), String.valueOf(nombreComercial), Toast.LENGTH_SHORT).show();

                //String precio = String.format("S/ %.2f",list.get(i).getPrecio());
                //Globales.precio = list.get(i).getPrecio();
                //String estadoAbierto = list.get(i).getEstadoAbierto();
                //String imagem = list.get(i).getImagen();

                TextView txtBarra = (TextView) getActivity().findViewById(R.id.txtBarra);
                txtBarra.setText(String.valueOf(nombreComercial));

                FragmentVistaProductos panelListaProductos = new FragmentVistaProductos();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.contenedorPrincipal, panelListaProductos, panelListaProductos.getTag())
                        .commit();
                //abrirVistaCategoria(view,codigo,nombreComercial,estadoAbierto,descripcion, imagem);
            }
        });



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
        list = new ArrayList<>();
        for (int i = 0; i < ja.length(); i++) {
            JSONObject objeto = ja.getJSONObject(i);
            Empresa empresa = new Empresa();
            empresa.setCodigo(objeto.getInt("codigo"));
            empresa.setNombreComercial(objeto.getString("nombreComercial"));
            empresa.setRazonSocial(objeto.getString("razonSocial"));
            empresa.setDireccion(objeto.getString("direccion"));
            empresa.setImagen(objeto.getString("foto"));
            empresa.setTelefonos(objeto.getString("telefonos"));
            empresa.setEstado(objeto.getInt("estado"));
            empresa.setEstadoAbierto(objeto.getString("estadoAbierto"));
            list.add(empresa);

        }
        gridView = (GridView) view.findViewById(R.id.tablaListaEmpresas);
        adapter = new EmpresaListAdapter(getActivity(), R.layout.list_item, list);
        gridView.setAdapter(adapter);
    }
}
