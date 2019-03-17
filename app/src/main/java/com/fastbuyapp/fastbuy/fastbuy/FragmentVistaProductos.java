package com.fastbuyapp.fastbuy.fastbuy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fastbuyapp.omar.fastbuy.R;

public class FragmentVistaProductos extends Fragment {

    public FragmentVistaProductos() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_vista_productos, container, false);
        final FragmentListaCategoriasProductos panelCategoriasProductos = new FragmentListaCategoriasProductos();
        final FragmentManager manager = getActivity().getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.PanelCategoriasGeneral, panelCategoriasProductos, panelCategoriasProductos.getTag())
                .commit();
        //Button btn = (Button) view.findViewById(R.id.btnCategoria);
        //btn.performClick();
        return view;
    }

}
