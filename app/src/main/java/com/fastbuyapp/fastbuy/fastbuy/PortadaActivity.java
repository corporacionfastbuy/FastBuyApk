package com.fastbuyapp.fastbuy.fastbuy;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.fastbuyapp.omar.fastbuy.R;
import com.fastbuyapp.fastbuy.fastbuy.entidades.Globales;

public class PortadaActivity extends AppCompatActivity {
    private Typeface fuente1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_portada);


        String ubicacion = getIntent().getStringExtra("ubicacion");
        //Toast.makeText(getBaseContext(),Globales.ciudad,Toast.LENGTH_SHORT).show();
        String fuente = "fonts/fuente1.ttf";
        this.fuente1 = Typeface.createFromAsset(getAssets(), fuente);
        //getSupportActionBar().hide();
        Button btnIniciar = (Button) findViewById(R.id.btnIniciar);
        btnIniciar.setTypeface(fuente1);
        TextView lblFast2 = (TextView) findViewById(R.id.lblFast2);
        TextView lblBuy2 = (TextView) findViewById(R.id.lblBuy2);
        lblFast2.setTypeface(fuente1);
        lblBuy2.setTypeface(fuente1);
        final Spinner cboCiudad = (Spinner) findViewById(R.id.cboCiudadCarrito);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.ciudades,R.layout.support_simple_spinner_dropdown_item);
        cboCiudad.setAdapter(adapter);
        if (ubicacion != null) {
            int spinnerPosition = adapter.getPosition(ubicacion);
            cboCiudad.setSelection(spinnerPosition);
        }
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), PrincipalActivity.class );
                i.putExtra("ubicacion", cboCiudad.getSelectedItem().toString());
                Globales.ubicacion = 1;
                Globales.ciudad = cboCiudad.getSelectedItem().toString();
                startActivity(i);
            }
        });


    }



}
