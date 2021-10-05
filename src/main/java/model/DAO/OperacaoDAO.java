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
import model.bean.Operacao;

/**
 *
 * @author Aluno
 */
public class OperacaoDAO {
       
    public List<Operacao> read() throws SQLException {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Operacao> operacoes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM operacao");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Operacao o = new Operacao();

                o.setId(rs.getInt("id"));
                o.setDescricao(rs.getString("descricao"));
                operacoes.add(o);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return operacoes;
    }  
    
}
