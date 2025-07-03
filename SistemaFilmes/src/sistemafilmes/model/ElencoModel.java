/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemafilmes.model;

/**
 *
 * @author gabriel-da-rosa : gustavo-gonçalves
 */

import java.sql.*;
import sistemafilmes.bean.ElencoBean;

public class ElencoModel {
    
    public static void create(ElencoBean eb,Connection con) throws SQLException{
        
        String sqlCheck = "SELECT COUNT(*) FROM Elenco WHERE IDFilme = ? AND IDPessoa = ?";
        
        
        try(PreparedStatement stCheck = con.prepareStatement(sqlCheck)){
            stCheck.setInt(1, eb.getIdFilme());
            stCheck.setInt(2, eb.getIdPessoa());

            try(ResultSet rs = stCheck.executeQuery()){
                if(rs.next() && rs.getInt(1) > 0){
                    System.out.println("Essa pessoa já está no elenco :( ");
                    return; // 
                }
            }
        } 
        
        String sqlInsert = "INSERT INTO Elenco (IDFilme, IDPessoa) VALUES (?, ?)";
            
        try(PreparedStatement stInsert = con.prepareStatement(sqlInsert)){
            stInsert.setInt(1, eb.getIdFilme());
            stInsert.setInt(2, eb.getIdPessoa());
            stInsert.execute();
            System.out.println("Pessoa adicionada ao elenco :)");
        }
        
        
        
    }
    
    
    
    
}
