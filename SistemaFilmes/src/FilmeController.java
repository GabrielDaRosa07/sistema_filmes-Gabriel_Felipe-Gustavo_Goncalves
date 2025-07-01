/**
 *
 * @author gabrielrosa
 */

import java.sql.*;
import java.util.*;

public class FilmeController {
    
    public void createFilme(Connection con) throws SQLException{
        
        Scanner s = new Scanner(System.in);
        System.out.println("\n-----Cadastro de Filme-----\n");
        
        int idFilme;
        Random random = new Random();
        
        do{
            idFilme = 1+random.nextInt(9999);
        }while (FilmeModel.idExists(idFilme, con));
        
        System.out.println("\nID gerado: "+idFilme);
        System.out.print("Título: ");
        String titulo = s.nextLine();
        System.out.print("Ano de lançamento: ");
        int ano = s.nextInt();
        System.out.print("Duração em Minutos: ");
        int duracao = s.nextInt();
        s.nextLine();
        System.out.print("Sinopse: ");
        String sinopse = s.nextLine();
        System.out.print("Url do poster: ");
        String poster = s.nextLine();
        
        FilmeBean fb = new FilmeBean(idFilme, titulo, ano, duracao, sinopse, poster);
        FilmeModel.create(fb, con);
        
        System.out.println("Filme Cadastrado :)");
    }
    
    public void listarFilmes(Connection con) throws SQLException{
        
        System.out.println("-----Lista de Filmes-----\n");
        
        ArrayList<FilmeBean> listaFilmes = FilmeModel.listAll(con);
        
        for(FilmeBean fb:listaFilmes){
            System.out.println(fb.toString());
        }
        
    }
    
    public void deleteFilme(Connection con) throws SQLException{
        
        Scanner s = new Scanner(System.in);
        System.out.println("-----Remover Filme-----\n");
        System.out.print("Digite o id do filme a ser removido: ");
        
        if(!s.hasNextInt()){
            System.out.println("Filme nao encontrado, digite um válido");
            return;
        }
        
        int id = s.nextInt();
        s.nextLine();
        
        System.out.print("Tem certeza da remoção? (s/n) ");
        String confirmacao = s.next();
        
        if(confirmacao.equalsIgnoreCase("s")){
            FilmeModel.delete(id, con);
        }else{
            System.out.println("A remoção foi cancelada :(");
        }
    }
    
}
