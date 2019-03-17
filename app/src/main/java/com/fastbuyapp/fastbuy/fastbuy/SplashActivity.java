package com.fastbuyapp.fastbuy.fastbuy;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.fastbuyapp.omar.fastbuy.R;

public class SplashActivity extends AppCompatActivity {
    private final int DURACION_SPLASH = 1500; // 2 segundos
    private Typeface fuente1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getSupportActionBar().hide();;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        String fuente = "fonts/fuente1.ttf";
        this.fuente1 = Typeface.createFromAsset(getAssets(), fuente);
        TextView lblFast = (TextView) findViewById(R.id.lblFastBuy);
        TextView lblBuy = (TextView) findViewById(R.id.lbleslogan);
        lblFast.setTypeface(fuente1);
        lblBuy.setTypeface(fuente1);
        new Handler().postDelayed(new Runnable(){
            public void run(){
                // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
                Intent intent = new Intent(SplashActivity.this, InicioActivity.class);
                startActivity(intent);
                finish();
            };
        }, DURACION_SPLASH);
    }
}
