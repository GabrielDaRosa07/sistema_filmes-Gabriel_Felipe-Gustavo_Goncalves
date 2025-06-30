
/**
 *
 * @author gabriel-da-rosa
 * 
 * Ele e o meio termo, nao fala com o banco e nem mostra nada, ele gera a logica das operacoes.
 */

import java.sql.*;
import java.util.*;

public class UsuarioController {
    
    public void createUsuario(Connection con) throws SQLException{
        
        Scanner s = new Scanner(System.in);
        System.out.println("\n----- Cadastro de novo usuario -----\n");
        
        int id;
        Random random = new Random();
        
        do{
            id = 1+random.nextInt(999);
        }while (UsuarioModel.idExists(id, con));
        
        System.out.println("Id gerado: "+id);
        
        System.out.print("Nome: ");
        String nome = s.nextLine();
        System.out.print("Email: ");
        String email = s.nextLine();
        System.out.print("Senha: ");
        String senha = s.nextLine();
        System.out.print("É um critico (s/n): ");
        String resposta = s.next();
        boolean credencial = resposta.equalsIgnoreCase("s");
        
        
        UsuarioBean ub = new UsuarioBean(id, nome, email, senha, credencial);
        
        UsuarioModel.create(ub, con);
        
        System.out.println("Usuário Criado :)");
        
    }
    
    
    public void listarUsuarios(Connection con) throws SQLException{
        
        System.out.println("-----Lista de Usuáruos Cadastrados-----\n");
        
        ArrayList<UsuarioBean> all = UsuarioModel.listAll(con);
      
        for(UsuarioBean ub: all){
            System.out.println(ub.toString());
        }
    } 
}
