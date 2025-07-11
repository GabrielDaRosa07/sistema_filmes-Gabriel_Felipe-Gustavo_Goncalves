package sistemafilmes.main;

import java.sql.*;
/**
 * - LEMBRAR DE ADICIONAR O .JAR DO DRIVER JDBC NOS LIBRARIES
 * - LINK JAVA 8 PRA CIMA: https://jdbc.postgresql.org/download/postgresql-42.7.7.jar
 * 
 * @author gabriel-da-rosa : gustavo-gonçalves
 * 
 * Classe de conexao com o banco
 */
public class Conexao {
    
    private Connection con;
    
    public Conexao(){
        String url = "jdbc:postgresql://localhost:5432/cinema";
        String user = "postgres";
        String senha = "udesc";
        
        
        try{
            Class.forName("org.postgresql.Driver");
            this.con = DriverManager.getConnection(url,user,senha);
        }catch (ClassNotFoundException | SQLException e){
            System.err.println("Falha na conexão com o banco:" +e.getMessage());
            System.exit(1);
        }
        
    }
    
    public Connection getConnection(){
        return con;
    }
    
    public void closeConnection(){
        try{
            if (this.con != null && !this.con.isClosed()){
                this.con.close();
            }
        }catch (SQLException e){
            System.err.println("Erro ao fechar a conexao: "+e.getMessage());
        }
    }
    
    
}
