package br.com.jungle.controlepamb.model;

/**
 * Created by neviton on 08/11/2017.
 */

public class ImpactoModel {

    private String  codigo;
    private String  descricao;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    @Override //Spinner
    public String toString() {
        return this.descricao;
    }

      public ImpactoModel(String codigo, String descricao){
        this.codigo    = codigo;
        this.descricao = descricao;

    }
}