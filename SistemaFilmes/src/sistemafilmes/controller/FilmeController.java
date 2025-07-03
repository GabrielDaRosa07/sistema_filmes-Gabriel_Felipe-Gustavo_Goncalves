package sistemafilmes.controller;

/**
 *
 * @author gabriel-da-rosa : gustavo-gonçalves
 */

import sistemafilmes.bean.*;
import sistemafilmes.model.*;
import java.sql.*;
import java.util.*;

public class FilmeController {
    
    public void createFilme(Connection con) throws SQLException{
        
        Scanner s = new Scanner(System.in);
        System.out.println("\n-----Cadastro de Filme-----");
        
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
        
        System.out.println("\n-----Lista de Filmes Cadastrados-----");
        ArrayList<FilmeBean> listaFilmes = FilmeModel.listAll(con);
       
        for(FilmeBean fb:listaFilmes){
            System.out.println(fb.toString());
        } 
        
        Scanner input = new Scanner(System.in);
        int idFilmeEscolhido;
        
        do {
            System.out.print("\nDigite o ID de um filme para ver o elenco (ou 0 para voltar ao menu principal): ");
            if(!input.hasNextInt()){
                System.out.println("Entrada inválida. Tente novamente.");
                input.next();
                idFilmeEscolhido = -1;
                continue;
            }
            idFilmeEscolhido = input.nextInt();
            input.nextLine();
            if(idFilmeEscolhido != 0){
                System.out.println("\n-----Elenco do Filme-----");
                ArrayList<PessoaBean> elenco = PessoaModel.listarElenco(idFilmeEscolhido, con);

                if(elenco.isEmpty()) {
                    System.out.println("Nenhuma pessoa encontrada no elenco");
                }else{
                    for(PessoaBean p:elenco){
                        System.out.println(p.toString());
                    }
                }
            }
        } while (idFilmeEscolhido != 0);
    }
    
    public void listarFilmesMedia(Connection con) throws SQLException{
        
        System.out.println("\n-----Filmes com nota acima de media de todos os filmes-----");
        
        ArrayList<FilmeNotaBean> lista = FilmeModel.listMedia(con);
        
        if(lista.isEmpty()){
            System.out.println("Não tem filmes acima da média ou não tem notas suficientes :(");        
        }else{
            for (FilmeNotaBean fnb:lista){
                System.out.println(fnb.toString());
            }
        }
        
    }
    
    public void deleteFilme(Connection con) throws SQLException{
        
        Scanner s = new Scanner(System.in);
        System.out.println("\n-----Remover Filme-----");
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
    
    public void associarGeneroFilme(Connection con) throws SQLException{
        
        Scanner s = new Scanner(System.in);
        System.out.println("\n-----Associar Gênero a um Filme-----");
        
        System.out.println("\nFilmes disponíveis:");
        new FilmeController().listarFilmes(con); 
        System.out.print("\nDigite o ID do Filme: ");
        int idFilme = s.nextInt();
        s.nextLine(); 
        
        String querContinuar;
        
        do{
            System.out.println("\nGeneros disponíveis:");
            new GeneroController().listarGeneros(con); 
            System.out.print("\nDigite o ID do Genero: ");
            int idGenero = s.nextInt();
            s.nextLine(); 
        
            FilmeModel.associarGenero(idFilme, idGenero, con);
            
            System.out.print("\nDeseja associar outro genero a esse filme? (s/n): ");
            querContinuar = s.nextLine();            
        }while (querContinuar.equalsIgnoreCase("s"));
        
        System.out.println("Associação de gêneros concluida :) .");
        
    }
    
}
