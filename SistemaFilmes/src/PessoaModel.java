/**
 *
 * @author gabrielrosa
*/

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
    
}
