package com.fastbuyapp.fastbuy.fastbuy;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fastbuyapp.omar.fastbuy.R;
import com.fastbuyapp.fastbuy.fastbuy.entidades.Globales;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.List;
import java.util.Locale;

public class InicioActivity extends AppCompatActivity {
    ProgressBar barraCargando;
    TextView porcentaje;
    Intent locatorService = null;
    private String ciudadActual = "";
    AlertDialog alertDialog = null;
    ActivityCargando anim ;
    TextView ciudadEncontrada;
    public double lati = 0.0;
    public double longi = 0.0;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        //getSupportActionBar().hide();

        barraCargando = (ProgressBar) findViewById(R.id.pgbBarraProgresoPortada);
        porcentaje = (TextView) findViewById(R.id.txtCargandoPorcentaje);
        anim = new ActivityCargando(InicioActivity.this, barraCargando, porcentaje, 0, 100f);
        // Button btnBuscar = (Button) findViewById(R.id.buttonBuscar);

        //AsyncTask_load ast = new AsyncTask_load();
        //ast.execute();
        /*btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Hilos();



            }
        });*/

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
        } else {
            AsyncTask_load ast = new AsyncTask_load();
            ast.execute();
            //locationStart();
        }
    }



    private void locationStart() {
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Localizacion Local = new Localizacion();
        Local.setMainActivity(this);
        final boolean gpsEnabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
        }
        if (ActivityCompat.checkSelfPermission(InicioActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(InicioActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(InicioActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
            return;
        }
        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, Local);
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, Local);
    }

    public void setLocation(Location loc) {
        //Obtener la direccion de la calle a partir de la latitud y la longitud
        if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(InicioActivity.this, Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(
                        loc.getLatitude(), loc.getLongitude(), 1);
                if (!list.isEmpty()) {
                    Address DirCalle = list.get(0);
                    int cant = DirCalle.getAddressLine(0).split(",").length;
                    Globales.ciudad = DirCalle.getAddressLine(0).split(",")[cant - 2].trim();
                    Globales.ciudadOrigen = DirCalle.getAddressLine(0).split(",")[cant - 2].trim();
                    //Toast.makeText(InicioActivity.this,Globales.ciudad , Toast.LENGTH_LONG).show();
                    //Toast.makeText(InicioActivity.this, DirCalle.getAddressLine(0) , Toast.LENGTH_LONG).show();

                    //direccion.setText("Mi direccion es: \n" + DirCalle.getAddressLine(0));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationStart();
                return;
            }
        }
    }

    public class AsyncTask_load extends AsyncTask<Void, Integer, Boolean> {

        //ProgressDialog progDailog = null;
        @Override
        protected void onPreExecute() {
            locationStart();
            //barraCargando.setAnimation(anim);

            /*progDailog = new ProgressDialog(InicioActivity.this);
            progDailog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    AsyncTask_load.this.cancel(true);
                }
            });
            progDailog.setMessage("Loading...");
            progDailog.setIndeterminate(true);
            progDailog.setCancelable(true);
            progDailog.show();*/


        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO Auto-generated method stub
            while (lati == 0.0) {

            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Boolean bool) {
            //progDailog.dismiss();
            new Handler().postDelayed(new Runnable(){
                public void run(){
                    // Cuando pasen 1 segundo, pasamos a la actividad principal de la aplicación
                    Intent i = new Intent(InicioActivity.this, PortadaActivity.class );
                    i.putExtra("ubicacion", Globales.ciudad);
                    InicioActivity.this.startActivity(i);
                    finish();
                };
            }, 1000);


        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Toast.makeText(getApplication(), "CANCELADO", Toast.LENGTH_SHORT).show();
        }
    }

    public class Localizacion implements LocationListener {
        InicioActivity mainActivity;

        public InicioActivity getMainActivity() {
            return mainActivity;
        }

        public void setMainActivity(InicioActivity mainActivity) {
            this.mainActivity = mainActivity;
        }

        @Override
        public void onLocationChanged(Location loc) {
            // Este metodo se ejecuta cada vez que el GPS recibe nuevas coordenadas
            // debido a la deteccion de un cambio de ubicacion
            lati = loc.getLatitude();
            longi = loc.getLongitude();
            this.mainActivity.setLocation(loc);
        }

        @Override
        public void onProviderDisabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es desactivado
            Toast.makeText(getApplication(), "GPS Desactivado", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderEnabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es activado
            //Toast.makeText(getApplication(), "GPS Activado", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                case LocationProvider.AVAILABLE:
                    Log.d("debug", "LocationProvider.AVAILABLE");
                    break;
                case LocationProvider.OUT_OF_SERVICE:
                    Log.d("debug", "LocationProvider.OUT_OF_SERVICE");
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Log.d("debug", "LocationProvider.TEMPORARILY_UNAVAILABLE");
                    break;
            }
        }
    }
}

