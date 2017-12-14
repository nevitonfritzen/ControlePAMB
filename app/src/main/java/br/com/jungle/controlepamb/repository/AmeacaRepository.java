package br.com.jungle.controlepamb.repository;

/**
 * Created by neviton on 07/11/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.jungle.controlepamb.uteis.DatabaseUtil;
import br.com.jungle.controlepamb.model.AmeacaModel;



    public class AmeacaRepository {

        DatabaseUtil databaseUtil;

        public AmeacaRepository(Context context){

            databaseUtil =  new DatabaseUtil(context);

        }

        public void Salvar(AmeacaModel ameacaModel){

            ContentValues contentValues =  new ContentValues();
            contentValues.put("descricao",  ameacaModel.getDescricao());
            contentValues.put("endereco",   ameacaModel.getEndereco());
            contentValues.put("bairro",     ameacaModel.getBairro());
            contentValues.put("potencia_impacto", ameacaModel.getPotencia_impacto());

            databaseUtil.GetConexaoDataBase().insert("ameaca",null,contentValues);

        }


        public void Atualizar(AmeacaModel ameacaModel){

            ContentValues contentValues =  new ContentValues();
            contentValues.put("id_ameaca", ameacaModel.getCodigo());
            contentValues.put("descricao",       ameacaModel.getDescricao());
            contentValues.put("endereco",   ameacaModel.getEndereco());
            contentValues.put("bairro",       ameacaModel.getBairro());
            contentValues.put("potencia_impacto", ameacaModel.getPotencia_impacto());

            databaseUtil.GetConexaoDataBase().update("ameaca", contentValues, "id_ameaca = ?", new String[]{Integer.toString(ameacaModel.getCodigo())});
        }


        public Integer Excluir(int codigo){
            return databaseUtil.GetConexaoDataBase().delete("ameaca","id_ameaca = ?", new String[]{Integer.toString(codigo)});
        }


        public AmeacaModel GetAmeaca(int codigo){


            Cursor cursor =  databaseUtil.GetConexaoDataBase().rawQuery("SELECT * FROM ameaca WHERE id_ameaca= "+ codigo,null);

            cursor.moveToFirst();


            AmeacaModel ameacaModel =  new AmeacaModel();


            ameacaModel.setCodigo(cursor.getInt(cursor.getColumnIndex("id_ameaca")));
            ameacaModel.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
            ameacaModel.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
            ameacaModel.setBairro(cursor.getString(cursor.getColumnIndex("bairro")));
            ameacaModel.setPotencia_impacto(cursor.getString(cursor.getColumnIndex("potencia_impacto")));

            return ameacaModel;

        }


        public List<AmeacaModel> SelecionarTodos(){

            List<AmeacaModel> ameacas = new ArrayList<AmeacaModel>();

            String stringBuilderQuery = " SELECT id_ameaca, descricao, endereco, bairro, potencia_impacto FROM  ameaca ORDER BY id_ameaca ";


            Cursor cursor = databaseUtil.GetConexaoDataBase().rawQuery(stringBuilderQuery, null);


            cursor.moveToFirst();


            AmeacaModel ameacaModel;

            while (!cursor.isAfterLast()){

                ameacaModel =  new AmeacaModel();

                ameacaModel.setCodigo(cursor.getInt(cursor.getColumnIndex("id_ameaca")));
                ameacaModel.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
                ameacaModel.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
                ameacaModel.setBairro(cursor.getString(cursor.getColumnIndex("bairro")));
                ameacaModel.setPotencia_impacto(cursor.getString(cursor.getColumnIndex("potencia_impacto")));

                ameacas.add(ameacaModel);
                cursor.moveToNext();
            }

            return ameacas;
        }
    }