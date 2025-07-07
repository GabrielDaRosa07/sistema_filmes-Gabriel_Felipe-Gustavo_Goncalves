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

                    case 1:
                        new UsuarioController().createUsuario(con);
                        break;
                    case 2:
                        new UsuarioController().listarUsuarios(con);
                        break;
                    case 3:
                        new UsuarioController().deleteUsuario(con);
                        break;
                    case 4:
                        new FilmeController().createFilme(con);
                        break;
                    case 5:
                        new FilmeController().listarFilmes(con);
                        break;
                    case 6:
                        new FilmeController().deleteFilme(con);
                        break;
                    case 7:
                        new PessoaController().createPessoa(con);
                        break;
                    case 8:
                        new PessoaController().listarPessoas(con);
                        break;
                    case 9:
                        new PessoaController().deletePessoa(con);
                        break;
                    case 10:
                        new GeneroController().createGenero(con);
                        break;
                    case 11:
                        new GeneroController().listarGeneros(con);
                        break;
                    case 12:
                        new GeneroController().deleteGenero(con);
                        break;
                    case 13:
                        new AvaliacaoController().createAvaliacao(con);
                        break;
                    case 14:
                        new AvaliacaoController().deleteAvaliacao(con);
                        break;
                    case 15:
                        new AvaliacaoJoinController().listarAvaliacoesJoin(con);
                        break;
                    case 16:
                        new AvaliacaoController().listarAvaliacoesDeUmUsuario(con);
                        break;                    
                    case 17:
                        new FilmeController().listarFilmesMedia(con);
                        break;
                    case 18:
                        new FilmeController().associarGeneroFilme(con);
                        break;
                    case 19:
                        new ElencoController().adicionarPessoaAoElenco(con);
                        break;
                    case 20:
                        new ListaController().createLista(con);
                        break;
                    case 21:
                        new ListaController().listarListasDoUsuario(con);
                        break;
                    case 22:
                        new ListaController().deleteLista(con);
                        break;
                    case 23:
                        new ListaController().adicionarFilmeLista(con);
                        break;

                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida, digite novamente:");
                        break;
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
        System.out.println("13 - Criar uma avaliacao | 14 - Deletar uma avaliacao");
        System.out.println("15 - Listar Avaliações (JOIN) | 16 - Listar Avaliações de um usuario");        
        System.out.println("17 - Filmes com nota acima de media de todos os filmes(SUBQUERY)");        
        System.out.println("18 - Associar genero a um filme | 19 - Adicionar pessoa ao elenco de um filme");
        System.out.println("\n--- LISTAS --- SISTEMA DE FILMES ---\n");
        System.out.println("20 - Criar Lista | 21 - Ver Listas");
        System.out.println("22 - Remover Lista | 23 - Associar filme a lista");
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
