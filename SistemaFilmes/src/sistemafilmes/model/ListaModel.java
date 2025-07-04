
package sistemafilmes.model;

/**
 *
 * @author gabriel-da-rosa : gustavo-gonçalves
 */

import java.sql.*;
import java.util.*;
import sistemafilmes.bean.*;

public class ListaModel {
    
    public static void create(ListaBean lb,Connection con) throws SQLException{
        String sql = "INSERT INTO Lista (IDLista, IDUser, Nome, Privada) VALUES (?,?,?,?)";
        try(PreparedStatement st = con.prepareStatement(sql)){
            st.setInt(1, lb.getIdLista());
            st.setInt(2, lb.getIdUser());
            st.setString(3, lb.getNome());
            st.setBoolean(4, lb.isPrivada());
            st.execute();
        }
    } 
    
    public static ArrayList<ListaBean> listAllByUser(int idUser,Connection con) throws SQLException{
        String sql = "SELECT IDLista, IDUser, Nome, Privada FROM Lista WHERE IDUser = ? ORDER BY Nome";
        ArrayList<ListaBean> listaDeListas = new ArrayList<>();
        try(PreparedStatement st = con.prepareStatement(sql)) {
            st.setInt(1, idUser);
            try(ResultSet result = st.executeQuery()){
                while(result.next()){
                    listaDeListas.add(new ListaBean(
                        result.getInt("IDLista"),
                        result.getInt("IDUser"),
                        result.getString("Nome"),
                        result.getBoolean("Privada")
                    ));
                }
            }
        }
        
        return listaDeListas;
    }

    public static void delete(int idLista,int idUser,Connection con) throws SQLException{
        String sql = "DELETE FROM Lista WHERE IDLista = ? AND IDUser = ?";
        try(PreparedStatement st = con.prepareStatement(sql)){
            st.setInt(1, idLista);
            st.setInt(2, idUser);
            if(st.executeUpdate() > 0){
                System.out.println("Lista de ID " + idLista + " foi removida :) ");
            }else{
                System.out.println("Nenhuma lista encontrada :(");
            }
        }
    }

    public static boolean idExists(int id,Connection con) throws SQLException{
        String sql = "SELECT COUNT(*) FROM Lista WHERE IDLista = ?";
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

    public static ArrayList<FilmeBean> pegarFilmesDaLista(int idLista,Connection con) throws SQLException{
        String sql = "SELECT f.* FROM Filme f " +
                     "JOIN ListaDeFilme ldf ON f.IDFilme = ldf.IDFilme " +
                     "WHERE ldf.IDLista = ? " +
                     "ORDER BY ldf.Posicao";
        
        ArrayList<FilmeBean> filmes = new ArrayList<>();
        
        try(PreparedStatement st = con.prepareStatement(sql)){
            st.setInt(1, idLista);
            try(ResultSet rs = st.executeQuery()){
                while(rs.next()){                
                    FilmeBean fb = new FilmeBean(
                        rs.getInt("IDFilme"),
                        rs.getString("Titulo"),
                        rs.getInt("Ano"),
                        rs.getInt("Duracao"),
                        rs.getString("Sinopse"),
                        rs.getString("Poster")
                    );
                    filmes.add(fb);
                }
            }
        }
        
        return filmes;
    }
}
