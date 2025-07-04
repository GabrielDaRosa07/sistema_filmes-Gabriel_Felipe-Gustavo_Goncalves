/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemafilmes.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sistemafilmes.bean.ListaFilmeBean;

/**
 *
 * @author gabriel-da-rosa
 */
public class ListaFilmeModel {
 
        
    public static void addFilme(ListaFilmeBean lfb, Connection con) throws SQLException {
        String sqlCheck = "SELECT COUNT(*) FROM ListaDeFilme WHERE IDLista = ? AND IDFilme = ?";
        try(PreparedStatement stCheck = con.prepareStatement(sqlCheck)){
            stCheck.setInt(1, lfb.getIdLista());
            stCheck.setInt(2, lfb.getIdFilme());
            try(ResultSet rs = stCheck.executeQuery()){
                if(rs.next() && rs.getInt(1)>0){
                    System.out.println("Esse filme já está na lista.");
                    return;
                }
            }
        }

        String sqlInsert = "INSERT INTO ListaDeFilme (IDLista, IDFilme, Posicao) VALUES (?, ?, ?)";
        try(PreparedStatement stInsert = con.prepareStatement(sqlInsert)){
            stInsert.setInt(1, lfb.getIdLista());
            stInsert.setInt(2, lfb.getIdFilme());
            stInsert.setInt(3, lfb.getPosicao());
            stInsert.execute();
            System.out.println("Filme adicionado à lista :)");
        }
    }
    
}
