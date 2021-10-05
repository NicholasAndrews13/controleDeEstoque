/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Estoque;


/**
 *
 * @author Aluno
 */
public class EstoqueDAO {
    
     public void create(Estoque est){

        try{
            
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        stmt = con.prepareStatement("INSERT INTO estoque "
                + "(descOperacao, descricaoProduto, quantidade, data)"
                + " VALUES(?,?,?,?)");
        
    
        stmt.setString(1,est.getdescOperacao());
        stmt.setString(2,est.getdescricaoProduto());        
        stmt.setDouble(3,est.getQuantidade());
        stmt.setString(4,est.getData());
        
 
        stmt.execute();
        
        }catch (SQLException ex){
        Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE,null, ex);
        }finally{
        //ConnectionFactory.closeConnection(con, stmt);
        }
    }   
        public List<Estoque> readForDesc(String desc) throws SQLException {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Estoque> saldo = new ArrayList<>();
       try {
            stmt = con.prepareStatement("SELECT \n" +
                                        "(entrada - saida) as saldo\n" +
                                        "FROM (\n" +
                                        " (SELECT SUM(quantidadeProduto) as entrada FROM historico WHERE operacaoProduto = 0 )as entrada,\n" +
                                        " (SELECT SUM(quantidadeProduto) as saida FROM historico WHERE operacaoProduto = 1)as saida\n" +
                                        ")");
            rs   = stmt.executeQuery();            
            while(rs.next()){
                Estoque est = new Estoque();
                est.setId(rs.getInt("id"));
                est.setdescricaoProduto(rs.getString("descricaoProduto"));                                
                saldo.add(est);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
           // ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return saldo;
    }  
}