package com.fastbuyapp.fastbuy.fastbuy.Validaciones;

import android.widget.TextView;

/**
 * Created by OMAR on 17/03/2019.
 */

public class ValidacionDatos {

    public ValidacionDatos(){

    }

    public boolean validarCelular(TextView txtCelular){
        boolean correcto = false;
        if(txtCelular.getText().toString().isEmpty()){
            correcto = false;
        }else {
            if (txtCelular.getText().toString().substring(0, 1).equals("9")) {
                if (txtCelular.getText().toString().trim().length() == 9) {
                    correcto = true;
                } else {
                    correcto = false;
                }
            } else if (txtCelular.getText().toString().substring(0, 1).equals("0")) {
                if (txtCelular.getText().toString().trim().length() == 9) {
                    correcto = true;
                } else {
                    correcto = false;
                }
            } else {
                correcto = false;
            }
        }
        return correcto;
    }
}
