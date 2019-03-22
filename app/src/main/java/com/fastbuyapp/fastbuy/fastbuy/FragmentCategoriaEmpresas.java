package com.fastbuyapp.fastbuy.fastbuy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fastbuyapp.omar.fastbuy.R;

public class FragmentCategoriaEmpresas extends Fragment {


    public FragmentCategoriaEmpresas() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_categorias_empresas, container, false);
        return view;
    }

}
