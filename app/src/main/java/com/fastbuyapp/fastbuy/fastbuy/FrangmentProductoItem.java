package com.fastbuyapp.fastbuy.fastbuy;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fastbuyapp.omar.fastbuy.R;

public class FrangmentProductoItem extends Fragment {

    public FrangmentProductoItem() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frangment_producto_item, container, false);
    }

}
