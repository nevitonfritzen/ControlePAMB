package br.com.jungle.controlepamb.uteis;

import android.app.AlertDialog;
import android.content.Context;

import br.com.jungle.controlepamb.R;

/**
 * Created by neviton on 08/11/2017.
 */

public class Uteis {

        public static void Alert(Context context, String mensagem){

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

            alertDialog.setTitle(R.string.app_name);

            alertDialog.setMessage(mensagem);

            alertDialog.setPositiveButton("OK", null);

            alertDialog.show();

        }


    }
