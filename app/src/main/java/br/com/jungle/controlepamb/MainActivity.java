package br.com.jungle.controlepamb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void creditos (View v){
        Intent it = new Intent(getBaseContext(), CreditosActivity.class);
        startActivity(it);
    };

    public void cadastrar (View v){
        Intent it = new Intent(getBaseContext(), CadastroActivity.class);
        startActivity(it);
    };

    public void consulta (View v){
        Intent it = new Intent(getBaseContext(), ConsultarActivity.class);
        startActivity(it);
    };
}
