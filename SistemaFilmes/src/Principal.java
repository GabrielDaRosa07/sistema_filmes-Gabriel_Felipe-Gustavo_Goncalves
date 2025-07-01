/**
 * - LEMBRAR DE ADICIONAR O .JAR DO DRIVER JDBC NOS LIBRARIES
 * - LINK JAVA 8 PRA CIMA: https://jdbc.postgresql.org/download/postgresql-42.7.7.jar
 * 
 * @author gabriel-da-rosa : gustavo-gonçalves
 * 
 * essa o nome ja diz tudo
 */

import java.sql.*;
import java.util.*;

public class Principal {
    
    public static void main(String[] args) {
        
        Conexao c = new Conexao();
        Connection con = c.getConnection();
        int op = 0;

        do{

            op=menu();

            try{

                switch(op){

                    case 1 -> new UsuarioController().createUsuario(con);
                    case 2 -> new UsuarioController().listarUsuarios(con);
                    case 3 -> new FilmeController().createFilme(con);
                    case 4 -> new FilmeController().listarFilmes(con);
                    case 5 -> System.out.println("Saindo...");
                    default -> System.out.println("Opção inválida, digite novamente:");
                }
            }catch(SQLException ex){
                System.err.println("Erro do banco de dados: "+ex.getMessage());   
            }
        }while (op!=5);
        
        c.closeConnection();
        System.out.println("Conexao terminada");
        
    }
    
    private static int menu(){
        
        System.out.println("\n -----MENU-----");
        System.out.println("\n1 - Inserir novo usuario");
        System.out.println("2 - Listar os usuarios");
        System.out.println("3 - Inserir novo filme");
        System.out.println("4 - Listar os filmes");
        System.out.println("5 - Sair");
        System.out.print("\nOpçao escolhida: ");
        
        Scanner s = new Scanner(System.in);
        
        if(s.hasNextInt()){
            return s.nextInt();
        }
        
        return 0;
    }
    
    
}
