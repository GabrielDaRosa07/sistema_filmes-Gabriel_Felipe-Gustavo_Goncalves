

/**
 *
 * @author gabriel-da-rosa
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

                    case 1:
                        new UsuarioController().createUsuario(con);
                        break;
                    case 2:
                        new UsuarioController().listarUsuarios(con);
                        break;
                    case 5:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida, digite novamente:");
                        break;
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
        System.out.println("5 - Sair");
        System.out.print("\nOpçao escolhida: ");
        
        Scanner s = new Scanner(System.in);
        
        if(s.hasNextInt()){
            return s.nextInt();
        }
        
        return 0;
    }
    
    
}
