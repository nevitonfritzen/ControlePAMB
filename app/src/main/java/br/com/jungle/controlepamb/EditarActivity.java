package br.com.jungle.controlepamb;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import br.com.jungle.controlepamb.uteis.LinhaConsultaAdapter;
import br.com.jungle.controlepamb.uteis.Uteis;

public class EditarActivity extends AppCompatActivity {

    EditText         editTextCodigo;
    EditText         editTextDescricao;
    EditText         editTextEndereco;
    EditText         editTextBairro;
    Spinner          spinnerImpacto;
    Button           buttonAlterar;
    Button           buttonVoltar;

    ArrayAdapter<ImpactoModel> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        this.CriarComponentes();
        this.CriarEventos();
        this.CarregaListImpacto();
        this.CarregaValoresCampos();
    }

    protected  void CriarComponentes(){

        editTextCodigo  = (EditText) this.findViewById(R.id.editTextCodigo);
        editTextDescricao  = (EditText) this.findViewById(R.id.editTextDescricao);
        editTextEndereco   = (EditText) this.findViewById(R.id.editTextEndereco);
        editTextBairro     = (EditText)this.findViewById(R.id.editTextBairro);
        spinnerImpacto     = (Spinner)this.findViewById(R.id.spinnerImpacto);
        buttonAlterar      = (Button) this.findViewById(R.id.buttonAlterar);
        buttonVoltar       = (Button) this.findViewById(R.id.buttonVoltar);
    }

    protected  void CriarEventos(){

         buttonAlterar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Alterar_onClick();
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

    protected  void Alterar_onClick(){

        if(editTextDescricao.getText().toString().trim().equals("")){

            Uteis.Alert(this, this.getString(R.string.descricao_obrigatorio));

            editTextDescricao.requestFocus();
        }
        else if(editTextEndereco.getText().toString().trim().equals("")){

            Uteis.Alert(this, this.getString(R.string.endereco_obrigatorio));

            editTextEndereco.requestFocus();
        }
        else{
            AmeacaModel ameacaModel = new AmeacaModel();
            ameacaModel.setCodigo(Integer.parseInt(editTextCodigo.getText().toString()));
            ameacaModel.setDescricao(editTextDescricao.getText().toString().trim());
            ameacaModel.setEndereco(editTextEndereco.getText().toString().trim());
            ameacaModel.setBairro(editTextBairro.getText().toString().trim());
            ImpactoModel impactoModel = (ImpactoModel)spinnerImpacto.getSelectedItem();
            ameacaModel.setPotencia_impacto(impactoModel.getCodigo());
            new AmeacaRepository(this).Atualizar(ameacaModel);

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setMessage("Registro alterado com sucesso! ");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {

                    Intent intentRedirecionar = new Intent(getApplicationContext(), ConsultarActivity.class);

                    startActivity(intentRedirecionar);

                    finish();
                }
            });

            alertDialog.show();
        }
    }

    protected  void CarregaListImpacto(){



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
    protected void PosicionaImpacto(String chaveImpacto){

         for(int index = 0; index < arrayAdapter.getCount(); index++){

            if(arrayAdapter.getItem(index).getCodigo().equals(chaveImpacto)){

                spinnerImpacto.setSelection(index);
                break;
            }
        }
    }

    protected  void CarregaValoresCampos(){

        AmeacaRepository ameacaRepository = new AmeacaRepository(this);

        Bundle extra =  this.getIntent().getExtras();
        int id_ameaca = extra.getInt("id_ameaca");

        AmeacaModel ameacaModel = ameacaRepository.GetAmeaca(id_ameaca);

        editTextCodigo.setText(String.valueOf(ameacaModel.getCodigo()));
        editTextDescricao.setText(ameacaModel.getDescricao());
        editTextEndereco.setText(ameacaModel.getEndereco());
        editTextBairro.setText(ameacaModel.getBairro());
        this.PosicionaImpacto(ameacaModel.getPotencia_impacto());
    }
}