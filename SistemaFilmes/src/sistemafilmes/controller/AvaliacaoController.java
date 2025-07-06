package sistemafilmes.controller;

/**
 *
 * @author gabriel-da-rosa : gustavo-gonçalves
 */

import sistemafilmes.bean.*;
import sistemafilmes.model.*;
import java.sql.*;
import java.util.Scanner;

public class AvaliacaoController {

    public void createAvaliacao(Connection con) throws SQLException{
        
        Scanner s = new Scanner(System.in);
        System.out.println("\n-----Fazer uma avaliação-----");

        System.out.println("\nUsuários disponíveis:");
        new UsuarioController().listarUsuarios(con);
        System.out.print("\nDigite o ID do Usuário que está avaliando:");
        int idUser = s.nextInt();

        System.out.println("\nFilmes disponíveis:");
        new ElencoController().listarFilmesSimples(con);
        System.out.print("\nDigite o ID do Filme que vai ser avaliado: ");
        int idFilme = s.nextInt();
        s.nextLine();

        if(AvaliacaoModel.avaliacaoExists(idUser,idFilme,con)){
            System.out.print("\nVocê já avaliou este filme. Deseja substituir a antiga? (s/n): ");
            String resposta = s.nextLine();

            if(resposta.equalsIgnoreCase("s")){
                System.out.println("\n-----Nova Avaliacao-----");
                System.out.print("Nova nota (de 0 a 5): ");
                double nota = s.nextDouble();
                s.nextLine();
                System.out.print("Crítica: ");
                String critica = s.nextLine();
                long millis = System.currentTimeMillis();
                Date data = new Date(millis);

                AvaliacaoBean ab = new AvaliacaoBean(idUser,idFilme,critica,nota,data);
                AvaliacaoModel.update(ab, con); 
                
            }else{
                System.out.println("Operação cancelada :( ");
            }
        }else{
            System.out.print("Digite a nota (de 0 a 5): ");
            double nota = s.nextDouble();
            s.nextLine(); 

            System.out.print("Crítica: ");
            String critica = s.nextLine();

            long millis = System.currentTimeMillis();
            Date data = new Date(millis);

            AvaliacaoBean ab = new AvaliacaoBean(idUser,idFilme,critica,nota,data);
            AvaliacaoModel.create(ab,con);
            System.out.println("Avaliação registrada :) ");
        }
    }
    
    public void deleteAvaliacao(Connection con) throws SQLException{

        Scanner input = new Scanner(System.in);
        System.out.println("\n--- Remover uma Avaliação ---");

        System.out.println("\nUsuários:");
        new UsuarioController().listarUsuarios(con);

        System.out.print("\nDigite o ID do usuário: ");
        int idUser = input.nextInt();
        input.nextLine();

        System.out.println("\n-----Avaliações feitas pelo usuário-----" + idUser + "-----");
        String sql = "SELECT f.IDFilme, f.Titulo, a.Nota FROM Avaliacao a JOIN Filme f ON a.IDFilme = f.IDFilme WHERE a.IDUser = ?";
        try(PreparedStatement st = con.prepareStatement(sql)){
            st.setInt(1,idUser);
            ResultSet rs = st.executeQuery();

            if(!rs.isBeforeFirst()){
                System.out.println("Nenhuma avaliação :( ");
                return;
            }
            while(rs.next()) {
                System.out.println("ID do Filme: " + rs.getInt("IDFilme") + " | Título: " + rs.getString("Titulo") + " | Nota: " + rs.getDouble("Nota"));
            }
        }
        System.out.print("\nDigite o ID do Filme da avaliação a ser removida: ");
        int idFilme = input.nextInt();
        input.nextLine();
        System.out.print("Remover avaliacao? (s/n): ");
        if(input.next().equalsIgnoreCase("s")){
            AvaliacaoModel.delete(idUser,idFilme,con);
        }else{
            System.out.println("Operação cancelada :( ");
        }
    }
    
}
