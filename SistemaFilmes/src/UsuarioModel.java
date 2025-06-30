
/**
 *
 * @author gabriel-da-rosa
 * 
 * Essa classe basicamente tem os metodos para inserir e mostar os usuarios
 */

import java.sql.*;
import java.util.*;

public class UsuarioModel {
    
    public static void create(UsuarioBean ub, Connection con) throws SQLException{
        
        String sql = "INSERT INTO Usuario (IDUser,Nome,Email,Senha,Credencial) VALUES (?,?,?,?,?)";
        
        try(PreparedStatement st = con.prepareStatement(sql)){
            
            st.setInt(1,ub.getIdUser());
            st.setString(2,ub.getNome());
            st.setString(3,ub.getEmail());
            st.setString(4,ub.getSenha());
            st.setBoolean(5,ub.isCredencial());
            
            st.execute();
           
        }
        
    }
    
    public static ArrayList<UsuarioBean> listAll(Connection con) throws SQLException{
        
        String sql = "SELECT IDUser, Nome, Email, Credencial FROM Usuario ORDER BY Nome";
        
        ArrayList<UsuarioBean> list = new  ArrayList<>();
        
        try (Statement st = con.createStatement(); ResultSet result = st.executeQuery(sql)){
            
            while(result.next()){
                
                UsuarioBean ub = new UsuarioBean(
                        result.getInt("IDUser"),
                        result.getString("Nome"),
                        result.getString("Email"),
                        "",
                        result.getBoolean("Credencial")
                );          
                
                list.add(ub);
                
            }     
        }
        
        return list;     
    }
    
    public static boolean idExists(int id, Connection con) throws SQLException{
        
        String sql = "SELECT COUNT(*) FROM Usuario WHERE IDUser = ?";
        
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
