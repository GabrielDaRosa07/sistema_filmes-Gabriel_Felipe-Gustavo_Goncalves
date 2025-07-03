package sistemafilmes.model;

/**
 *
 * @author gabriel-da-rosa : gustavo-gonçalves
 */

import sistemafilmes.bean.*;

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
        
        String sql = "SELECT f.IDFilme, f.Titulo, f.Ano, f.Duracao, f.Sinopse, f.Poster, " +
                     "STRING_AGG(g.Nome, ', ') AS Generos " +
                     "FROM Filme f " +
                     "LEFT JOIN Possui p ON f.IDFilme = p.IDFilme " +
                     "LEFT JOIN Genero g ON p.IDGenero = g.IDGenero " +
                     "GROUP BY f.IDFilme " +
                     "ORDER BY f.Titulo";
        
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

                String generosConcatenados = result.getString("Generos");                
                if (generosConcatenados != null) {
                    // Separa a string de generos em uma lista
                    List<String> generosList = List.of(generosConcatenados.split(", "));
                    fb.setGeneros(generosList);
                }                
                
                listaFilmes.add(fb);
            }  
        }
        return listaFilmes;
        
    }
    
    public static ArrayList<FilmeNotaBean> listMedia(Connection con) throws SQLException{
        
        String sql = "SELECT f.Titulo, a.Nota " +
                     "FROM Avaliacao a " +
                     "JOIN Filme f ON a.IDFilme = f.IDFilme " +
                     "WHERE a.Nota > (SELECT AVG(Nota) FROM Avaliacao) " +
                     "ORDER BY a.Nota DESC"; // ordena o melhor pro pior

        
        ArrayList<FilmeNotaBean> lista = new ArrayList<>();
        
        try(Statement st = con.createStatement(); ResultSet result = st.executeQuery(sql)){
            while(result.next()){
                FilmeNotaBean fnb = new FilmeNotaBean(
                        result.getString("Titulo"),
                        result.getDouble("Nota")
                );
                lista.add(fnb);
            }
        }
        return lista;
    }
    
    public static boolean idExists(int id, Connection con) throws SQLException{
        
        String sql = "SELECT COUNT(*) FROM Filme WHERE IDFilme = ?";
        
        try (PreparedStatement st = con.prepareStatement(sql)){
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
    
    public static void associarGenero(int idFilme, int idGenero, Connection con) throws SQLException{
        
        String sqlCheck = "SELECT COUNT(*) FROM Possui WHERE IDFilme = ? AND IDGenero = ?";        
        
        try(PreparedStatement stCheck = con.prepareStatement(sqlCheck)){
           
            stCheck.setInt(1,idFilme);
            stCheck.setInt(2, idGenero);
            
            try(ResultSet rs = stCheck.executeQuery()){
                if (rs.next() && rs.getInt(1) > 0) {
                    System.out.println("Este filme já está associado a este gênero.");
                    return; // Sai do método se a associação já existe
                }
            }
        }
        
        String sqlInsert = "INSERT INTO Possui(IDFilme, IDGenero) VALUES (?, ?)";
        
        try(PreparedStatement stInsert = con.prepareStatement(sqlInsert)){
            
            stInsert.setInt(1, idFilme);
            stInsert.setInt(2, idGenero);
            stInsert.execute();
            System.out.println("Gênero associado ao filme com sucesso!");
            
        }
            
    }
    
    public static void adicionarElenco(int idFilme,int idPessoa,Connection con) throws SQLException{
        
        String sqlCheck = "SELECT COUNT(*) FROM Elenco WHERE IDFilme = ? AND IDPessoa = ?";
        try(PreparedStatement stCheck = con.prepareStatement(sqlCheck)){
            stCheck.setInt(1,idFilme);
            stCheck.setInt(2,idPessoa);
            try(ResultSet rs = stCheck.executeQuery()){
                if(rs.next() && rs.getInt(1)>0){
                    System.out.println("Essa pessoa já está no elenco deste filme.");
                    return;
                }
            }
        }
        
        String sqlInsert = "INSERT INTO Elenco (IDFilme, IDPessoa) VALUES (?, ?)";
        try (PreparedStatement stInsert = con.prepareStatement(sqlInsert)) {
            stInsert.setInt(1, idFilme);
            stInsert.setInt(2, idPessoa);
            stInsert.execute();
            System.out.println("Pessoa adicionada ao elenco :) ");
        }
    }
    
}
