package br.com.jungle.controlepamb.uteis;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.jungle.controlepamb.ConsultarActivity;
import br.com.jungle.controlepamb.EditarActivity;
import br.com.jungle.controlepamb.R;
import br.com.jungle.controlepamb.model.AmeacaModel;
import br.com.jungle.controlepamb.repository.AmeacaRepository;

public class LinhaConsultaAdapter extends BaseAdapter {

    private static LayoutInflater layoutInflater = null;

    List<AmeacaModel> AmeacaModel =  new ArrayList<AmeacaModel>();

    AmeacaRepository  AmeacaRepository;

    private ConsultarActivity consultarActivity;

    public LinhaConsultaAdapter(ConsultarActivity consultarActivity, List<AmeacaModel> AmeacaModel ) {

        this.AmeacaModel       =  AmeacaModel;
        this.consultarActivity  =  consultarActivity;
        this.layoutInflater     = (LayoutInflater) this.consultarActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.AmeacaRepository   = new AmeacaRepository(consultarActivity);
    }

    @Override

    public int getCount(){

        return AmeacaModel.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        final View viewLinhaLista = layoutInflater.inflate(R.layout.activity_linha_consulta,null);

        TextView textViewCodigo          = (TextView) viewLinhaLista.findViewById(R.id.textViewCodigo);

        TextView textViewDescricao            = (TextView) viewLinhaLista.findViewById(R.id.textViewDescricao);

        TextView textViewEndereco        = (TextView) viewLinhaLista.findViewById(R.id.textViewEndereco);

        TextView textViewBairro            = (TextView) viewLinhaLista.findViewById(R.id.textViewBairro);

        TextView textViewImpactoAmb     = (TextView) viewLinhaLista.findViewById(R.id.textViewImpactoAmb);

        Button buttonExcluir             = (Button)   viewLinhaLista.findViewById(R.id.buttonExcluir);

        Button   buttonEditar            = (Button)   viewLinhaLista.findViewById(R.id.buttonEditar);

        textViewCodigo.setText(String.valueOf(AmeacaModel.get(position).getCodigo()));

        textViewDescricao.setText(AmeacaModel.get(position).getDescricao());

        textViewEndereco.setText(AmeacaModel.get(position).getEndereco());

        textViewBairro.setText(AmeacaModel.get(position).getBairro());

        textViewImpactoAmb.setText(AmeacaModel.get(position).getPotencia_impacto());


        buttonExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AmeacaRepository.Excluir(AmeacaModel.get(position).getCodigo());
                Toast.makeText(consultarActivity, "Registro excluido com sucesso!", Toast.LENGTH_LONG).show();

                AtualizarLista();

            }
        });

        buttonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intentRedirecionar = new Intent(consultarActivity, EditarActivity.class);

                intentRedirecionar.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                intentRedirecionar.putExtra("id_ameaca",AmeacaModel.get(position).getCodigo());

                consultarActivity.startActivity(intentRedirecionar);

                consultarActivity.finish();


            }
        });


        return viewLinhaLista;
    }

    public void AtualizarLista(){

        this.AmeacaModel.clear();
        this.AmeacaModel = AmeacaRepository.SelecionarTodos();
        this.notifyDataSetChanged();
    }

}