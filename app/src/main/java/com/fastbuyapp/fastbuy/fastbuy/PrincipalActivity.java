package com.fastbuyapp.fastbuy.fastbuy;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.fastbuyapp.omar.fastbuy.R;
import com.fastbuyapp.fastbuy.fastbuy.entidades.Globales;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        final Spinner cboCiudad = (Spinner) findViewById(R.id.cboCiudad1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.ciudades,R.layout.support_simple_spinner_dropdown_item);
        cboCiudad.setAdapter(adapter);
        if (Globales.ciudad != null) {
            int spinnerPosition = adapter.getPosition(Globales.ciudad);
            cboCiudad.setSelection(spinnerPosition);
        }
        final ImageButton btnCategoriasEmpresas = (ImageButton) findViewById(R.id.btnCategoriaEmpresas);
        final ImageButton btnCarritoCompras = (ImageButton) findViewById(R.id.btnCarritoCompras);
        final FragmentSubCategoriaEmpresas panelCategoriaEmpresas = new FragmentSubCategoriaEmpresas();
        final FragmentCarrito panelCarritoCompras = new FragmentCarrito();
        final FragmentManager manager = getSupportFragmentManager();

        btnCategoriasEmpresas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txtBarra = (TextView) findViewById(R.id.txtBarra);
                txtBarra.setText("");
                btnCategoriasEmpresas.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.fastbuy));
                Bitmap bmpCategoria = BitmapFactory.decodeResource(getResources(), R.drawable.ic_tienda2);
                btnCategoriasEmpresas.setImageBitmap(bmpCategoria);

                btnCarritoCompras.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.blanco));
                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_carrito1);
                btnCarritoCompras.setImageBitmap(bmp);

                manager.beginTransaction()
                        .replace(R.id.contenedorPrincipal, panelCategoriaEmpresas, panelCategoriaEmpresas.getTag())
                        .commit();
            }
        });
        btnCarritoCompras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txtBarra = (TextView) findViewById(R.id.txtBarra);
                txtBarra.setText("");

                btnCategoriasEmpresas.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.blanco));
                Bitmap bmpCategoria = BitmapFactory.decodeResource(getResources(), R.drawable.ic_tienda1);
                btnCategoriasEmpresas.setImageBitmap(bmpCategoria);

                btnCarritoCompras.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.fastbuy));
                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_carrito2);
                btnCarritoCompras.setImageBitmap(bmp);

                manager.beginTransaction()
                        .replace(R.id.contenedorPrincipal, panelCarritoCompras, panelCarritoCompras.getTag())
                        .commit();
            }
        });

        btnCategoriasEmpresas.performClick();
    }
}
