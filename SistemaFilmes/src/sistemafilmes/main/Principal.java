package sistemafilmes.main;

/**
 * - LEMBRAR DE ADICIONAR O .JAR DO DRIVER JDBC NOS LIBRARIES
 * - LINK JAVA 8 PRA CIMA: https://jdbc.postgresql.org/download/postgresql-42.7.7.jar
 * 
 * IDEIAS PARA AS ULTIMAS ETAPAS:
 * 
 * - Listar todas as avaliaçoes, mostrando o nome do usuario que a fez e o titulo do filme
 * - Listar todos os filmes que tem uma nota de avaliacao superior a media geral das notas dos filmes
 * 
 * @author gabriel-da-rosa : gustavo-gonçalves
 *
 * essa o nome ja diz tudo
 */

import sistemafilmes.controller.*;
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
                    case 3 -> new UsuarioController().deleteUsuario(con);
                    case 4 -> new FilmeController().createFilme(con);
                    case 5 -> new FilmeController().listarFilmes(con);
                    case 6 -> new FilmeController().deleteFilme(con);
                    case 7 -> new PessoaController().createPessoa(con);
                    case 8 -> new PessoaController().listarPessoas(con);
                    case 9 -> new PessoaController().deletePessoa(con);
                    case 10 -> new GeneroController().createGenero(con);
                    case 11 -> new GeneroController().listarGeneros(con);
                    case 12 -> new GeneroController().deleteGenero(con);
                    case 13 -> new AvaliacaoController().createAvaliacao(con);
                    case 14 -> new AvaliacaoController().deleteAvaliacao(con);
                    case 15 -> new AvaliacaoJoinController().listarAvaliacoesJoin(con);
                    case 16 -> new FilmeController().listarFilmesMedia(con);
                    case 17 -> new FilmeController().associarGeneroFilme(con);
                    case 18 -> new ElencoController().adicionarPessoaAoElenco(con);
                    case 19 -> new ListaController().createLista(con);
                    case 20 -> new ListaController().listarListasDoUsuario(con);
                    case 21 -> new ListaController().deleteLista(con);
                    case 22 -> new ListaController().adicionarFilmeLista(con);
                    case 0 -> System.out.println("Saindo...");
                    default -> System.out.println("Opção inválida, digite novamente:");
                }
            }catch(SQLException ex){
                System.err.println("Erro do banco de dados: "+ex.getMessage());  
            }
            
            if(op!=0){
                pausa();
            }
            
        }while (op!=0);
        
        c.closeConnection();
        System.out.println("Conexao terminada");
        
    }
    
    private static int menu(){
        
        System.out.println("\n--- MENU PRINCIPAL--- SISTEMA DE FILMES ---\n");
        System.out.println("1 - Inserir Usuário | 2 - Listar Usuários | 3 - Remover Usuário");
        System.out.println("4 - Inserir Filme   | 5 - Listar Filmes   | 6 - Remover Filme");
        System.out.println("7 - Inserir Pessoa  | 8 - Listar Pessoas  | 9 - Remover Pessoa");
        System.out.println("10 - Inserir Gênero | 11 - Listar Gêneros | 12 - Remover Gênero");
        System.out.println("\n--- AÇÕES E CONSULTAS COMPLEXAS --- SISTEMA DE FILMES ---\n");
        System.out.println("13 - Criar uma avaliacao | 14 - Deletar uma avaliacao | 15 - Listar Avaliações (JOIN)");
        System.out.println("16 - Media das notas das Avaliações (SUBQUERY)");
        System.out.println("17 - Associar genero a um filme");
        System.out.println("18 - Adicionar pessoa ao elenco de um filme");
        System.out.println("\n--- LISTAS --- SISTEMA DE FILMES ---\n");
        System.out.println("19 - Criar Lista | 20 - Ver Listas | 21 - Remover Lista");
        System.out.println("22 - Associar filme a lista");
        System.out.println("\n--- --- --- ---");
        System.out.println("0 - Sair");
        System.out.println("--- --- --- ---");
        System.out.print("Sua opção: ");
        
        Scanner s = new Scanner(System.in);
        
        if(s.hasNextInt()){
            return s.nextInt();
        }
        
        return 0;
    }
    
    private static void pausa(){
        System.out.println("\n--- --- --- ---");
        System.out.print("Pressione enter para continuar no menu");
        System.out.println("\n--- --- --- ---");
        try{
            new Scanner(System.in).nextLine();
        }catch(Exception e){
            
        }
        
    }
    
    
}
