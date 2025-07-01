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
        System.out.println("Título: ");
        String titulo = s.nextLine();
        System.out.println("Ano de lançamento: ");
        int ano = s.nextInt();
        System.out.println("Duração em Minutos: ");
        int duracao = s.nextInt();
        s.nextLine();
        System.out.println("Sinopse: ");
        String sinopse = s.nextLine();
        System.out.println("Url do poster: ");
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
    
}
