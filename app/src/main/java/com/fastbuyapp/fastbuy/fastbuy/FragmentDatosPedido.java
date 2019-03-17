package com.fastbuyapp.fastbuy.fastbuy;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fastbuyapp.fastbuy.fastbuy.Validaciones.ValidacionDatos;
import com.fastbuyapp.fastbuy.fastbuy.entidades.Globales;
import com.fastbuyapp.omar.fastbuy.R;

import org.w3c.dom.Text;

public class FragmentDatosPedido extends Fragment {
    private Typeface fuente1;
    public FragmentDatosPedido() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View row = inflater.inflate(R.layout.fragment_fragment_datos_pedido, container, false);
        String fuente = "fonts/fuente1.ttf";
        fuente1 = Typeface.createFromAsset(getActivity().getAssets(), fuente);
        Button btnResumen = (Button) row.findViewById(R.id.btnResumen);
        btnResumen.setTypeface(fuente1);

        final Spinner cboCiudad = (Spinner) row.findViewById(R.id.cboCiudadOrigen);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(row.getContext(),R.array.ciudades,R.layout.support_simple_spinner_dropdown_item);
        cboCiudad.setAdapter(adapter);
        if (Globales.ciudadOrigen != null) {
            int spinnerPosition = adapter.getPosition(Globales.ciudadOrigen);
            cboCiudad.setSelection(spinnerPosition);
        }
        final Spinner cboFormaPago = (Spinner) row.findViewById(R.id.cboFormaPago);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(row.getContext(),R.array.formaPago,R.layout.support_simple_spinner_dropdown_item);
        cboFormaPago.setAdapter(adapter2);
        final int spinnerPosition2 = adapter2.getPosition(Globales.formaPago);
        cboFormaPago.setSelection(spinnerPosition2);
        final TextView txtNombreCliente = (TextView) row.findViewById(R.id.txtNombreCliente);
        final TextView txtDireccionCliente = (TextView) row.findViewById(R.id.txtDireccionCliente);
        final TextView txtTelefonoCliente = (TextView) row.findViewById(R.id.txtTelefonoCliente);

        txtNombreCliente.setText(Globales.nombreCliente);
        txtDireccionCliente.setText(Globales.direccion);
        txtTelefonoCliente.setText(Globales.numeroTelefono);

        btnResumen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidacionDatos validacionDatos = new ValidacionDatos();
                if(validacionDatos.validarCelular(txtTelefonoCliente)){
                    TextView txtBarra = (TextView) getActivity().findViewById(R.id.txtBarra);
                    txtBarra.setText("RESUMEN");
                    Globales.nombreCliente = String.valueOf(txtNombreCliente.getText());
                    Globales.direccion = String.valueOf(txtDireccionCliente.getText());
                    Globales.numeroTelefono = String.valueOf(txtTelefonoCliente.getText());
                    Globales.formaPago = String.valueOf(cboFormaPago.getSelectedItem());
                    //Toast.makeText(v.getContext(), String.valueOf(spinnerPosition2),Toast.LENGTH_SHORT).show();
                    Globales.ciudadOrigen = String.valueOf(cboCiudad.getSelectedItem());
                    final FragmentResumenEfectivo panelResumenEfectivo = new FragmentResumenEfectivo();
                    final FragmentManager manager = getActivity().getSupportFragmentManager();
                    manager.beginTransaction()
                            .replace(R.id.contenedorPrincipal, panelResumenEfectivo, panelResumenEfectivo.getTag())
                            .commit();
                }
                else{
                    Toast toast = Toast.makeText(v.getContext(), "Ingrese un número de teléfono válido.", Toast.LENGTH_SHORT);
                    View vistaToast = toast.getView();
                    vistaToast.setBackgroundResource(R.drawable.toast_warning);
                    toast.show();
                }
            }
        });
        return row;
    }

}
