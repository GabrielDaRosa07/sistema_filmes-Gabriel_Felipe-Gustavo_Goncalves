
package sistemafilmes.controller;

/**
 *
 * @author gabriel-da-rosa : gustavo-gonçalves
 */

import java.sql.*;
import java.util.*;
import sistemafilmes.bean.*;
import sistemafilmes.model.*;

public class ListaController {
    
    
    public void createLista(Connection con) throws SQLException{
        Scanner s = new Scanner(System.in);
        System.out.println("\n-----Criar lista de filmes-----");

        System.out.println("\nUsuários:");
        new UsuarioController().listarUsuarios(con);
        System.out.print("\nDigite o ID do Usuário que vai ser o dono da lista: ");
        int idUser = s.nextInt();
        s.nextLine(); 

        System.out.print("\nNome da Lista: ");
        String nome = s.nextLine();
        System.out.print("\nA lista será privada? (s/n): ");
        boolean privada = s.next().equalsIgnoreCase("s");

        int idLista;
        Random random = new Random();
        do{
            idLista = 1+random.nextInt(9999);
        }while(ListaModel.idExists(idLista, con));

        ListaBean lb = new ListaBean(idLista,idUser,nome,privada);
        ListaModel.create(lb, con);
        System.out.println("\nLista '" + nome + "' criada com ID: " + idLista);
    }

    public void listarListasDoUsuario(Connection con) throws SQLException{
        
        Scanner input = new Scanner(System.in);
        System.out.println("\n-----Ver Minhas Listas-----");
        System.out.println("\nUsuários disponíveis:");
        new UsuarioController().listarUsuarios(con);
        System.out.print("\nDigite o seu ID de Usuário: ");
        int idUser = input.nextInt();
        input.nextLine();

        ArrayList<ListaBean> listas = ListaModel.listAllByUser(idUser, con);

        if(listas.isEmpty()){
            System.out.println("nenhuma lista :( ");
            return;
        }
        
        int idListaEscolhida;
        do{
            System.out.println("\n-----Listas do Usuário ID " + idUser + "-----");
            for(ListaBean lb : listas){
            System.out.println("ID: " + lb.getIdLista() + " | Nome: " + lb.getNome());
            }
            System.out.print("\nDigite o ID de uma lista (ou 0 para voltar ao menu): ");
            idListaEscolhida = input.nextInt();
            input.nextLine();

            if(idListaEscolhida != 0){
                ArrayList<FilmeBean> filmes = ListaModel.pegarFilmesDaLista(idListaEscolhida, con);

                System.out.println("\n-----Filmes na Lista ID " + idListaEscolhida + "-----");
                System.out.println("----- ----- ----- ----- -----");                
                if(filmes.isEmpty()){
                    System.out.println("(Esta lista está vazia)");
                }else{
                    new FilmeController().listarFilmesSimples(filmes);
                    System.out.println("----- ----- ----- ----- -----");                                    
                }
            }
            
        } while (idListaEscolhida != 0);
    }

    public void deleteLista(Connection con) throws SQLException {
        Scanner s = new Scanner(System.in);
        System.out.println("\n-----Remover uma Lista-----");
        new UsuarioController().listarUsuarios(con);

        System.out.print("\nDigite o ID de Usuário ");
        int idUser = s.nextInt();
        new ListaController().listarListasSimples(idUser, con);
        
        System.out.print("\nDigite o ID da lista pra remover: ");
        int idLista = s.nextInt();

        System.out.print("\nTem certeza da remoção? (s/n) ");
        if (s.next().equalsIgnoreCase("s")) {
            ListaModel.delete(idLista, idUser, con);
        } else {
            System.out.println("\noperação cancelada");
        }
    }
    
    public boolean listarListasSimples(int idUser,Connection con) throws SQLException{
        System.out.println("\n-----Listas do Usuário ID " + idUser + "-----");
        ArrayList<ListaBean> listas = ListaModel.listAllByUser(idUser, con);
        if (listas.isEmpty()) {
            System.out.println("nenhuma lista :(");
            return false;
        }
        for (ListaBean lb : listas) {
            System.out.println(lb.toString());
        }
        return true;
    }
    
    public void adicionarFilmeLista(Connection con) throws SQLException{
        Scanner s = new Scanner(System.in);
        System.out.println("\n-----Adicionar Filme a Lista-----");

        System.out.println("\nUsuários disponíveis:");
        new UsuarioController().listarUsuarios(con);
        System.out.print("\nDigite o seu ID de Usuário: ");
        int idUser = s.nextInt();

        System.out.println("\n-----Listas Disponíveis-----");
        boolean temListas = this.listarListasSimples(idUser, con); 
        if (!temListas) {
            return;
        }
        System.out.print("\nDigite o ID da Lista na qual deseja adicionar o filme: ");
        int idLista = s.nextInt();

        System.out.println("\n-----Filmes Disponíveis-----");
        new ElencoController().listarFilmesSimples(con);
        System.out.print("\nDigite o ID do Filme a adicionar à lista: ");
        int idFilme = s.nextInt();
        System.out.print("Digite a posição do filme nesta lista (ex: 1, 2, 3): ");
        int posicao = s.nextInt();

        ListaFilmeBean ldfb = new ListaFilmeBean(idLista, idFilme, posicao);
        ListaFilmeModel.addFilme(ldfb, con);
    }
    
}
