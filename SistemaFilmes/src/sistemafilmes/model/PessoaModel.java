package sistemafilmes.model;

/**
 *
 * @author gabriel-da-rosa : gustavo-gonçalves
*/

import sistemafilmes.bean.PessoaBean;
import java.sql.*;
import java.util.*;

public class PessoaModel {
    
    public static void create(PessoaBean pb, Connection con) throws SQLException{
        
        String sql = "INSERT INTO Pessoa (IDPessoa,Nome,Papel) VALUES (?,?,?)";
        
        try(PreparedStatement st = con.prepareStatement(sql)){
            st.setInt(1,pb.getIdPessoa());
            st.setString(2,pb.getNome());
            st.setString(3,pb.getPapel());
            st.execute();
        }
    }
    
    public static ArrayList<PessoaBean> listAll(Connection con) throws SQLException{
        
        String sql = "SELECT IDPessoa, Nome, Papel FROM Pessoa ORDER BY nome";
        ArrayList<PessoaBean> listaPessoas = new ArrayList<>();
        
        try(Statement st = con.createStatement(); ResultSet result = st.executeQuery(sql)){
            
            while(result.next()){
                PessoaBean pb = new PessoaBean(
                    result.getInt("IDPessoa"),
                    result.getString("Nome"),
                    result.getString("Papel")
                );
                listaPessoas.add(pb);
            }
            
        }
        return listaPessoas;
    }
    
    public static ArrayList<PessoaBean>listarElenco(int idFilme,Connection con) throws SQLException {

        String sql = "SELECT p.IDPessoa, p.Nome, p.Papel FROM Pessoa p " +
                     "JOIN Elenco e ON p.IDPessoa = e.IDPessoa " +
                     "WHERE e.IDFilme = ? " +
                     "ORDER BY p.Nome";
        ArrayList<PessoaBean> elenco = new ArrayList<>();
        try(PreparedStatement st = con.prepareStatement(sql)){
            st.setInt(1,idFilme);
            try(ResultSet result = st.executeQuery()){
                while(result.next()){
                    PessoaBean pb = new PessoaBean(
                        result.getInt("IDPessoa"),
                        result.getString("Nome"),
                        result.getString("Papel")
                    );
                    elenco.add(pb);
                }
            }
        }
        return elenco;
        
    }
    
    public static boolean idExists(int id,Connection con) throws SQLException{
        
        String sql = "SELECT COUNT(*) FROM Pessoa WHERE IDPessoa = ?";
        
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
    
    public static void delete(int id, Connection con) throws SQLException{
        
        String sqlElenco = "DELETE FROM Elenco WHERE IDPessoa = ?";
        String sqlPessoa = "DELETE FROM Pessoa WHERE IDPessoa = ?";
        
        try (PreparedStatement stElenco = con.prepareStatement(sqlElenco)){
            
            stElenco.setInt(1, id);
            stElenco.executeUpdate();
            
        }
        
        try(PreparedStatement stPessoa = con.prepareStatement(sqlPessoa)){
            
            stPessoa.setInt(1, id);
            int rowsAffected = stPessoa.executeUpdate();
            
            if(rowsAffected >0){
                System.out.println("Pessoa de ID "+id+" foi removida :) ");
            }else{
                System.out.println("Nenhuma pessoa encontrada com o ID "+id+" :( ");
            }
            
        }
        
    }
    
}
