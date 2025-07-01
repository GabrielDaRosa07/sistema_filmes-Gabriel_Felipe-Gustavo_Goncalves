/**
 *
 * @author gabriel-da-rosa
 */

import java.sql.*;
import java.util.*;

public class GeneroModel {
    
    public static void create(GeneroBean gb, Connection con) throws SQLException{
        
        String sql = "INSERT INTO Genero (IDGenero,Nome) VALUES (?,?)";
        
        try (PreparedStatement st = con.prepareStatement(sql)){
            st.setInt(1,gb.getIdGenero());
            st.setString(2,gb.getNome());
            st.execute();
        }
        
    }
    
    public static ArrayList<GeneroBean> listAll(Connection con) throws SQLException{
        
        String sql = "SELECT IDGenero, Nome FROM Genero ORDER BY Nome";
        
        ArrayList<GeneroBean> listaGeneros = new ArrayList<>();
        
        try(Statement st = con.createStatement(); ResultSet result = st.executeQuery(sql)){
            while (result.next()){
                listaGeneros.add(new GeneroBean(
                   result.getInt("IDGenero"),
                   result.getString("Nome")
                ));
            }
        }
        return listaGeneros;
    }
    
    public static void delete(int id, Connection con) throws SQLException{
        
        String sqlPossui = "DELETE FROM Possui WHERE IDGenero = ?";
        
        try(PreparedStatement st = con.prepareStatement(sqlPossui)){
            st.setInt(1, id);
            st.executeUpdate();
        }
        
        String sqlGenero = "DELETE FROM Genero WHERE IDGenero = ?";
        
        try(PreparedStatement st = con.prepareStatement(sqlGenero)){
            st.setInt(1, id);
            if(st.executeUpdate()>0){
                System.out.println("Genero de ID "+id+" removido :) ");
            }else{
                System.out.println("Genero de ID "+id+" nao encontrado :( ");
            }
        }
        
    }
    
    public static boolean idExists(int id, Connection con) throws SQLException{
        
        String sql = "SELECT COUNT(*) FROM Genero WHERE IDGenero = ?";
        
        try(PreparedStatement st = con.prepareStatement(sql)){
            st.setInt(1, id);
            try(ResultSet rs = st.executeQuery()){
                if(rs.next()){
                    return rs.getInt(1)>0;
                }
            }
        }
        return false;
    }
    
}
