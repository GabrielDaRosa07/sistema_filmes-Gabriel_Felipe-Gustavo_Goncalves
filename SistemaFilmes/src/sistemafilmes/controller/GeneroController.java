package sistemafilmes.controller;

/**
 *
 * @author gabriel-da-rosa : gustavo-gonçalves
 */

import sistemafilmes.model.GeneroModel;
import sistemafilmes.bean.GeneroBean;
import java.sql.*;
import java.util.*;

public class GeneroController {
    
    public void createGenero(Connection con) throws SQLException{
        
        Scanner s = new Scanner(System.in);
        System.out.println("\n-----Casdastro de Genero-----");
        
        int idGenero;
        Random random = new Random();
        
        do{
            idGenero = 1+random.nextInt(99);
        }while(GeneroModel.idExists(idGenero, con));
        
        System.out.println("\nId gerado: "+idGenero);
        
        System.out.print("Nome do Genero: ");
        String nome = s.nextLine();
        
        GeneroBean gb = new GeneroBean(idGenero, nome);
        GeneroModel.create(gb, con);
        System.out.print("Genero criado :) ");
        
    }
    
    public void listarGeneros(Connection con) throws SQLException{
        
        System.out.println("\n-----Lista de Generos-----");
        ArrayList<GeneroBean> listaGenero = GeneroModel.listAll(con);
        for(GeneroBean gb:listaGenero){
            System.out.println(gb.toString());
        }
        
    }
    
    public void deleteGenero(Connection con) throws SQLException{
        
        Scanner s = new Scanner(System.in);
        
        System.out.println("\n-----Remover Genero-----");
        System.out.print("Digite o genero a ser removido: ");
        
        int id = s.nextInt();
        s.nextLine();
        
        System.out.print("Tem certeza da remoção? (s/n) ");
        
        if(s.next().equalsIgnoreCase("s")){
            GeneroModel.delete(id, con);
        }else{
            System.out.println("Remoçao cancelada :( ");
        }
    }
    
}
