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

import sistemafilmes.controller.UsuarioController;
import sistemafilmes.controller.PessoaController;
import sistemafilmes.controller.GeneroController;
import sistemafilmes.controller.FilmeController;
import sistemafilmes.main.Conexao;
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
                    case 0 -> System.out.println("Saindo...");
                    default -> System.out.println("Opção inválida, digite novamente:");
                }
            }catch(SQLException ex){
                System.err.println("Erro do banco de dados: "+ex.getMessage());   
            }
        }while (op!=0);
        
        c.closeConnection();
        System.out.println("Conexao terminada");
        
    }
    
    private static int menu(){
        
        System.out.println("\n -----MENU-----");
        System.out.println("\n1 - Inserir novo usuario");
        System.out.println("2 - Listar os usuarios");
        System.out.println("3 - Deletar um usuario\n");
        System.out.println("4 - Inserir novo filme");
        System.out.println("5 - Listar os filmes");
        System.out.println("6 - Deletar um filme\n");
        System.out.println("7 - Inserir Pessoa (Ator/Diretor)");
        System.out.println("8 - Listar as Pessoas(Ator/Diretor)");
        System.out.println("9 - Deletar uma Pessoa (Ator/Diretor)\n");
        System.out.println("10 - Inserir novo Genero");
        System.out.println("11 - Listar os Generos");
        System.out.println("12 - Deletar um Genero\n");
        System.out.println("0 - Sair");
        System.out.print("\nOpçao escolhida: ");
        
        Scanner s = new Scanner(System.in);
        
        if(s.hasNextInt()){
            return s.nextInt();
        }
        
        return 0;
    }
    
    
}
