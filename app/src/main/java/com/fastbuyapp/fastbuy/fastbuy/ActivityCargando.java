package com.fastbuyapp.fastbuy.fastbuy;

import android.content.Context;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fastbuyapp.fastbuy.fastbuy.entidades.Globales;

public class ActivityCargando extends Animation {
    private ProgressBar progressBar;
    private float from;
    private Context context;
    private TextView porcentaje;
    private float  to;

    public ActivityCargando(Context context, ProgressBar progressBar, TextView porcentaje , float from, float to) {
        super();
        this.context = context;
        this.progressBar = progressBar;
        this.porcentaje = porcentaje;
        this.from = from;
        this.to = to;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        float value = from + (to - from) * interpolatedTime;
        //porcentaje.setText(String.valueOf((int) value) + " %");
        progressBar.setProgress((int) value);
        if(value == 100){
            Intent i = new Intent(context, PortadaActivity.class );
            i.putExtra("ubicacion", Globales.ciudad);
            context.startActivity(i);
        }
    }

}
