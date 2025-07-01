package sistemafilmes.model;

/**
 *
 * @author gabrielrosa
 */

import sistemafilmes.bean.FilmeBean;
import java.sql.*;
import java.util.*;

public class FilmeModel {
    
    public static void create(FilmeBean fb, Connection con) throws SQLException{
        
        String sql = "INSERT INTO Filme (IDFilme, Titulo, Ano, Duracao, Sinopse, Poster) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement st = con.prepareStatement(sql)){
            st.setInt(1,fb.getIdFilme());
            st.setString(2,fb.getTitulo());
            st.setInt(3,fb.getAno());
            st.setInt(4,fb.getDuracao());
            st.setString(5,fb.getSinopse());
            st.setString(6,fb.getPoster());
            st.execute();
        }
    }
    
    public static ArrayList<FilmeBean> listAll(Connection con) throws SQLException{
        
        String sql = "SELECT IDFilme, Titulo, Ano, Duracao, Sinopse, Poster FROM Filme ORDER BY Titulo";
        ArrayList<FilmeBean> listaFilmes = new ArrayList<>();
        
        try (Statement st = con.createStatement(); ResultSet result = st.executeQuery(sql)){
            
            while (result.next()){
                
                FilmeBean fb = new FilmeBean(
                    result.getInt("IDFilme"),
                    result.getString("Titulo"),
                    result.getInt("Ano"),
                    result.getInt("Duracao"),
                    result.getString("Sinopse"),
                    result.getString("Poster")
                );
                listaFilmes.add(fb);
            }  
        }
        return listaFilmes;
        
    }
    
    public static boolean idExists(int id, Connection con) throws SQLException{
        
        String sql = "SELECT COUNT(*) FROM Filme WHERE IDFilme = ?";
        
        try (PreparedStatement st = con.prepareCall(sql)){
            st.setInt(1, id);
            
            try(ResultSet rs = st.executeQuery()){
                if(rs.next()){
                    return rs.getInt(1)>0;
                }
            }
        }
        
        return false;
    }
    
    public static void delete(int id, Connection con) throws SQLException{
        
        String sqlAvaliacoes = "DELETE FROM Avaliacao WHERE IDFilme = ?";
        String sqlPossui = "DELETE FROM Possui WHERE IDFilme = ?";
        String sqlElenco = "DELETE FROM Elenco WHERE IDFilme = ?";
        String sqlFilme = "DELETE FROM Filme WHERE IDFilme = ?";
            
        try (PreparedStatement st = con.prepareStatement(sqlAvaliacoes)){
            st.setInt(1, id);
            st.executeUpdate();
        }
        
        try (PreparedStatement st = con.prepareStatement(sqlPossui)){
            st.setInt(1, id);
            st.executeUpdate();
        }
        
        try (PreparedStatement st = con.prepareStatement(sqlElenco)){
            st.setInt(1, id);
            st.executeUpdate();
        }
        
        try (PreparedStatement st = con.prepareStatement(sqlFilme)){
            st.setInt(1, id);
            int rowsAffected = st.executeUpdate();
            if(rowsAffected>0){
                System.out.println("Filme de ID "+id+ " e suas dependencias foram removidas :) ");
            }else{
                System.out.println("Filme de ID "+id+ " não encontrado :( ");
            }
        }   
    }
    
}
