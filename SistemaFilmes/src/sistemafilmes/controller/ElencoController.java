
package sistemafilmes.controller;

/**
 *
 * @author gabriel-da-rosa : gustavo-gonçalves
 */

import java.sql.*;
import java.util.*;
import sistemafilmes.bean.*;
import sistemafilmes.model.*;

public class ElencoController {
    
    public void adicionarPessoaAoElenco(Connection con) throws SQLException{
        
        Scanner s = new Scanner(System.in);
        System.out.println("\n-----Adicionar Pessoa ao Elenco de um Filme-----");

        System.out.println("\nFilmes disponíveis:");
        new ElencoController().listarFilmesSimples(con);
        System.out.print("\nDigite o ID do Filme: ");
        int idFilme = s.nextInt();

        System.out.println("\nPessoas (Atores/Diretores) disponíveis:");
        new PessoaController().listarPessoas(con);
        System.out.print("\nDigite o ID da Pessoa pra adicionar no elenco: ");
        int idPessoa = s.nextInt();

        ElencoBean eb = new ElencoBean(idFilme, idPessoa);
        ElencoModel.create(eb, con);
    }
    
    public void listarFilmesSimples(Connection con) throws SQLException{
        
        ArrayList<FilmeBean> listaFilmes = FilmeModel.listAll(con);
        
        for(FilmeBean fb:listaFilmes){
            System.out.println(fb.toString());
        }
    }
    
}
