package sistemafilmes.controller;

/**
 *
 * @author gabriel-da-rosa : gustavo-gonçalves
 */

import sistemafilmes.model.PessoaModel;
import sistemafilmes.bean.PessoaBean;
import java.sql.*;
import java.util.*;

public class PessoaController {
    
    public void createPessoa(Connection con) throws SQLException{
        
        Scanner s = new Scanner(System.in);
        System.out.println("\n-----Cadastro de Pessoa (Diretor, Ator)-----");
        
        int idPessoa;
        Random random = new Random();
        
        do{
            idPessoa = 1+random.nextInt(999);
        }while(PessoaModel.idExists(idPessoa, con));
        
        System.out.println("Id Gerado: "+idPessoa);
        System.out.print("Nome da Pessoa: ");
        String nome = s.nextLine();
        System.out.print("Papel: ");
        String papel = s.nextLine();
        
        PessoaBean pb = new PessoaBean(idPessoa, nome, papel);
        PessoaModel.create(pb, con);
        
        System.out.println("Pessoa Cadastrada :)");
        
    }
    
    public void listarPessoas(Connection con) throws SQLException{
        
        System.out.println("\n-----Lista de Pessoas(Ator, Diretor)-----");
        ArrayList<PessoaBean> listaPessoas = PessoaModel.listAll(con);
        
        for(PessoaBean pb:listaPessoas){
            System.out.println(pb.toString());
        }
        
    }
    
    public void deletePessoa(Connection con) throws SQLException{
        
        Scanner s = new Scanner(System.in);
        System.out.println("\n-----Remover Pessoa (Ator/Diretor)-----");
        new PessoaController().listarPessoas(con);
        System.out.print("\nDigite o ID da pessoa que quer remover:");
        
        if(!s.hasNextInt()){
            
            System.out.print("Entrada inválida, digite um ID existente");
            return;
        }
        
        int id = s.nextInt();
        s.nextLine();
        
        System.out.print("\nTem certeza da remoção? (s/n) ");
        String confirmacao = s.next();
        
        if(confirmacao.equalsIgnoreCase("s")){
            PessoaModel.delete(id,con);
        }else{
            System.out.println("Remoçao cancelada :(");
        }
        
    }
    
}
