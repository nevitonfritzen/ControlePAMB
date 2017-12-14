package br.com.jungle.controlepamb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;

import br.com.jungle.controlepamb.model.AmeacaModel;
import br.com.jungle.controlepamb.model.ImpactoModel;
import br.com.jungle.controlepamb.repository.AmeacaRepository;
import br.com.jungle.controlepamb.uteis.Uteis;

public class CadastroActivity extends AppCompatActivity {

    EditText         editTextDescricao;
    EditText         editTextEndereco;
    EditText         editTextBairro;
    Spinner          spinnerImpacto;
    Button           buttonSalvar;
    Button           buttonVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        this.CriarComponentes();
        this.CriarEventos();
        this.CarregaListImpacto();
    }

    protected  void CriarComponentes(){

        editTextDescricao           = (EditText) this.findViewById(R.id.editTextDescricao);

        editTextEndereco       = (EditText) this.findViewById(R.id.editTextEndereco);

        editTextBairro = (EditText)this.findViewById(R.id.editTextBairro);

        spinnerImpacto     = (Spinner)this.findViewById(R.id.spinnerImpacto);

        buttonSalvar           = (Button) this.findViewById(R.id.buttonSalvar);

        buttonVoltar           = (Button) this.findViewById(R.id.buttonVoltar);

    }

    protected  void CriarEventos(){

        buttonSalvar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Salvar_onClick();
            }
        });

        buttonVoltar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent intentMainActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentMainActivity);
                finish();
            }
        });
    }


    protected  void Salvar_onClick(){

        if(editTextDescricao.getText().toString().trim().equals("")){

            Uteis.Alert(this, this.getString(R.string.descricao_obrigatorio));

            editTextDescricao.requestFocus();
        }
        else if(editTextEndereco.getText().toString().trim().equals("")){

            Uteis.Alert(this, this.getString(R.string.endereco_obrigatorio));

            editTextEndereco.requestFocus();
        }
        else if(editTextBairro.getText().toString().trim().equals("")){

            Uteis.Alert(this, this.getString(R.string.bairro_obrigatorio));

            editTextBairro.requestFocus();
        }
        else{

            AmeacaModel AmeacaModel = new AmeacaModel();
            AmeacaModel.setDescricao(editTextDescricao.getText().toString().trim());
            AmeacaModel.setEndereco(editTextEndereco.getText().toString().trim());
            AmeacaModel.setBairro(editTextBairro.getText().toString().trim());
            ImpactoModel ImpactoModel = (ImpactoModel)spinnerImpacto.getSelectedItem();

            AmeacaModel.setPotencia_impacto(ImpactoModel.getCodigo());

            new AmeacaRepository(this).Salvar(AmeacaModel);
            Uteis.Alert(this,this.getString(R.string.registro_salvo_sucesso));

            LimparCampos();
        }
    }
    protected void LimparCampos(){

        editTextDescricao.setText(null);
        editTextEndereco.setText(null);
        editTextBairro.setText(null);
    }

    protected  void CarregaListImpacto(){

        ArrayAdapter<ImpactoModel> arrayAdapter;

        List<ImpactoModel> itens =  new ArrayList<ImpactoModel>();

        itens.add(new ImpactoModel("1","1"));
        itens.add(new ImpactoModel("2","2"));
        itens.add(new ImpactoModel("3","3"));
        itens.add(new ImpactoModel("4","4"));
        itens.add(new ImpactoModel("5","5"));
        itens.add(new ImpactoModel("6","6"));
        itens.add(new ImpactoModel("7","7"));
        itens.add(new ImpactoModel("8","8"));
        itens.add(new ImpactoModel("9","9"));
        itens.add(new ImpactoModel("10","10"));


        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, itens);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinnerImpacto.setAdapter(arrayAdapter);

    }
}
