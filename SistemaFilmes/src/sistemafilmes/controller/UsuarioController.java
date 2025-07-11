package sistemafilmes.controller;


/**
 *
 * @author gabriel-da-rosa : gustavo-gonçalves
 * 
 */

import sistemafilmes.model.UsuarioModel;
import sistemafilmes.bean.UsuarioBean;
import java.sql.*;
import java.util.*;

public class UsuarioController {
    
    public void createUsuario(Connection con) throws SQLException{
        
        Scanner s = new Scanner(System.in);
        System.out.println("\n-----Cadastro de novo usuario-----");
        
        int id;
        Random random = new Random();
        
        do{
            id = 1+random.nextInt(999);
        }while (UsuarioModel.idExists(id, con));
        
        System.out.println("\nId gerado: "+id);
        
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
        
        System.out.println("\n-----Lista de Usuáruos Cadastrados-----");
        
        ArrayList<UsuarioBean> all = UsuarioModel.listAll(con);
      
        for(UsuarioBean ub: all){
            System.out.println(ub.toString());
        }
    }
    
    public void deleteUsuario(Connection con) throws SQLException{
        
        Scanner s = new Scanner(System.in);
        System.out.println("\n-----Remover Usuário-----");
        new UsuarioController().listarUsuarios(con);
        System.out.print("\nDigite o ID do usuário que quer remover: ");
        
        if(!s.hasNextInt()){
            
            System.out.print("Entrada inválida, digite um ID existente");
            return;
        }
        
        int id = s.nextInt();
        s.nextLine();
        
        System.out.print("Tem certeza da remoção? (s/n) ");
        String confirmacao = s.next();
        
        if(confirmacao.equalsIgnoreCase("s")){
            UsuarioModel.delete(id,con);
        }else{
            System.out.println("Remoçao cancelada :(");
        }
        
    }
}
