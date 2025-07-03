
package sistemafilmes.controller;

/**
 *
 * @author gabriel-da-rosa : gustavo-gonçalves
 */

import sistemafilmes.model.AvaliacaoJoinModel;
import sistemafilmes.bean.AvaliacaoJoinBean;
import java.sql.*;
import java.util.*;

public class AvaliacaoJoinController {
    
    public void listarAvaliacoesJoin(Connection con) throws SQLException{
        
        System.out.println("\n-----Lista de todas as avaliacoes-----");
        
        ArrayList<AvaliacaoJoinBean> lista = AvaliacaoJoinModel.listAllJoin(con);
        
        if(lista.isEmpty()){
            System.out.println("Nenhuma avaliacao encontrada :( ");
        }else{
            for(AvaliacaoJoinBean adb:lista){
                System.out.println(adb.toString());
            }
        }  
    }
    
}
