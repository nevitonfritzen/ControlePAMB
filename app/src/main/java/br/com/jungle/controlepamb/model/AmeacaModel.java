package br.com.jungle.controlepamb.model;

/**
 * Created by neviton on 07/11/2017.
 */

public class AmeacaModel {

    private Integer codigo;
    private String  descricao;
    private String  endereco;
    private String  bairro;
    private String  potencia_impacto;


    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getPotencia_impacto() {
        return potencia_impacto;
    }

    public void setPotencia_impacto(String potencia_impacto) {
        this.potencia_impacto = potencia_impacto;
    }
}