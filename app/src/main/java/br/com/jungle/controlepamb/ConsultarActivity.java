package br.com.jungle.controlepamb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import br.com.jungle.controlepamb.model.AmeacaModel;
import br.com.jungle.controlepamb.repository.AmeacaRepository;
import br.com.jungle.controlepamb.uteis.LinhaConsultaAdapter;

public class ConsultarActivity extends AppCompatActivity {

    ListView listViewAmeacas;
    Button buttonVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        listViewAmeacas = (ListView)this.findViewById(R.id.listViewAmeacas);

        buttonVoltar    = (Button)this.findViewById(R.id.buttonVoltar);

        this.CarregarAmeacasCadastradas();

        this.CriarEvento();
    }

    protected  void CriarEvento(){
                buttonVoltar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent intentMainActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentMainActivity);

                finish();
            }
        });
    }

    protected  void CarregarAmeacasCadastradas(){

        AmeacaRepository ameacaRepository =  new AmeacaRepository(this);

        List<AmeacaModel> Ameaca = ameacaRepository.SelecionarTodos();
        listViewAmeacas.setAdapter(new LinhaConsultaAdapter(this, Ameaca));
    }

}