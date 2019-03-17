package com.fastbuyapp.fastbuy.fastbuy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fastbuyapp.omar.fastbuy.R;
import com.fastbuyapp.fastbuy.fastbuy.config.Servidor;
import com.fastbuyapp.fastbuy.fastbuy.entidades.Globales;
import com.fastbuyapp.fastbuy.fastbuy.entidades.Rubro;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class FragmentCategoriaEmpresas extends Fragment {
    GridView gridView;
    ArrayList<Rubro> list;
    RubroListAdapter adapter = null;
    public FragmentCategoriaEmpresas() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_categoria_empresas, container, false);
        try {
            listarRubros(String.valueOf(1));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        final FragmentListaEmpresas panelListaEmpresas = new FragmentListaEmpresas();
        final FragmentManager manager = getActivity().getSupportFragmentManager();

        GridView gv = (GridView) view.findViewById(R.id.tablaRubros);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int codigo = (list.get(i).getCodigo());
                String descripcion = list.get(i).getDescripcion();
                String imagen = list.get(i).getImagen();
                Globales.rubro = codigo;
                manager.beginTransaction()
                        .replace(R.id.contenedorPrincipal, panelListaEmpresas, panelListaEmpresas.getTag())
                        .commit();

            }
        });
        return view;

    }

    public void listarRubros(String ciudad) throws UnsupportedEncodingException {
        Servidor s = new Servidor();
        String consulta = "http://"+s.getServidor()+"/app/consultasapp/app_listarubros_xciudad.php?jurisdiccion="+ciudad;
        EnviarRecibirDatos(consulta);
    }

    public void EnviarRecibirDatos(String URL){

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.length()>0){
                    try {
                        JSONArray ja = new JSONArray(response);
                        CargarLista(ja);
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

    public void CargarLista(JSONArray ja) throws JSONException {
        try {
            list = new ArrayList<>();
            for (int i = 0; i < ja.length(); i++) {
                JSONObject objeto = ja.getJSONObject(i);
                Rubro rubro = new Rubro();
                rubro.setCodigo(objeto.getInt("codigo"));
                rubro.setDescripcion(objeto.getString("descripcion"));
                rubro.setImagen(objeto.getString("imagen"));
                rubro.setEstado(objeto.getString("estado"));
                rubro.setUbicacion(objeto.getString("ubicacion"));
                list.add(rubro);

            }

            gridView = (GridView) getActivity().findViewById(R.id.tablaRubros);
            adapter = new RubroListAdapter(getActivity(), R.layout.list_rubro_item, list);
            gridView.setAdapter(adapter);
        }
        catch(Exception ex) {}
    }
}
