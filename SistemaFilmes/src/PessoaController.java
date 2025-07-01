/**
 *
 * @author gabrielrosa
 */

import java.sql.*;
import java.util.*;

public class PessoaController {
    
    public void createPessoa(Connection con) throws SQLException{
        
        Scanner s = new Scanner(System.in);
        System.out.println("\n-----Cadastro de Pessoa (Diretor, Ator)-----\n");
        
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
        
        System.out.println("-----Lista de Pessoas(Ator, Diretor)-----\n");
        ArrayList<PessoaBean> listaPessoas = PessoaModel.listAll(con);
        
        for(PessoaBean pb:listaPessoas){
            System.out.println(pb.toString());
        }
        
    }
    
}
