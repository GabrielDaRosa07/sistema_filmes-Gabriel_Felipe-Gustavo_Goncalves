package sistemafilmes.model;

import sistemafilmes.bean.*;
import java.sql.*;
import java.util.*;

/**
 *
 * @author gabriel-da-rosa : gustavo-gonçalves
 */
public class AvaliacaoModel {
    
    public static void create(AvaliacaoBean ab, Connection con) throws SQLException{
        
        String sql = "INSERT INTO Avaliacao (IDUser, IDFilme, Critica, Nota, Data) VALUES (?,?,?,?,?)";
        try(PreparedStatement st = con.prepareStatement(sql)){
            st.setInt(1,ab.getIdUser());
            st.setInt(2,ab.getIdFilme());
            st.setString(3,ab.getCritica());
            st.setDouble(4,ab.getNota());
            st.setDate(5,ab.getData());
            st.execute();
        }
        
    }
    
    public static void delete(int idUser,int idFilme,Connection con) throws SQLException{
        String sql = "DELETE FROM Avaliacao WHERE IDUser = ? AND IDFilme = ?";
        try(PreparedStatement st = con.prepareStatement(sql)){
            st.setInt(1,idUser);
            st.setInt(2,idFilme);
            if (st.executeUpdate()>0){
                System.out.println("Avaliação removida com sucesso.");
            }else{
                System.out.println("Nenhuma avaliação encontrada para este usuário e filme.");
            }
        }
    }
    
    public static ArrayList<AvaliacaoJoinBean> listByUser(int idUser, Connection con) throws SQLException{
        String sql = "SELECT u.Nome, f.Titulo, a.Nota, a.Critica, f.IDFilme " +
                     "FROM Avaliacao a " +
                     "JOIN Usuario u ON a.IDUser = u.IDUser " +
                     "JOIN Filme f ON a.IDFilme = f.IDFilme " +
                     "WHERE a.IDUser = ? " +
                     "ORDER BY f.Titulo";

        ArrayList<AvaliacaoJoinBean> listaAvaliacoes = new ArrayList<>();

        try (PreparedStatement st = con.prepareStatement(sql)){
            st.setInt(1, idUser);

            try (ResultSet result = st.executeQuery()){
                while (result.next()){
                    AvaliacaoJoinBean ajb = new AvaliacaoJoinBean(
                        result.getString("Nome"),
                        result.getString("Titulo"),
                        result.getDouble("Nota"),
                        result.getString("Critica")
                    );
                    listaAvaliacoes.add(ajb);
                }
            }
        }
        return listaAvaliacoes;
    }
    
    public static boolean idExists(int idUser,int idFilme,Connection con) throws SQLException{
        String sql = "SELECT COUNT(*) FROM Avaliacao WHERE IDUser = ? AND IDFilme = ?";
        try(PreparedStatement st = con.prepareStatement(sql)){
            st.setInt(1,idUser);
            st.setInt(2,idFilme);
            try (ResultSet rs = st.executeQuery()){
                if (rs.next()){
                    return rs.getInt(1)>0;
                }
            }
        }
        return false;
    }
    
    public static boolean avaliacaoExists(int idUser,int idFilme,Connection con) throws SQLException{
        String sql = "SELECT COUNT(*) FROM Avaliacao WHERE IDUser = ? AND IDFilme = ?";
        try(PreparedStatement st = con.prepareStatement(sql)){
            st.setInt(1, idUser);
            st.setInt(2, idFilme);
            try(ResultSet rs = st.executeQuery()){
                if (rs.next()){
                    return rs.getInt(1)>0;
                }
            }
        }
        return false;
    }

    public static void update(AvaliacaoBean ab,Connection con) throws SQLException{
        String sql = "UPDATE Avaliacao SET Critica = ?, Nota = ?, Data = ? WHERE IDUser = ? AND IDFilme = ?";
        try(PreparedStatement st = con.prepareStatement(sql)){
            st.setString(1, ab.getCritica());
            st.setDouble(2, ab.getNota());
            st.setDate(3, ab.getData());
            st.setInt(4, ab.getIdUser());
            st.setInt(5, ab.getIdFilme());
            st.executeUpdate();
            System.out.println("\nAvaliação atualizada :) ");
        }
    }
    
}
