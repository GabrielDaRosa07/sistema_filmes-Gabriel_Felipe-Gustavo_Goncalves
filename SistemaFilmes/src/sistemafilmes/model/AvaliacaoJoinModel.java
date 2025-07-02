package sistemafilmes.model;

/**
 *
 * @author gabrielrosa
 */

import sistemafilmes.bean.AvaliacaoJoinBean;
import java.sql.*;
import java.util.*;

public class AvaliacaoJoinModel {
    
    public static ArrayList<AvaliacaoJoinBean> listAllJoin(Connection con) throws SQLException{
        
        String sql = "SELECT u.Nome, f.Titulo, a.Nota, a.Critica " + 
                     "FROM Avaliacao a " +                          
                     "JOIN Usuario u ON a.IDUser = u.IDUser " +      
                     "JOIN Filme f ON a.IDFilme = f.IDFilme " +      
                     "ORDER BY f.Titulo, u.Nome";

        ArrayList<AvaliacaoJoinBean> listaAvaliacoes = new ArrayList<>();
        
        try(Statement st = con.createStatement(); ResultSet result = st.executeQuery(sql)){
            while(result.next()){
                //pra cada linha do resultado, cria um AvaliacaoJoinBean
                AvaliacaoJoinBean adb = new AvaliacaoJoinBean(
                   result.getString("Nome"),   //coluna usuario
                   result.getString("Titulo"), //  ||   filme
                   result.getDouble("Nota"),   //  ||   avaliacao
                   result.getString("Critica") //  ||   avaliacao
                );
                listaAvaliacoes.add(adb);
            }
        }
        return listaAvaliacoes;
    }
    
}
