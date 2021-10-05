/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.sql.Date;



/**
 *
 * @author Aluno
 */
public class Estoque {
     private int id,quantidade;
     private String descricaoProduto, descOperacao, data;
  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
        public String getdescricaoProduto() {
        return descricaoProduto;
    }

    public void setdescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
        public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getdescOperacao() {
        return descOperacao;
    }

    public void setdescOperacao(String descOperacao) {
        this.descOperacao = descOperacao;
    }
}