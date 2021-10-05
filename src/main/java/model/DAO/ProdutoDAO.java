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
import model.bean.Produto;

/**
 *
 * @author Aluno
 */
public class ProdutoDAO {
    
        public void create(Produto c){

        try{
            
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        stmt = con.prepareStatement("INSERT INTO cadastro_produto "
                + "(codigoProduto, descricaoProduto, precoUnitProduto, marcaProduto, corProduto)"
                + " VALUES(?,?,?,?,?)");
        
    
        stmt.setInt(1,c.getCodigo());
        stmt.setString(2,c.getDescricao());        
        stmt.setDouble(3,c.getPreco());
        stmt.setInt(4,c.getMarca());
        stmt.setInt(5,c.getCor());
 
        stmt.execute();
        
        }catch (SQLException ex){
        Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE,null, ex);
        }finally{
        //ConnectionFactory.closeConnection(con, stmt);
        }
    } 
     public List<Produto> read() throws SQLException {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Produto> produtos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM cadastro_produto");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Produto pdt = new Produto();
                
                pdt.setId(rs.getInt("id"));
                pdt.setCodigo(rs.getInt("codigoProduto"));
                pdt.setDescricao(rs.getString("descricaoProduto"));
                pdt.setPreco(rs.getDouble("precoUnitProduto"));
                pdt.setMarca(rs.getInt("marcaProduto"));
                pdt.setCor(rs.getInt("corProduto"));
                
                produtos.add(pdt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
          // ConnectionFactory.closeConnection(con, stmt);
        }
        return produtos;
    }      
}